package com.zjh.store;


import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.zh.babyplan.R;

public class CategoryGoodsGridViewActivity extends TabActivity{
	private ImageButton category_goods_gridview_back;
	private TabHost mTabHost;
	private TabWidget mTabWidget;
	private TextView category_goods_tv;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_goods_layout);
		//取得TabHost对象
		mTabHost=getTabHost();
		
		Intent intent=getIntent();
		String goodsClassify_Name=intent.getStringExtra("goodsClassify_name");
		
		category_goods_tv=(TextView) findViewById(R.id.category_goods_tv);
		category_goods_tv.setText(goodsClassify_Name);

		if(goodsClassify_Name.equals("精选推荐")) {
			/* 为TabHost添加标签 */
			//新建一个newTabSpec(newTabSpec)
			//设置其标签和图标(setIndicator)
			//设置内容(setContent)

//			Intent tabIntenet0=new Intent(CategoryGoodsGridViewActivity.this, CategoryGoodsGridViewTabActivity.class);
//			Bundle bundle0=new Bundle();
//			bundle0.putString("tabname0", "百元好物");
//			tabIntenet0.putExtras(bundle0);
			mTabHost.addTab(mTabHost.newTabSpec("first0")
					.setIndicator("百元好物")
					.setContent(new Intent(CategoryGoodsGridViewActivity.this, CategoryGoodsGridViewTabActivity.class)));


//			Intent tabIntenet1=new Intent(CategoryGoodsGridViewActivity.this, CategoryGoodsGridViewTabActivity.class);
//			Bundle bundle1=new Bundle();
//			bundle1.putString("tabname1", "本季热销");
//			tabIntenet0.putExtras(bundle1);
			mTabHost.addTab(mTabHost.newTabSpec("first1")
					.setIndicator("本季热销")
					.setContent(new Intent(CategoryGoodsGridViewActivity.this, CategoryGoodsGridViewTabActivity.class)));

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
		}
		else if(goodsClassify_Name.equals("童婴用品")) {
			/* 为TabHost添加标签 */
			//新建一个newTabSpec(newTabSpec)
			//设置其标签和图标(setIndicator)
			//设置内容(setContent)
			mTabHost.addTab(mTabHost.newTabSpec("tab_test1")
					.setIndicator("喂养用品")
					.setContent(new Intent(this,CategoryGoodsGridViewTabActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
					.setIndicator("清洁/洗护")
					.setContent(new Intent(this,CategoryGoodsGridViewTabActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
					.setIndicator("奶粉")
					.setContent(new Intent(this,CategoryGoodsGridViewTabActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
					.setIndicator("尿裤")
					.setContent(new Intent(this,CategoryGoodsGridViewTabActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
					.setIndicator("辅食")
					.setContent(new Intent(this,CategoryGoodsGridViewTabActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
					.setIndicator("营养品")
					.setContent(new Intent(this,CategoryGoodsGridViewTabActivity.class)));
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
		}
	}
}
