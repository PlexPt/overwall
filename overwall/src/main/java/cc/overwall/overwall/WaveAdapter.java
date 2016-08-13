package cc.overwall.overwall;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cc.overwall.overwall.ui.WaveView;

/**
 * Created by pt on 16-8-13.
 */

public class WaveAdapter extends RecyclerView.Adapter {
    
    class ViewHolder extends RecyclerView.ViewHolder{
        
        private View itemView;
        private WaveView wave;
        private TextView textLiuliang;
        
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            wave = (WaveView) itemView.findViewById(R.id.wave);
            textLiuliang = (TextView) itemView.findViewById(R.id.text_liuliang);

        }

        public TextView getTextLiuliang() {
            return textLiuliang;
        }

        public WaveView getWave() {
            return wave;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_wave, parent, false);
        return new WaveAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    
}
