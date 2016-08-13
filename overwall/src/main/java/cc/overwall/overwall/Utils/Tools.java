package cc.overwall.overwall.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cc.overwall.overwall.App;

public class Tools {
    private static Context context = cc.overwall.overwall.App.getContext();
    private static final boolean switchs = false;

    public static void showSnack(final Context context, View view, String str) {
        Snackbar.make(view, str, Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }


    public static void showmySnack(View view, String str) {
        Snackbar.make(view, str, Snackbar.LENGTH_LONG).setAction("OK",null)
                .show();
    }

    public static void showDebugToast(String s) {
        if (switchs) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    public static void saveSetting(String key, String v) {
        SharedPreferences.Editor ed = context.getSharedPreferences("info", context.MODE_PRIVATE).edit();

        ed.putString(key, v);
        ed.commit();

    }


    public static String String2Base64(String str) {
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }

    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除  
        
        cookieManager.setCookie(url, App.cookieJar.toString());//cookies是在HttpClient中获得的cookie  
        CookieSyncManager.getInstance().sync();
    }

    public static ArrayList<String> getStringMiddle2(String src, String left, String right) {
        ArrayList<String> list = new ArrayList<String>();
        while (src.indexOf(left) != -1 && src.indexOf(right) != -1) {
            list.add(src.substring(src.indexOf(left) + left.length(), src.indexOf(right)));
            src = src.substring(src.indexOf(right) + right.length());
        }
        return list;
    }

    public static String getStringMiddle(String src, String left, String right) {
        if (src.indexOf(left) != -1 && src.indexOf(right) != -1)
            return src.substring(src.indexOf(left) + left.length(), src.indexOf(right));
        else
            return "";
    }

    private static final Pattern REG_UNICODE = Pattern.compile("[0-9A-Fa-f]{4}");
    private static final Pattern EN_CODE = Pattern.compile("[A-Za-z]{4}");

    public static String unicode2String(String str) {
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c1 = str.charAt(i);
            if (c1 == '\\' && i < len - 1) {
                char c2 = str.charAt(++i);
                if (c2 == 'u' && i <= len - 5) {
                    String tmp = str.substring(i + 1, i + 5);
                    Matcher matcher = REG_UNICODE.matcher(tmp);
                    if (matcher.find()) {
                        sb.append((char) Integer.parseInt(tmp, 16));
                        i = i + 4;
                    } else {
                        sb.append(c1).append(c2);
                    }
                } else {
                    sb.append(c1).append(c2);
                }
            } else {
                sb.append(c1);
            }
        }
        return sb.toString();
    }


}
