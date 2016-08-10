package cc.overwall.overwall.Utils;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.CookieCache;
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;


public final class SimpleCookieJar extends PersistentCookieJar {
    private final List<Cookie> allCookies = new ArrayList<Cookie>();

    private static List<Cookie> cookies;

    public SimpleCookieJar(CookieCache cache, CookiePersistor persistor) {
        super(cache, persistor);
    }

    public static List<Cookie> getCookies() {
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }

    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        super.saveFromResponse(url, cookies);
        allCookies.addAll(cookies);

        setCookies(cookies);
    }

    public static void setCookies(List<Cookie> cookies) {
        SimpleCookieJar.cookies = cookies;
    }

    @Override
    public synchronized List<okhttp3.Cookie> loadForRequest(HttpUrl url) {
        super.loadForRequest(url);
        List<Cookie> result = new ArrayList<Cookie>();
        for (Cookie cookie : allCookies) {
            if (cookie.matches(url)) {
                result.add(cookie);
            }
        }
        return result;
    }

}  