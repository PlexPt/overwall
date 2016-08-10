package cc.overwall.overwall.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.overwall.overwall.App;
import cc.overwall.overwall.R;

public class Fragment3 extends Fragment {

    private static final String ARG_POSITION = "position";
    public static final String SSR_DL = "https://www.overwall.cc/downloads/client/SSR.apk";
    private int position;
    private CardView btnTopay;
    private CardView btnToshop;
    private CardView btnToinvite;
    private CardView btnToedit;
    private CardView btnSignout;
    private CardView btnToabout;
    private CardView btnDownload;


    public static Fragment3 newInstance(int position) {
        Fragment3 f = new Fragment3();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_more, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnTopay = (CardView) getActivity().findViewById(R.id.btn_topay);
        btnToshop = (CardView) getActivity().findViewById(R.id.btn_toshop);
        btnToinvite = (CardView) getActivity().findViewById(R.id.btn_toinvite);
        btnToedit = (CardView) getActivity().findViewById(R.id.btn_toedit);
        btnToabout = (CardView) getActivity().findViewById(R.id.btn_toabout);
        btnSignout = (CardView) getActivity().findViewById(R.id.btn_signout);
        btnDownload = (CardView) getActivity().findViewById(R.id.btn_download);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(SSR_DL));

                startActivity(intent);
            }
        });
        btnToabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutActivity.class));

            }
        });
        btnToedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditActivity.class);
                startActivity(i);
            }
        });
        btnTopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PayActivity.class);
                startActivity(intent);
            }
        });
        btnToshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ShopActivity.class);
                startActivity(i);
            }
        });
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.preferenceE.putInt("user_id", 0);
                App.preferenceE.commit();
                App.clearCookie();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        btnToinvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), InviteActivity.class);
                getActivity().startActivity(i);
            }
        });
    }
}
