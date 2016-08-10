package cc.overwall.overwall.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import cc.overwall.overwall.App;
import cc.overwall.overwall.R;


public class PayActivity extends AppCompatActivity {
    private TextView payId;
    private CardView btnToAlipay;
    private CardView btnToAlipay2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        payId = (TextView) findViewById(R.id.pay_id);
        btnToAlipay = (CardView) findViewById(R.id.btn_to_alipay);
        btnToAlipay2 = (CardView) findViewById(R.id.btn_to_alipay2);


        payId.append(App.preferenceR.getInt("user_id", 0) + "");
        btnToAlipay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("overwall", getString(R.string.alipay_code));
                clipboardManager.setPrimaryClip(clip);

                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.eg.android.AlipayGphone");
                startActivity(LaunchIntent);
            }
        });
        btnToAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://qr.alipay.com/4989345434310931"));
                startActivity(i);
            }
        });
    }

}
