package cc.overwall.overwall.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.overwall.overwall.App;
import cc.overwall.overwall.R;
import okhttp3.Call;
import okhttp3.MediaType;


public class LoginActivity extends AppCompatActivity {

    private static final String API = "https://www.overwall.cc/api/token";
    
    private AppCompatAutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        if (App.preferenceR.getInt("user_id", 0) != 0) {
            
           toMain();
        }
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mEmailView = (AppCompatAutoCompleteTextView) findViewById(R.id.email);
     

        findViewById(R.id.register_button)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_reg = new Intent(LoginActivity.this, RegActivity.class);
                        startActivity(intent_reg);
                    }
                });
        
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    appLogin();
                    return true;
                }
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
                return false;
            }
        });

        View mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                appLogin();
            }
        });

       
    }

    private void toMain() {

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }


    private void appLogin() {


        // Reset errors.
        mEmailView.setError("错误");
        mPasswordView.setError("错误");

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        App.preferenceE.putString("email", email);
        App.preferenceE.putString("password", password);
        App.preferenceE.commit();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // 检查有效的电子邮件地址
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            realLogin(email, password);


        }
    }

    private void realLogin(String email, String passwd) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("email", email);
        map.put("passwd", passwd);

        OkHttpUtils.postString()
                .url(API)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new JSONObject(map).toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {

                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(s);
                            deal(jsonobj);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
       /* JsonObjectRequest newMissRequest = new JsonObjectRequest(
                Request.Method.POST, API,
                new JSONObject(map), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonobj) {
                deal(jsonobj);
            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                showProgress(false);
                mEmailView.setText(error.toString());
                Tools.showDebugToast("错误" + error.toString());
            }
        });

        App.mQueue.add(newMissRequest);


    */
    }
    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {

        return password.length() > 6;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    private void deal(JSONObject jsonobj)

    {
        try {
            if (jsonobj.getInt("ret") == 1) {
                JSONObject j = (JSONObject) jsonobj.get("data");
                String TOKEN = j.getString("token");
                int user_id = j.getInt("user_id");
                SharedPreferences.Editor ed = getSharedPreferences("overwall", MODE_PRIVATE).edit();
                ed.putInt("user_id", user_id);
                ed.putString("token", TOKEN);
                ed.commit();
                toMain();

                
            }
            else {
                showProgress(false);
                 Toast.makeText(LoginActivity.this, jsonobj.getString("msg"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            showProgress(false);
            Toast.makeText(LoginActivity.this, "网络太差", Toast.LENGTH_LONG).show();

        }
    }
}

