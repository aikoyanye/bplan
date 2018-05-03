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
 * Created by 84481 on 2018/3/6.
 * 查看宝宝的流式布局适配器
 */

public class BBAdapter extends RecyclerView.Adapter<BBAdapter.ViewHolder>{

    private Context mContext;
    private List<BB> mBBList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView b_name, b_birthday, b_sex;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            b_name = (TextView)view.findViewById(R.id.b_name);
            b_birthday = (TextView)view.findViewById(R.id.b_birthday);
            b_sex = (TextView)view.findViewById(R.id.b_sex);
        }
    }

    public BBAdapter(List<BB> list){
        this.mBBList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.bb_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BBAdapter.ViewHolder holder, int position) {
        BB bb = mBBList.get(position);
        holder.b_birthday.setText(bb.getBirthday());
        holder.b_sex.setText(bb.getSex());
        holder.b_name.setText(bb.getB_name());
    }

    @Override
    public int getItemCount() {
        return mBBList.size();
    }
}
