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
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context mContext;
    private List<Comment> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView c_name, c_content, c_created;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            c_name = (TextView)view.findViewById(R.id.c_name);
            c_content = (TextView)view.findViewById(R.id.c_content);
            c_created = (TextView)view.findViewById(R.id.c_created);
        }
    }

    public CommentAdapter(List<Comment> list){
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = list.get(position);
        holder.c_name.setText(comment.getName());
        holder.c_created.setText(comment.getCreated());
        holder.c_content.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
