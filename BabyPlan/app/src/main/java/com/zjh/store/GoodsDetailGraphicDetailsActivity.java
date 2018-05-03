package com.zjh.store;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.zh.babyplan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GoodsDetailGraphicDetailsActivity extends Activity{
	private ListView goods_detail_graphic_details_listview;
	private int[] image=new int[] {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_detail_graphic_details);
		goods_detail_graphic_details_listview=(ListView) findViewById(R.id.goods_detail_graphic_details_listview);
		ArrayList<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for(int i=0;i<image.length;i++) {
			Map map = new HashMap<String, Object>();
			map.put("image", image[i]);
			listItems.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems, R.layout.goods_detail_graphic_details_listview_layout,new String[] {"image"},new int[] {R.id.goods_detail_graphic_details_image});
		goods_detail_graphic_details_listview.setAdapter(simpleAdapter);
		setListViewHeightBasedOnChildren(goods_detail_graphic_details_listview);
	}
	//由于listview在scrollview中只能显示一项，所以要动态计算listview的height进行动态设置
	public void setListViewHeightBasedOnChildren(ListView listView) {
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
			// listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			// 计算子项View 的宽高
			listItem.measure(0, 0);
			// 统计所有子项的总高度
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}
}
