package cc.overwall.overwall.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cc.overwall.overwall.App;
import cc.overwall.overwall.Bean.Node;
import cc.overwall.overwall.Bean.NodeInfo;
import cc.overwall.overwall.MyAdapter;
import cc.overwall.overwall.MyButton;
import cc.overwall.overwall.R;
import cc.overwall.overwall.Utils.Tools;

import static android.content.Context.MODE_PRIVATE;

public class Fragment2 extends Fragment {
    public static final String TAG = "Fragment2";
    public static final String NODE = "https://www.overwall.cc/api/node?access_token=";
    private String token;
    public static int times = 1;
    private ImageView listitemcardmainImageView1;
    private MyButton listButtonConnect;
    private TextView listTitle;
    private TextView listSpeed;
    private TextView listInfo;
    private TextView listBottom;


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
        mRecyclerView =
                (RecyclerView) inflater.inflate(R.layout.lin, container, false);
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated");
        times = 1;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));


        SharedPreferences p = getActivity().getSharedPreferences("overwall", MODE_PRIVATE);
        token = p.getString("token", "");
        getData();
    }

    void getData() {
        JsonObjectRequest getDataRequest = new JsonObjectRequest(
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

        App.mQueue.add(getDataRequest);

       /* new VolleyRequestUtil().RequestGet(getActivity(), NODE + token, "NODE",
                new VolleyListenerInterface(getActivity(), VolleyListenerInterface.mListener, VolleyListenerInterface.mErrorListener) {
                    // Volley请求成功时调用的函数
                    @Override
                    public void onMySuccess(String result) {
                        // Tools.showDebugToast("请求成功");
                         processNodeData(result);
                    }


                    // Volley请求失败时调用的函数
                    @Override
                    public void onMyError(VolleyError error) {
                        Tools.showToast(error.toString());
                        if (times < 4) {
                            getData();
                            times++;
                        }

                    }
                });*/
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


            Tools.showDebugToast("处理完了");
        } catch (Exception e) {
            Tools.showDebugToast(e.toString());
        }


        mRecyclerView.setAdapter(new MyAdapter(node));

    }


}

