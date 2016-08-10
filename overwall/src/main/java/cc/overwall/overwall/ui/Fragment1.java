package cc.overwall.overwall.ui;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import cc.overwall.overwall.App;
import cc.overwall.overwall.Bean.UserSet;
import cc.overwall.overwall.FloatingActionMyButton;
import cc.overwall.overwall.R;
import cc.overwall.overwall.Utils.Tools;
import cc.overwall.overwall.Utils.WaveHelper;
import okhttp3.Call;

import static android.content.Context.MODE_PRIVATE;

public class Fragment1 extends Fragment {

    public static final String USER = "https://www.overwall.cc/api/user/";
    private static final String ARG_POSITION = "position";
    private static final String URL_CHEKIN = "https://www.overwall.cc/user/checkin";
    private static final String TAG = "Fragment1";
    private int position;
    String token;
    int user_id;
    private FloatingActionMyButton fabButton;
    private TextView textLiuliang;


    private WaveHelper mWaveHelper;
    private int mBorderColor = Color.parseColor("#44FFFFFF");
    private int mBorderWidth = 10;
    private WaveView waveView;

    public static Fragment1 newInstance(int position) {
        Fragment1 f = new Fragment1();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        FloatingActionMyButton fab = (FloatingActionMyButton) rootView.findViewById(R.id.fabButton);
        fab.setDrawableIcon(getResources().getDrawable(R.drawable.ic_eyedropper_variant));


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        waveView = (WaveView) getActivity().findViewById(R.id.wave);

        waveView.setBorder(mBorderWidth, mBorderColor);
        waveView.setShapeType(WaveView.ShapeType.CIRCLE);
        // waveView.setAlpha(0.5f);
        mWaveHelper = new WaveHelper(waveView);

        token = App.preferenceR.getString("token", "");
        user_id = App.preferenceR.getInt("user_id", 0);

        getInfo();
        fabButton = (FloatingActionMyButton) getActivity().findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpUtils.postString()
                        .url(URL_CHEKIN)
                        .content("")
                        .build()

                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int i) {
                                Tools.showToast(e.toString());
                            }

                            @Override
                            public void onResponse(String s, int i) {


                                s = Tools.unicode2String(s);
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    s = jsonObject.getString("msg");
                                    Tools.showToast(s + "每天可以续命一次");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
            }
        });
    }


    void getInfo() {
        JsonObjectRequest getInfoRequest = new JsonObjectRequest(
                Request.Method.GET, USER + user_id + "?access_token=" + token,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonobj) {
                        waveView.setWaveColor(
                                Color.parseColor("#404CAF50"),
                                Color.parseColor("#804CAF50"));
                        mBorderColor = Color.parseColor("#B0f44336");
                        //#f44336
                        waveView.setBorder(mBorderWidth, mBorderColor);
                        try {
                            if (jsonobj.getInt("ret") != 0)
                                processData(jsonobj.toString());
                            else
                                App.login();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


                Tools.showDebugToast("错误" + error.toString());
            }
        });

        App.mQueue.add(getInfoRequest);


    }


    private void processData(String data) {
        int cla;

        getActivity().findViewById(R.id.progress).setVisibility(View.GONE);

        Gson gson = new Gson();
        try {
            Log.e(TAG, data);
            JSONObject s = new JSONObject(data);
            JSONObject g = (JSONObject) s.get("data");
            cla = g.getInt("class");
            UserSet user = gson.fromJson(g.toString(), UserSet.class);
            setInfo(user, cla);

            String method = user.getMethod();
            String passwd = user.getPasswd();
            int port = user.getPort();

            SharedPreferences.Editor ed = getActivity().getSharedPreferences("overwall", MODE_PRIVATE).edit();
            ed.putInt("port", port);
            ed.putString("method", method);
            ed.putString("passwd", passwd);
            ed.commit();

            Tools.showDebugToast("处理完了");
        } catch (JSONException e) {
            Tools.showDebugToast(e.toString());
        }


    }

    private void setInfo(UserSet user, int clas) {

        TextView t1 = (TextView) getActivity().findViewById(R.id.info1TextView1);
        TextView t2 = (TextView) getActivity().findViewById(R.id.info2TextView1);
        TextView t3 = (TextView) getActivity().findViewById(R.id.info3TextView1);
        TextView t4 = (TextView) getActivity().findViewById(R.id.info4TextView1);
        TextView t5 = (TextView) getActivity().findViewById(R.id.info5TextView1);
        TextView t6 = (TextView) getActivity().findViewById(R.id.info6TextView1);
        TextView t7 = (TextView) getActivity().findViewById(R.id.info7TextView1);
        TextView t8 = (TextView) getActivity().findViewById(R.id.info8TextView1);
        TextView t9 = (TextView) getActivity().findViewById(R.id.info9TextView1);
        t1.setText(user.getUser_name());
        t2.setText(user.getEmail());
        t3.setText(user.getMoney());
        t4.setText(user.getReg_date());
        t5.setText(clas + "");
        t6.setText(user.getClass_expire());
        int T =(int ) (( user.getTransfer_enable()) / 1000000);
        int S = (int)((user.getTransfer_enable() / 1000000)
                - ((user.getU() + user.getD()) / 1000000));
        t7.setText(T + "M");
        t8.setText(( (user.getU() + user.getD()) / 1000000) + "M");
        t9.setText(S + "M");

        float waterLevel = S / T;
        Log.e(TAG, "水深" + String.valueOf(waterLevel));
        if (waterLevel < 0.1f)
            waterLevel = waterLevel + 0.1f;
        if (waterLevel > 0.7f) {
            waveView.setWaveColor(
                    Color.parseColor("#40f44336"),
                    Color.parseColor("#80f44336"));
            mBorderColor = Color.parseColor("#B04CAF50");

            waveView.setBorder(mBorderWidth, mBorderColor);
        }

        waveView.setWaterLevelRatio(waterLevel);
        textLiuliang = (TextView) getActivity().findViewById(R.id.text_liuliang);
        textLiuliang.setText(S + "M");
    }

    /*
    @Override
    public void onPause() {
        super.onPause();
        MainActivity.cancelWave();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.startWave();
    }*/
    @Override
    public void onPause() {
        super.onPause();
        mWaveHelper.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWaveHelper.start();
    }
}

