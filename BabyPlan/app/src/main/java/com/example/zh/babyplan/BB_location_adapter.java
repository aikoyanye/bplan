package com.example.zh.babyplan;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 84481 on 2018/1/31.
 */

public class BB_location_adapter extends RecyclerView.Adapter<BB_location_adapter.ViewHolder> {

    private Context context;
    private List<BB_location> locations;

    public BB_location_adapter(List<BB_location> locations){
        this.locations = locations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.bb_location_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BB_location location = locations.get(position);
        holder.map_bb.setText(location.getMap_bb());
        holder.map_name.setText(location.getMap_name());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        //        ImageView fruitImage;
        TextView map_name, map_bb;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            map_bb = (TextView) view.findViewById(R.id.map_bb);
            map_name = (TextView) view.findViewById(R.id.map_name);
        }
    }
}
