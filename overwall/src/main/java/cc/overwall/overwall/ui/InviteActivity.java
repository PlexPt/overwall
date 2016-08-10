package cc.overwall.overwall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cc.overwall.overwall.R;
import cc.overwall.overwall.Utils.Tools;
import okhttp3.Call;


public class InviteActivity extends AppCompatActivity {
    public static final String URL_INVITE =
            "https://www.overwall.cc/user/invite";
    private CardView btnMakecode;


    private TextView mTextView;

    public void getcode() {
        OkHttpUtils.get()
                .url(URL_INVITE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Log.e("PayAty", e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (s.indexOf("<tbody>") != -1) {
                            String code = Tools.getStringMiddle(s, "<tbody>", "</tbody>");
                            code = code.trim();
                            if (code.length() > 20) {
                                mTextView.setText("");
                                ArrayList<String> arrayList = Tools.getStringMiddle2(code, "blank\">", "</a>");
                                Log.e("pay", code);
                                for (String c : arrayList) {
                                    mTextView.append("(长按复制)" + c + "\n\n");
                                }
                            } else {
                                mTextView.setText("邀请码已消耗光");
                            }

                        }
                    }
                });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        btnMakecode = (CardView) findViewById(R.id.btn_makecode);

        mTextView = (TextView) findViewById(R.id.pay_code);
        btnMakecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.postString()
                        .url(URL_INVITE)
                        .content("")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int i) {

                            }

                            @Override
                            public void onResponse(String s, int i) {
                                Log.e("makecode", s);
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    Tools.showToast(Tools.unicode2String(jsonObject.getString("msg")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                getcode();

                            }
                        });
            }
        });


        getcode();
    }
}
