package com.zjh.store;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.example.zh.babyplan.R;

import java.util.ArrayList;
import java.util.List;

public class AllIndentActivity extends TabActivity{
	TabHost mTabHost;
	List<ImageView> imageList = new ArrayList<ImageView>();
	ImageButton back;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allindent_main);
		mTabHost=getTabHost();

		mTabHost.addTab(mTabHost.newTabSpec("all_indent")
				.setIndicator(composeLayout("全部",0))
				.setContent(new Intent(this,All_Indent_Activity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("wait_for_payment")
				.setIndicator(composeLayout("待付款", 0))
				.setContent(new Intent(this,Wait_For_Payment_Activity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("wait_for_sending")
				.setIndicator(composeLayout("待发货", 0))
				.setContent(new Intent(this,Wait_For_Sending_Activity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("wait_for_goods")
				.setIndicator(composeLayout("待收货", 0))
				.setContent(new Intent(this,Wait_For_Goods_Activity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("wait_for_evaluate")
				.setIndicator(composeLayout("待评价", 0))
				.setContent(new Intent(this,Wait_For_Evaluate_Activity.class)));

		setTab(0);
		
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				imageList.get(0).setImageDrawable(null);
				imageList.get(1).setImageDrawable(null);
				imageList.get(2).setImageDrawable(null);
				imageList.get(3).setImageDrawable(null);
				imageList.get(4).setImageDrawable(null);
				if(tabId.equalsIgnoreCase("all_indent")) {
					imageList.get(0).setImageDrawable(getResources().getDrawable(R.drawable.line));
				} else if(tabId.equalsIgnoreCase("wait_for_payment")) {
					imageList.get(1).setImageDrawable(getResources().getDrawable(R.drawable.line));
				} else if(tabId.equalsIgnoreCase("wait_for_sending")) {
					imageList.get(2).setImageDrawable(getResources().getDrawable(R.drawable.line));
				} else if(tabId.equalsIgnoreCase("wait_for_goods")) {
					imageList.get(3).setImageDrawable(getResources().getDrawable(R.drawable.line));
				} else if(tabId.equalsIgnoreCase("wait_for_evaluate")) {
					imageList.get(4).setImageDrawable(getResources().getDrawable(R.drawable.line));
				}
			}
		});
		
		back=(ImageButton) findViewById(R.id.allindent_back);
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
	public View composeLayout(String s,int i) {
		RelativeLayout layout=new RelativeLayout(this);
		layout.setGravity(Gravity.CENTER_VERTICAL);
		
		TextView tv=new TextView(this);
		tv.setGravity(Gravity.CENTER);
		tv.setSingleLine(true);
		tv.setText(s);
		tv.setTextColor(Color.parseColor("#303030"));
		tv.setTextSize(14);
		
		ImageView iv=new ImageView(this);
		imageList.add(iv);
		iv.setImageResource(i);
		
		int textWidth = (int) android.text.Layout.getDesiredWidth(tv.getText(), tv.getPaint());
		
		RelativeLayout.LayoutParams imageviewLP=new RelativeLayout.LayoutParams(textWidth+2, 3);
		imageviewLP.addRule(RelativeLayout.CENTER_HORIZONTAL);
		imageviewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		imageviewLP.setMargins(0, 0, 0, 10);
		layout.addView(iv, imageviewLP);
		
		RelativeLayout.LayoutParams textviewLP=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		textviewLP.addRule(RelativeLayout.CENTER_VERTICAL);
		textviewLP.addRule(RelativeLayout.CENTER_HORIZONTAL);
		textviewLP.setMargins(0, 31, 0, 0);
		layout.addView(tv, textviewLP);
		return layout;
	}
	public void setTab(int i) {
		mTabHost.setCurrentTab(i);
		imageList.get(i).setImageResource(R.drawable.line);
	}
}
