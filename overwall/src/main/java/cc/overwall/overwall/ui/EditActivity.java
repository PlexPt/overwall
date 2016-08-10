package cc.overwall.overwall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cc.overwall.overwall.Utils.sync;


public class EditActivity extends AppCompatActivity {
    public static final String URL_SHOP = "https://www.overwall.cc/user/edit";
    WebView mWebView ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new WebView(this);
        setContentView(mWebView);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        sync.synCookies(this, "https://www.overwall.cc");
        mWebView.loadUrl(URL_SHOP);
    }
}
