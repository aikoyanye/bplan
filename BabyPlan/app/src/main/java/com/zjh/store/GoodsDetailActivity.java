package com.zjh.store;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.junweiliu.simpleindicatorview.BannerPagerAdapter2;
import com.example.junweiliu.simpleindicatorview.IndicatorView;
import com.example.zh.babyplan.R;
import com.zjh.adapter.SkuAdapter;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailActivity extends TabActivity{
	/**
	 * 轮播图
	 */
	private ViewPager mViewPager;
	/**
	 * 指示器
	 */
	private IndicatorView mIndicatorView;
	/**
	 * 适配器
	 */
	private BannerPagerAdapter2 mBannerPagerAdapter;
	/**
	 * 图片资源
	 */
	private List<Integer> pictureList = new ArrayList<>();
	/**
	 * 当前轮播图位置
	 */
	private int mBannerPosition;

	ImageButton goods_detail_back;
	TabHost mTabHost;
	TabWidget mTabWidget=null;

	TextView old_price;

	//点击选择商品属性popupwindow
	private LinearLayout firstView;
	private RelativeLayout secondView;
	private int fHeight;
	private int sHeight;
	private TextView mShowAttr;
	private Button mSaveAttr;

	//商品属性部分
	List<SkuItem> skuList;//sku数据
	List<GoodsDetailAttrBean> mColorList;//颜色列表
	List<GoodsDetailAttrBean> mSizeList;//尺码列表
	GridView color_gridView;//颜色
	GridView size_gridView;//尺码
	SkuAdapter skuColorAdapter;//颜色适配器
	SkuAdapter skuSizeAdapter;//尺码适配器
	String color;
	String size;
	TextView skuName;//显示sku

	int[] scroll_image=new int[] {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_detail);

		old_price=(TextView) findViewById(R.id.goods_old_price);
		old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

		//顶部轮播图
		initDatas();
		initHomeView();
		goods_detail_back=(ImageButton) findViewById(R.id.goods_detail_back);
		goods_detail_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		//取得TabHost对象
		mTabHost=getTabHost();
		/* 为TabHost添加标签 */
		//新建一个newTabSpec(newTabSpec)
		//设置其标签和图标(setIndicator)
		//设置内容(setContent)
		mTabHost.addTab(mTabHost.newTabSpec("tab_test1")
				.setIndicator("图文详情")
				.setContent(new Intent(this,GoodsDetailGraphicDetailsActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
				.setIndicator("品牌描述")
				.setContent(new Intent(this,GoodsDetailBrandDescriptionActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("tab_test3")
				.setIndicator("评价")
				.setContent(new Intent(this,GoodsDetailEvaluationActivity.class)));
		mTabWidget = mTabHost.getTabWidget();
		mTabWidget.setStripEnabled(false);
		for(int i=0;i<mTabWidget.getChildCount();i++){
			mTabWidget.getChildAt(i).getLayoutParams().height = 60;
			//换字体颜色
			TextView tv = (TextView)
					mTabWidget.getChildAt(i).findViewById(android.R.id.title);
			tv.setTextColor(Color.rgb(255, 255, 255));
			//设置背景图
			mTabWidget.getChildAt(i).setBackgroundResource(
					R.drawable.tabwidget_selector);
		}

		//点击选择商品属性popupwindow
		firstView=(LinearLayout) findViewById(R.id.goods_view);
		secondView=(RelativeLayout) findViewById(R.id.goods_detail_second_view);
		mShowAttr=(TextView) findViewById(R.id.select_goods_attrs);
		mSaveAttr=(Button) findViewById(R.id.save_attr);
		firstView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			public void onGlobalLayout() {
				fHeight = firstView.getHeight();
				firstView.getViewTreeObserver()
						.removeGlobalOnLayoutListener(this);
			}
		});
		secondView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			public void onGlobalLayout() {
				sHeight = secondView.getHeight();
				secondView.getViewTreeObserver()
						.removeGlobalOnLayoutListener(this);
			}
		});
		mShowAttr.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				initShowAnim();
			}
		});
		mSaveAttr.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				initHiddenAnim();
                mShowAttr.setText("已选择："+color+" "+size);
                mShowAttr.setTextColor(Color.RED);
			}
		});


		//商品属性部分
		color_gridView=(GridView) findViewById(R.id.attr_goods_color);
		size_gridView=(GridView) findViewById(R.id.attr_goods_size);
		skuName=(TextView) findViewById(R.id.goods_sku);
		addData();
		skuColorAdapter=new SkuAdapter(mColorList, this);
		color_gridView.setAdapter(skuColorAdapter);
		skuColorAdapter.setItemClickListener(new SkuAdapter.onItemClickListener() {
			public void onItemClick(GoodsDetailAttrBean bean, int position) {
				color=bean.getName();
				switch (bean.getStates()) {
					case "0":
						//清空尺码
						mSizeList=GoodsDetailAttrDataUtil.clearAdapterStates(mSizeList);
						skuSizeAdapter.notifyDataSetChanged();
						//清空颜色
						mColorList=GoodsDetailAttrDataUtil.clearAdapterStates(mColorList);
						skuColorAdapter.notifyDataSetChanged();
						color="";
						//判断使用选中了尺码
						if(!TextUtils.isEmpty(size)) {
							skuName.setText("请选择尺码");
							//获取改尺码对应的颜色列表
							List<String> list=GoodsDetailAttrDataUtil.getColorListBySize(skuList, size);
							if(list!=null&&list.size()>0) {
								//更新颜色列表
								mColorList=GoodsDetailAttrDataUtil.setSizeOrColorListStates(mColorList, list, color);
								skuColorAdapter.notifyDataSetChanged();
							}
							mSizeList=GoodsDetailAttrDataUtil.setAdapterStates(mSizeList, size);
							skuSizeAdapter.notifyDataSetChanged();
						}else {
							skuName.setText("请选择尺码，颜色分类");
						}
						break;

					case "1":
						//选中颜色
						mColorList=GoodsDetailAttrDataUtil.updateAdapterStates(mColorList, "0", position);
						skuColorAdapter.notifyDataSetChanged();
						//计算改颜色对应的尺码列表
						List<String> list=GoodsDetailAttrDataUtil.getSizeListByColor(skuList, color);
						if(!TextUtils.isEmpty(size)) {
							//计算改颜色与尺码对应的库存
							skuName.setText("规格："+color+" "+size);
							if(list!=null&&list.size()>0) {
								//更新尺码列表
								mSizeList=GoodsDetailAttrDataUtil.setSizeOrColorListStates(mSizeList, list, size);
								skuSizeAdapter.notifyDataSetChanged();
							}
						}else {
							skuName.setText("请选择尺码");
							if(list!=null&&list.size()>0) {
								//更新尺码列表
								mSizeList=GoodsDetailAttrDataUtil.setSizeOrColorListStates(mSizeList, list, "");
								skuSizeAdapter.notifyDataSetChanged();
							}
						}
						break;
					default:
						break;
				}
			}
		});
		skuSizeAdapter=new SkuAdapter(mSizeList, this);
		size_gridView.setAdapter(skuSizeAdapter);
		skuSizeAdapter.setItemClickListener(new SkuAdapter.onItemClickListener() {
			public void onItemClick(GoodsDetailAttrBean bean, int position) {
				size=bean.getName();
				switch (bean.getStates()) {
					case "0":
						//清空尺码
						mSizeList=GoodsDetailAttrDataUtil.clearAdapterStates(mSizeList);
						skuSizeAdapter.notifyDataSetChanged();
						//清空颜色
						mColorList=GoodsDetailAttrDataUtil.clearAdapterStates(mColorList);
						skuColorAdapter.notifyDataSetChanged();
						size="";
						if(!TextUtils.isEmpty(color)) {
							skuName.setText("请选择尺码");
							//计算改颜色对应的尺码列表
							List<String> list=GoodsDetailAttrDataUtil.getSizeListByColor(skuList, color);
							if(list!=null&&list.size()>0) {
								//更新尺码列表
								mSizeList=GoodsDetailAttrDataUtil.setSizeOrColorListStates(mSizeList, list, size);
								skuSizeAdapter.notifyDataSetChanged();
							}
							mColorList=GoodsDetailAttrDataUtil.setAdapterStates(mColorList, color);
							skuColorAdapter.notifyDataSetChanged();
						}else {
							skuName.setText("请选择尺码，颜色分类");
						}
						break;
					case "1":
						//选中尺码
						mSizeList=GoodsDetailAttrDataUtil.updateAdapterStates(mSizeList, "0", position);
						skuSizeAdapter.notifyDataSetChanged();
						//获取改尺码对应的颜色列表
						List<String> list=GoodsDetailAttrDataUtil.getColorListBySize(skuList, size);
						if(!TextUtils.isEmpty(color)) {
							skuName.setText("规格："+color+" "+size);
							if(list!=null&&list.size()>0) {
								//更新颜色列表
								mColorList=GoodsDetailAttrDataUtil.setSizeOrColorListStates(mColorList, list, color);
								skuColorAdapter.notifyDataSetChanged();
							}
						}else {
							skuName.setText("请选择颜色分类");
							if(list!=null&&list.size()>0) {
								mColorList=GoodsDetailAttrDataUtil.setSizeOrColorListStates(mColorList, list, "");
								skuColorAdapter.notifyDataSetChanged();
							}
						}
						break;
					default:
						break;
				}
			}
		});
	}
	/**
	 * 初始化数据
	 */
	private void initDatas() {
		for(int i=0;i<scroll_image.length;i++) {
			pictureList.add(scroll_image[i]);
		}
	}
	/**
	 * 初始化控件
	 */
	private void initHomeView() {
		mViewPager = (ViewPager) findViewById(R.id.goods_vp_banner);
		mIndicatorView = (IndicatorView) findViewById(R.id.goods_idv_banner);
		mBannerPagerAdapter = new BannerPagerAdapter2(this, pictureList);
		mViewPager.setAdapter(mBannerPagerAdapter);
		mIndicatorView.setViewPager(pictureList.size(), mViewPager);
		// 设置默认起始位置,使开始可以向左边滑动
		mViewPager.setCurrentItem(pictureList.size() -pictureList.size());
		mIndicatorView.setOnPageChangeListener(new IndicatorView.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}
			@Override
			public void onPageSelected(int position) {
			}
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
		//由于ViewPager 没有点击事件，可以通过对VIewPager的setOnTouchListener进行监听已达到实现点击事件的效果
		mViewPager.setOnTouchListener(new OnTouchListener() {
			int flage = 0 ;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()){
					case MotionEvent.ACTION_DOWN:
						flage = 0 ;
						break ;
					case MotionEvent.ACTION_MOVE:
						flage = 1 ;
						break ;
					case  MotionEvent.ACTION_UP :
						if (flage == 0) {
						}
						break ;
				}
				return false;
			}
		});
	}


	private void initShowAnim(){
		ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(firstView,"scaleX",1.0f,0.8f);
		fViewScaleXAnim.setDuration(350);
		ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(firstView,"scaleY",1.0f,0.8f);
		fViewScaleYAnim.setDuration(350);
		ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(firstView,"alpha",1.0f,0.5f);
		fViewAlphaAnim.setDuration(350);
		ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 0f, 10f);
		fViewRotationXAnim.setDuration(200);
		ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 10f, 0f);
		fViewResumeAnim.setDuration(150);
		fViewResumeAnim.setStartDelay(200);
		ObjectAnimator fViewTransYAnim=ObjectAnimator.ofFloat(firstView,"translationY",0,-0.1f* fHeight);
		fViewTransYAnim.setDuration(350);
		ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(secondView,"translationY",sHeight,0);
		sViewTransYAnim.setDuration(350);
		sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				secondView.setVisibility(View.VISIBLE);
			}
		});
		AnimatorSet showAnim=new AnimatorSet();
		showAnim.playTogether(fViewScaleXAnim,fViewRotationXAnim,fViewResumeAnim,fViewTransYAnim,fViewAlphaAnim,fViewScaleYAnim,sViewTransYAnim);
		showAnim.start();
	}

	private void initHiddenAnim(){
		ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(firstView,"scaleX",0.8f,1.0f);
		fViewScaleXAnim.setDuration(350);
		ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(firstView,"scaleY",0.8f,1.0f);
		fViewScaleYAnim.setDuration(350);
		ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(firstView,"alpha",0.5f,1.0f);
		fViewAlphaAnim.setDuration(350);
		ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 0f, 10f);
		fViewRotationXAnim.setDuration(200);
		ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 10f, 0f);
		fViewResumeAnim.setDuration(150);
		fViewResumeAnim.setStartDelay(200);
		ObjectAnimator fViewTransYAnim=ObjectAnimator.ofFloat(firstView,"translationY",-0.1f* fHeight,0);
		fViewTransYAnim.setDuration(350);
		ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(secondView,"translationY",0,sHeight);
		sViewTransYAnim.setDuration(350);
		sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				secondView.setVisibility(View.GONE);
			}
		});
		AnimatorSet showAnim=new AnimatorSet();
		showAnim.playTogether(fViewScaleXAnim,fViewRotationXAnim,fViewResumeAnim,fViewTransYAnim,fViewAlphaAnim,fViewScaleYAnim,sViewTransYAnim);
		showAnim.start();
	}

	//初始化商品属性
	private void addData() {
		skuList=new ArrayList<SkuItem>();
		mColorList=new ArrayList<GoodsDetailAttrBean>();
		mSizeList=new ArrayList<GoodsDetailAttrBean>();
		String[] colorAttr={"粉色","黄色","绿色","红色","紫色"};
		String[] sizeAttr= {"m","s","xs","l","xl","xxl"};
		for(int i=0;i<colorAttr.length;i++) {
			GoodsDetailAttrBean bean=new GoodsDetailAttrBean();
			bean.setName(colorAttr[i]);
			bean.setStates("1");
			mColorList.add(bean);
		}
		for(int i=0;i<sizeAttr.length;i++) {
			GoodsDetailAttrBean bean=new GoodsDetailAttrBean();
			bean.setName(sizeAttr[i]);
			bean.setStates("1");
			mSizeList.add(bean);
		}
		for(int i=0;i<sizeAttr.length;i++) {
			for(int j=0;j<colorAttr.length;j++) {
				SkuItem item=new SkuItem(sizeAttr[i], colorAttr[j]);
				skuList.add(item);
			}
		}
	}
}
