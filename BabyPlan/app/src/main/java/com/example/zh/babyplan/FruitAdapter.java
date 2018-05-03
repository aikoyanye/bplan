package com.example.zh.babyplan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zh.babyplan.Util.CircleImageView;
import com.example.zh.babyplan.Util.FakeDataBase;
import com.loopj.android.http.Base64;

import java.util.List;

/**
 * Created by ZH on 2018/1/17.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
//        ImageView fruitImage;
        TextView fruitName, fruitProblem, fruitProblemContent, p_name, created, support;
        ImageView iv;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
//            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
            fruitProblem = (TextView) view.findViewById(R.id.fruit_problem);
            fruitProblemContent = (TextView) view.findViewById(R.id.fruit_problem_content);
            p_name = (TextView) view.findViewById(R.id.p_name);
            created = (TextView) view.findViewById(R.id.created);
            support = (TextView) view.findViewById(R.id.support_num);
            iv = (CircleImageView) view.findViewById(R.id.head_iv);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        holder.fruitProblem.setText(fruit.getProblem());
        holder.p_name.setText(fruit.getP_name());
        holder.created.setText(deal_string(fruit.getCreated()));
        holder.fruitProblemContent.setText(fruit.getProblemContent());
        holder.support.setText(fruit.getSupport_num());
        if(!fruit.getHead().equals("null")){
            byte[] bs = Base64.decode(fruit.getHead(), 100);
            holder.iv.setImageBitmap(zoomBitmap(BitmapFactory.decodeByteArray(bs, 0, bs.length), 70, 70));
        }
//        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    private String deal_string(String data){
        String l = data.substring(0, data.indexOf("T"));
        String r = data.substring(data.indexOf("T")+1, data.indexOf("."));
        return l + " " + r;
    }
}
