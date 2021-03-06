package cc.overwall.overwall;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.overwall.overwall.Utils.SimpleCookieJar;
import cc.overwall.overwall.ui.LoginActivity;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class App extends Application {
    private static final String API = "https://www.overwall.cc/api/token";
    private static final boolean DEBUG = false;
    public static boolean isCookieOK;

    private static Context context;

    // 建立请求队列
    //  public static RequestQueue mQueue;
    public static SharedPreferences.Editor preferenceE;
    public static SimpleCookieJar cookieJar;
    public static SharedPreferences preferenceR;


    @Override
    public void onCreate() {
        super.onCreate();


        context = getApplicationContext();
        if (DEBUG == true) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(getApplicationContext());
        }
        //  mQueue = Volley.newRequestQueue(getApplicationContext());

        cookieJar =
                new SimpleCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(getApplicationContext()));
        //  HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();
        OkHttpUtils.initClient(okHttpClient);
        preferenceE = getSharedPreferences("overwall", MODE_PRIVATE).edit();
        preferenceR = getSharedPreferences("overwall", MODE_PRIVATE);

    }

    public static void clearCookie() {
        App.cookieJar.clear();
    }

    public static Context getContext() {
        return context;
    }

    public static void login() {
        String email = preferenceR.getString("email", "");
        String passwd = preferenceR.getString("passwd", "");

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
                        Log.e("App", e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try{
                            JSONObject jsonobj = new JSONObject(s);
                        if (jsonobj.getInt("ret") == 1) {
                            JSONObject j = (JSONObject) jsonobj.get("data");
                            String TOKEN = j.getString("token");
                            int user_id = j.getInt("user_id");
                            SharedPreferences.Editor ed = preferenceE;
                            ed.putInt("user_id", user_id);
                            ed.putString("token", TOKEN);
                            ed.commit();

                        } else {

                            Intent intent =new Intent(context,LoginActivity.class);
                            context.startActivity(intent);
                        }
                    } catch (JSONException e) {
                    }
                    }
                });
       /* JsonObjectRequest newMissRequest = new JsonObjectRequest(
                Request.Method.POST, API,
                new JSONObject(map), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonobj) {

                Tools.showDebugToast(jsonobj.toString());
                try {
                    if (jsonobj.getInt("ret") == 1) {
                        JSONObject j = (JSONObject) jsonobj.get("data");
                        String TOKEN = j.getString("token");
                        int user_id = j.getInt("user_id");
                        SharedPreferences.Editor ed = preferenceE;
                        ed.putInt("user_id", user_id);
                        ed.putString("token", TOKEN);
                        ed.commit();
                        
                    } else {

                        Intent i =new Intent(context,LoginActivity.class);
                        context.startActivity(i);
                    }
                } catch (JSONException e) {
                }


            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Tools.showDebugToast("错误" + error.toString());
            }
        });

       App.mQueue.add(newMissRequest);
        */
    }

    public static boolean isMN() {

        if (Build.VERSION.SDK_INT >= 23)
            return true;
        else
            return false;
    }
}
