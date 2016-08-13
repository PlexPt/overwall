package cc.overwall.overwall.ui;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cc.overwall.overwall.App;
import cc.overwall.overwall.Bean.Node;
import cc.overwall.overwall.Bean.NodeInfo;
import cc.overwall.overwall.NodeAdapter;
import cc.overwall.overwall.R;
import cc.overwall.overwall.Utils.Tools;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import okhttp3.Call;

import static android.content.Context.MODE_PRIVATE;

public class Fragment2 extends Fragment {
    public static final String TAG = "Fragment2";
    public static final String NODE = "https://www.overwall.cc/api/node?access_token=";
    private String token;
    public static int times = 1;
/*    private ImageView img_city;
    private MyButton listButtonConnect;
    private TextView listTitle;
    private TextView listSpeed;
    private TextView listInfo;
    private TextView listBottom;*/
private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;

    private static final String ARG_POSITION = "position";

    private int position;
    private RecyclerView mRecyclerView;

    public static Fragment2 newInstance(int position) {
        Fragment2 f = new Fragment2();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);

        Log.e(TAG, "onCreateView");
        View mview = inflater.inflate(R.layout.fragment_node, container, false);
      
        return mview;
    }

    @Override
    public  void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated");
        times = 1;
        mRecyclerView =
                (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) getActivity().findViewById(R.id.wave_refresh);
        mWaveSwipeRefreshLayout.setWaveColor(App.preferenceR.getInt("theme_color", Color.BLUE));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
 
    @Override
    public void onRefresh() {
        getData();
        mWaveSwipeRefreshLayout.setRefreshing(false);
    }
            
});

        SharedPreferences p = getActivity().getSharedPreferences("overwall", MODE_PRIVATE);
        token = p.getString("token", "");
        getData();
    }

    void getData() {
        OkHttpUtils.get()
                .url(NODE + token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Tools.showToast("网络不好,刷新节点信息失败,重启app试试");
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            JSONObject jsonobj = new JSONObject(s);
                            if (jsonobj.getInt("ret") != 0)
                                processNodeData(jsonobj.toString());
                            else
                                App.login();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
       /* JsonObjectRequest getDataRequest = new JsonObjectRequest(
                Request.Method.GET,  NODE + token,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonobj) {

                        try {
                            if (jsonobj.getInt("ret")!=0)
                            processNodeData(jsonobj.toString());
                            else 
                                App.login();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Tools.showToast(error.toString());
                if (times < 4) {
                    getData();
                    times++;
                }
            }
        });

        App.mQueue.add(getDataRequest);*/


    }


    private void processNodeData(String data) {
        Gson gson = new Gson();
        ArrayList<Node> node = null;
        try {
            String formatedData = Tools.unicode2String(data);
            Log.e(TAG, formatedData);
            NodeInfo nodeinfo = gson.fromJson(formatedData, NodeInfo.class);
            if (nodeinfo.getRet() == 1) {
                node = nodeinfo.getData();
            }
        } catch (Exception e) {
            Log.e(TAG,e.toString());
        }
        if (mRecyclerView.getAdapter() ==null)
        mRecyclerView.setAdapter(new NodeAdapter(node));
        else {
         ( (NodeAdapter) mRecyclerView.getAdapter()).setNode(node);
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
         
    }
}

