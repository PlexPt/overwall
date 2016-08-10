package cc.overwall.overwall.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import java.util.List;

import okhttp3.Cookie;

/**
 * Created by root on 16-8-8.
 */

public class sync {
    @SuppressWarnings("deprecation")
    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        List<Cookie> cookies =SimpleCookieJar.getCookies();
        StringBuffer sb = new StringBuffer();

        for (Cookie cookie : cookies) {

            String cookieName = cookie.name();
            String cookieValue = cookie.value();
            if (!TextUtils.isEmpty(cookieName)
                    && !TextUtils.isEmpty(cookieValue)) {
                sb.append(cookieName + "=");
                sb.append(cookieValue + ";");
            }
        }


        String[] cookie = sb.toString().split(";");
        for (int i = 0; i < cookie.length; i++) {
            Log.d("cookie[i]", cookie[i]);
            cookieManager.setCookie(url, cookie[i]);// cookies是在HttpClient中获得的cookie    
        }


        CookieSyncManager.getInstance().sync();
    }
}
