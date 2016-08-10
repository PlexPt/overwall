package cc.overwall.overwall.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import cc.overwall.overwall.R;
import cc.overwall.overwall.Utils.Tools;
import okhttp3.Call;

/**
 * Created by pt on 16-8-10.
 */

public class Fragment0 extends Fragment {

    private static final String ARG_POSITION = "position";
    private static final String URL = "https://www.overwall.cc/user";

    public static Fragment0 newInstance(int position) {
        Fragment0 f = new Fragment0();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notice, container, false);


        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TextView mTextView = (TextView) getActivity().findViewById(R.id.notice_1);
//        while (App.isCookieOK = true) {
        OkHttpUtils.get()
                .url(URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (s.indexOf("【") != -1) {
                            mTextView.setText("");

                            ArrayList<String> arrayList = Tools.getStringMiddle2(s, "【", "】");

                            for (String c : arrayList) {
                                mTextView.append(c + "\n\n");
                            }

                        }
                    }
                });
        
    }
}
