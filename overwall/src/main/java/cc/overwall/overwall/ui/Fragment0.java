package cc.overwall.overwall.ui;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sefford.circularprogressdrawable.CircularProgressDrawable;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import cc.overwall.overwall.App;
import cc.overwall.overwall.Bean.UserSet;
import cc.overwall.overwall.R;
import cc.overwall.overwall.Utils.Tools;
import cc.overwall.overwall.Utils.WaveHelper;
import cc.overwall.overwall.WaveAdapter;
import okhttp3.Call;

import static android.content.Context.MODE_PRIVATE;

public class Fragment0 extends Fragment {

    public static final String USER = "https://www.overwall.cc/api/user/";
    private static final String ARG_POSITION = "position";
    private static final String URL_CHEKIN = "https://www.overwall.cc/user/checkin";
    private static final String TAG = "Fragment0";
    private int position;
    String token;
    int user_id;
    private ImageView fabButton;
    private TextView textLiuliang;
    CircularProgressDrawable drawable;
    Animator currentAnimation;

    private WaveHelper mWaveHelper;
    private int mBorderColor = Color.parseColor("#44FFFFFF");
    private int mBorderWidth = 10;
    private WaveView waveView;
    private WaveAdapter mWaveAdapter;
    private RecyclerView mRecyclerView;
    
