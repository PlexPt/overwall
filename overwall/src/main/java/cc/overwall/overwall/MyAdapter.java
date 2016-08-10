package cc.overwall.overwall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cc.overwall.overwall.Bean.Node;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by xps on 2016/7/19.
 */
public class MyAdapter extends RecyclerView.Adapter {
    public static final String TAG =
            "MyAdapter";
    private ArrayList<Node> node;

    public MyAdapter(ArrayList<Node> node) {
        this.node = node;
    }

    class MyVH extends RecyclerView.ViewHolder {

        private View root;


        private ImageView listitemcardmainImageView1;
        private Button listButtonConnect;
        private TextView listTitle;
        private TextView listSpeed;
        private TextView listInfo;
        private TextView listBottom;


        public MyVH(View root) {
            super(root);
            listitemcardmainImageView1 = (ImageView) root.findViewById(R.id.listitemcardmainImageView1);
            listButtonConnect = (Button) root.findViewById(R.id.list_Button_connect);
            listTitle = (TextView) root.findViewById(R.id.list_title);
            listSpeed = (TextView) root.findViewById(R.id.list_speed);
            listInfo = (TextView) root.findViewById(R.id.list_info);
            listBottom = (TextView) root.findViewById(R.id.list_bottom);

        }

        public ImageView getListitemcardmainImageView1() {
            return listitemcardmainImageView1;
        }

        public Button getListButtonConnect() {
            return listButtonConnect;
        }

        public TextView getListTitle() {
            return listTitle;
        }

        public TextView getListSpeed() {
            return listSpeed;
        }

        public TextView getListInfo() {
            return listInfo;
        }

        public TextView getListBottom() {
            return listBottom;
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // return new MyVH(new TextView(parent.getContext()));
      //  Log.e(TAG, "onCreateViewHolder");
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_main, parent, false);
        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

       // Log.e(TAG, "onBindViewHolder");
        MyVH vh = (MyVH) holder;
        final int pos = position;
        vh.getListTitle().setText(node.get(position).getName());
        vh.getListSpeed().setText(node.get(position).getServer());
        vh.getListBottom().setText(node.get(position).getInfo());
        vh.getListButtonConnect().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SharedPreferences p = App.getContext().getSharedPreferences("overwall", MODE_PRIVATE);
                String method = p.getString("method", "");
                int port = p.getInt("port", 0);
                String passwd = p.getString("passwd", "");
                String all = method + ":" + passwd + "@" + node.get(pos).getServer() + ":" + port;
                String all_encode = Base64.encodeToString(all.getBytes(), Base64.DEFAULT);
                Log.e("加密后", all_encode);
                String ss = "ss://" + all_encode;
                intent.setData(Uri.parse(ss));
              //  Log.e(TAG,ss);
                App.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (node.size()!=0)
        return node.size();
        else 
            return 1;
    }
}
