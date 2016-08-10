package cc.overwall.overwall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.overwall.overwall.App;
import cc.overwall.overwall.R;
import cc.overwall.overwall.Utils.Tools;

/**
 * Created by xps on 2016/7/13.
 */

public class RegActivity extends AppCompatActivity {
    public  static final String API_REG = "https://www.overwall.cc/auth/register";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

    
     final AutoCompleteTextView regEmail;
       final EditText regNickname;
         final EditText regPassword;
          final EditText regRepassword;
          final EditText regQq;
          final EditText regInvitCode;
          CardView regHelpButton;

        regEmail = (AutoCompleteTextView) findViewById(R.id.reg_email);
        regNickname = (EditText) findViewById(R.id.reg_nickname);
        regPassword = (EditText) findViewById(R.id.reg_password);
        regRepassword = (EditText) findViewById(R.id.reg_repassword);
        regQq = (EditText) findViewById(R.id.reg_qq);
        regInvitCode = (EditText) findViewById(R.id.reg_invit_code);
        regHelpButton = (CardView) findViewById(R.id.reg_help_button);


        final TextView info   = (TextView) findViewById(R.id.reg_info);


        regHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent_help =new Intent(RegActivity.this,BuyActivity.class);
//                startActivity(intent_help);
            }
        });
        findViewById(R.id.register_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regEmail.getText().toString();
                String passwd = regPassword.getText().toString();
                String repasswd = regRepassword.getText().toString();
                String nickname = regNickname.getText().toString();
                String qq = regQq.getText().toString();
                String code = regInvitCode.getText().toString();


                Map<String, String> map = new HashMap<String, String>();
                map.put("email", email);
                map.put("passwd", passwd);
                map.put("repasswd",repasswd);
                map.put("name",nickname);
                map.put("wechat",qq);
                map.put("imtype","2");
                map.put("code",code);
                 
                
                
                
            JsonObjectRequest newMissRequest = new JsonObjectRequest(
                    Request.Method.POST, API_REG,
                    new JSONObject(map), new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject jsonobj) {

                    Tools.showDebugToast(jsonobj.toString());
                    deal(jsonobj);


                }


            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
 
                    regEmail.setText(error.toString());
                    Tools.showDebugToast("错误" + error.toString());
                }
            });

            App.mQueue.add(newMissRequest);


        }

            private void deal(JSONObject jsonobj) {
                try {
                    if (jsonobj.getInt("ret")==1)
                        info.setText("注册成功，请返回");
                    else 
                        info.setText(jsonobj.getString("msg"));
                   
                } catch (JSONException e) {
                    info.setText(jsonobj.toString());
                    e.printStackTrace();
                }
            }
        });
         
    }

    
}
