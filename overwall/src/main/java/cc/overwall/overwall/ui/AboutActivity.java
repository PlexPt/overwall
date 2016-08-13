package cc.overwall.overwall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cc.overwall.overwall.R;

/**
 * Created by pt on 16-8-10.
 */

public class AboutActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mTextView = (TextView) findViewById(R.id.meizi);
        findViewById(R.id.btn_meigong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Cieo cieo = new Cieo(AboutActivity.this, mTextView.getText().toString(), mTextView, null, new BulletListener(), MainAnimation.SHOTGUN1_SLOW, null,
                        HitAnimation.SHAKE_VERTICAL1, null, TargetAnimation.FLASH_SLOW, null);
                cieo.FIRE();
                */
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
       
    }
}
