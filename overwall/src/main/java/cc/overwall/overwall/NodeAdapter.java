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
import cc.overwall.overwall.Utils.Tools;

import static android.content.Context.MODE_PRIVATE;

public class NodeAdapter extends RecyclerView.Adapter {
    public static final String TAG = "NodeAdapter";
    private ArrayList<Node> node;

    public ArrayList<Node> getNode() {
        return node;
    }

    public void setNode(ArrayList<Node> node) {
        this.node = node;
    }


    public NodeAdapter(ArrayList<Node> node) {
        this.node = node;
    }

    class MyVH extends RecyclerView.ViewHolder {

        private View root;

        private ImageView img_city;
        private Button listButtonConnect;
        private TextView listTitle;
        private TextView listSpeed;
        private TextView listInfo;
        private TextView listBottom, listOnline;


        public MyVH(View root) {
            super(root);
            this.root = root;
            img_city = (ImageView) root.findViewById(R.id.img_city);
            listButtonConnect = (Button) root.findViewById(R.id.list_Button_connect);
            listTitle = (TextView) root.findViewById(R.id.list_title);
            listSpeed = (TextView) root.findViewById(R.id.list_speed);
            listInfo = (TextView) root.findViewById(R.id.list_info);
            listBottom = (TextView) root.findViewById(R.id.list_bottom);
            listOnline = (TextView) root.findViewById(R.id.list_online);

        }

        public ImageView getImg_city() {
            return img_city;
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

        public TextView getListOnline() {
            return listOnline;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.list_item_card_node, parent, false);
        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyVH vh = (MyVH) holder;
        final int pos = position;
        vh.getListTitle().setText(node.get(position).getName());
        vh.getListSpeed().setText(node.get(position).getServer());
        vh.getListBottom().setText(node.get(position).getInfo());
        vh.getListOnline().setText("当前连接人数:" + node.get(position).getOnline());
        vh.getListInfo().setText(node.get(pos).getStatus());
        setCityImg(vh, position);
        vh.getListButtonConnect().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (node.get(pos).getNode_class() > App.preferenceR.getInt("user_class", 0)) {

                    Tools.showmySnack(vh.root, "等级不够哦亲╭(╯3╰)╮,去右边商店升级试试");
                } else {
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
            }
        });

    }

    private void setCityImg(MyVH vh, int position) {
        ImageView img = vh.getImg_city();
        String city = node.get(position).getName();
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        if (city.contains("日本"))
            img.setImageResource(R.drawable.city_jp);
        if (city.contains("台湾"))
            img.setImageResource(R.drawable.city_tw);
        if (city.contains("美国"))
            img.setImageResource(R.drawable.city_us);
        if (city.contains("韩国"))
            img.setImageResource(R.drawable.city_kr);
        if (city.contains("香港"))
            img.setImageResource(R.drawable.city_hk);
        if (city.contains("奥地利"))
            img.setImageResource(R.drawable.city_at);
        if (city.contains("俄罗斯"))
            img.setImageResource(R.drawable.city_ru);
        if (city.contains("中国"))
            img.setImageResource(R.drawable.city_cn);

    }

    @Override
    public int getItemCount() {
        if (node.size() != 0)
            return node.size();
        else
            return 1;
    }
}