    public static Fragment0 newInstance(int position) {
        Fragment0 f = new Fragment0();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
/*mRecyclerView = (RecyclerView) container.findViewById(R.id.recycler_wavebool);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new WaveAdapter());*/
        CircularProgressDrawable drawable ;
       
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        token = App.preferenceR.getString("token", "");
        user_id = App.preferenceR.getInt("user_id", 0);

    
        waveView = (WaveView) getActivity().findViewById(R.id.wave);

        waveView.setBorder(mBorderWidth, mBorderColor);
        waveView.setShapeType(WaveView.ShapeType.CIRCLE);
        // waveView.setAlpha(0.5f);
        mWaveHelper = new WaveHelper(waveView);
        fabButton = (ImageView) getActivity().findViewById(R.id.btn_checkin);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentAnimation != null) {
                    currentAnimation.cancel();
                }
                currentAnimation = prepareStyle1Animation();
                currentAnimation.start();
                OkHttpUtils.postString()
                        .url(URL_CHEKIN)
                        .content("")
                        .build()

                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int i) {
                                Tools.showToast("网络不好签到失败");
                                
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
        OkHttpUtils.get()
                .url(USER + user_id + "?access_token=" + token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Tools.showToast("网络不好,刷新信息失败,重启app试试");

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        waveView.setWaveColor(
                                Color.parseColor("#404CAF50"),
                                Color.parseColor("#804CAF50"));
                        mBorderColor = Color.parseColor("#B0f44336");
                        //#f44336
                        waveView.setBorder(mBorderWidth, mBorderColor);

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getInt("ret") == 1)
                                processData(jsonObject.toString());
                            else
                                App.login();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        
                    }
                });
       /* JsonObjectRequest getInfoRequest = new JsonObjectRequest(
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
*/

    }


    private void processData(String data) {
        

        getActivity().findViewById(R.id.progress).setVisibility(View.GONE);

        Gson gson = new Gson();
        try {
            Log.e(TAG, data);
            JSONObject s = new JSONObject(data);
            JSONObject g = (JSONObject) s.get("data");
            App.preferenceE.putString("user_info",g.toString()).apply();
            UserSet user = gson.fromJson(g.toString(), UserSet.class);
            setInfo(user);

            String method = user.getMethod();
            String passwd = user.getPasswd();
            int port = user.getPort();

            SharedPreferences.Editor ed = getActivity().getSharedPreferences("overwall", MODE_PRIVATE).edit();
            ed.putInt("port", port);
            ed.putString("method", method);
            ed.putString("passwd", passwd);
            ed.apply();
        } catch (JSONException e) {
            Tools.showDebugToast(e.toString());
        }


    }

    private void setInfo(UserSet user) {

        App.preferenceE.putInt("user_class",user.getClassX()).apply();
        TextView t1 = (TextView) getActivity().findViewById(R.id.info1TextView1);
        TextView t2 = (TextView) getActivity().findViewById(R.id.info2TextView1);
        TextView t3 = (TextView) getActivity().findViewById(R.id.info3TextView1);
        TextView t5 = (TextView) getActivity().findViewById(R.id.info5TextView1);
        TextView t6 = (TextView) getActivity().findViewById(R.id.info6TextView1);
        TextView t7 = (TextView) getActivity().findViewById(R.id.info7TextView1);
        TextView t8 = (TextView) getActivity().findViewById(R.id.info8TextView1);
        TextView t9 = (TextView) getActivity().findViewById(R.id.info9TextView1);
        
        
            t1.setText("用 户 名 : " + user.getUser_name() +"");
            t2.setText("邮 箱 : "+user.getEmail()+"");
            t3.setText("账 户 余 额 : "+user.getMoney()+"");
            t5.setText("账 户 等 级 : "+user.getClassX() + "");
            t6.setText("等 级 到 期 : "+user.getClass_expire()+"");
            int T = (int) ((user.getTransfer_enable()) / 1000000);
            int S = (int) ((user.getTransfer_enable() / 1000000)
                    - ((user.getU() + user.getD()) / 1000000));
        if (T<1000){
            t7.setText("总 流 量 : "+T + "M");}
        else {
            float TT = ((float) T) / 1000;
            t7.setText("总 流 量 : "+TT + "G");
        }
        long USE =((user.getU() + user.getD()) / 1000000);
        if (USE < 1000){
            t8.setText("已 用 流 量 : "+USE + "M");}
        else {
            float UU = ((float) USE)/1000;
            t8.setText("已 用 流 量 : "+UU + "G");
        }
        if (S < 1000){
            t9.setText("剩 余 流 量 : "+S + "M");}
else {
            float SS = ((float) S)/1000;
            t9.setText("剩 余 流 量 : "+SS + "G");
        }
            float waterLevel = (float) S / (float) T;
            Log.e(TAG, "水深" + String.valueOf(waterLevel));
            if (waterLevel > 0.9f)
                waterLevel = waterLevel - 0.1f;
            if (waterLevel < 0.3f) {
                waveView.setWaveColor(
                        Color.parseColor("#40f44336"),
                        Color.parseColor("#80f44336"));
                mBorderColor = Color.parseColor("#B04CAF50");

                waveView.setBorder(mBorderWidth, mBorderColor);
            }
            // waterLevel = 0.4f;
            waveView.setWaterLevelRatio(waterLevel);
            textLiuliang = (TextView) getActivity().findViewById(R.id.text_liuliang);
        if (S>1000){
            float SS = ((float) S)/1000;
        textLiuliang.setText(+SS + "G");}
        else {
        
            textLiuliang.setText(S + "M");}
        }
    


    @Override
    public void onPause() {
        super.onPause();
        mWaveHelper.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWaveHelper.start();
        String user_info = App.preferenceR.getString("user_info","");
        if (user_info != "") {
            UserSet user = new Gson().fromJson(user_info, UserSet.class);
            setInfo(user);
        }
        getInfo();
        drawable = new CircularProgressDrawable.Builder()
                .setRingWidth(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size))
                .setOutlineColor(getResources().getColor(android.R.color.darker_gray))
                .setRingColor(getResources().getColor(android.R.color.holo_green_light))
                .setCenterColor(getResources().getColor(R.color.Blue))
                .create();
       fabButton. setImageDrawable(drawable);
    }
    private Animator prepareStyle1Animation() {
        AnimatorSet animation = new AnimatorSet();

        final Animator indeterminateAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0, 3600);
        indeterminateAnimation.setDuration(3600);

        Animator innerCircleAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0f, 0.75f);
        innerCircleAnimation.setDuration(3600);
        innerCircleAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                drawable.setIndeterminate(true);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                indeterminateAnimation.end();
                drawable.setIndeterminate(false);
                drawable.setProgress(0);
            }
        });

        animation.playTogether(innerCircleAnimation, indeterminateAnimation);
        return animation;
    }
}

