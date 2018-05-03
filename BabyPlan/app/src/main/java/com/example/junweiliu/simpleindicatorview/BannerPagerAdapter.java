package com.example.junweiliu.simpleindicatorview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zh.babyplan.R;

import java.util.ArrayList;
import java.util.List;

public class BannerPagerAdapter extends PagerAdapter {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 图像列表
     */
    private List<Integer> pictureList = new ArrayList<>();
    /**
     * 默认轮播个数
     */
    public static final int FAKE_BANNER_SIZE = Integer.MAX_VALUE;

    public BannerPagerAdapter(Context context, List<Integer> pictureList) {
        this.mContext = context;
        this.pictureList = pictureList;
    }

    @Override
    public int getCount() {
        return FAKE_BANNER_SIZE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_page_imagescroll, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_banner_item);
        // 获取当前显示位置
        position %= pictureList.size();
        imageView.setImageResource(pictureList.get(position));
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
