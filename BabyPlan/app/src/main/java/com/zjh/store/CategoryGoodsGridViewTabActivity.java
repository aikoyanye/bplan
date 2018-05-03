package com.zjh.store;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.zh.babyplan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryGoodsGridViewTabActivity extends Activity{
	int[] category_goods_images=new int[] {R.mipmap.goods,R.mipmap.goods,R.mipmap.goods,R.mipmap.goods,R.mipmap.goods,R.mipmap.goods,R.mipmap.goods};
	String[] category_goods_features=new String[] {"不用手洗就能洗干净","有了它，宝宝不在吵闹","你好我好大家好","清明节快乐啊啊啊","重阳节快乐啊啊啊","妈妈再也不用担心我的学习","哪里不会点哪里"};
	String[] category_goods_names=new String[] {"宝宝精水0","宝宝精水1","宝宝精水2","宝宝精水3","宝宝精水4","宝宝精水5","宝宝精水6"};
	String[] category_goods_prices=new String[] {"10.0","20.0","30.0","40.0","50.0","60.0","70.0"};
//	int[] category_goods_images1=new int[] {R.mipmap.brand,R.mipmap.brand,R.mipmap.goods,R.mipmap.goods,R.mipmap.goods,R.mipmap.goods,R.mipmap.goods};
//	String[] category_goods_features1=new String[] {"不用手洗就能洗干净","有了它，宝宝不在吵闹","你好我好大家好","清明节快乐啊啊啊","重阳节快乐啊啊啊","妈妈再也不用担心我的学习","哪里不会点哪里"};
//	String[] category_goods_names1=new String[] {"宝宝精水0","宝宝精水1","宝宝精水2","宝宝精水3","宝宝精水4","宝宝精水5","宝宝精水6"};
//	String[] category_goods_prices1=new String[] {"10.0","20.0","30.0","40.0","50.0","60.0","70.0"};

//	Intent intent1=getIntent();
//	String tabhost1=intent1.getStringExtra("tabhost1");

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_goods_gridview);

//		Intent intent0=getIntent();
//		Bundle bundle0=intent0.getExtras();
//		String tabname0=bundle0.getString("tabname0");
//
//		Intent intent1=getIntent();
//		Bundle bundle1=intent1.getExtras();
//		String tabname1=bundle1.getString("tabname1");

		GridView gridView=(GridView) findViewById(R.id.category_goods_gridview);
		List<Map<String, Object>> category_goods_list=new ArrayList<Map<String,Object>>();

//		if(tabname0.equals("百元好物")) {
		for(int i=0;i<category_goods_images.length;i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("category_goods_image", category_goods_images[i]);
			map.put("category_goods_feature", category_goods_features[i]);
			map.put("category_goods_name", category_goods_names[i]);
			map.put("category_goods_price", category_goods_prices[i]);
			category_goods_list.add(map);
		}
		SimpleAdapter category_goods_Adapter=new SimpleAdapter(this,category_goods_list,R.layout.category_goods_gridview_item,new String[] {"category_goods_image","category_goods_feature","category_goods_name","category_goods_price"},new int[] {R.id.goods_category_gridview_item_imageview,R.id.goods_category_gridview_item_feature,R.id.goods_category_gridview_item_name,R.id.goods_category_gridview_item_price});
		gridView.setAdapter(category_goods_Adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), category_goods_names[position], Toast.LENGTH_SHORT).show();
			}
		});
//		}
//		else if(tabname1.equals("本季热销")) {
//			for(int i=0;i<category_goods_images1.length;i++) {
//		    	Map<String, Object> map=new HashMap<String, Object>();
//		    	map.put("category_goods_image", category_goods_images1[i]);
//		    	map.put("category_goods_feature", category_goods_features1[i]);
//		    	map.put("category_goods_name", category_goods_names1[i]);
//		    	map.put("category_goods_price", category_goods_prices1[i]);
//		    	category_goods_list.add(map);
//			}
//			SimpleAdapter category_goods_Adapter=new SimpleAdapter(this,category_goods_list,R.layout.category_goods_gridview_item,new String[] {"category_goods_image","category_goods_feature","category_goods_name","category_goods_price"},new int[] {R.id.goods_category_gridview_item_imageview,R.id.goods_category_gridview_item_feature,R.id.goods_category_gridview_item_name,R.id.goods_category_gridview_item_price});
//			gridView.setAdapter(category_goods_Adapter);
//			gridView.setOnItemClickListener(new OnItemClickListener() {
//				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//					Toast.makeText(getApplicationContext(), category_goods_names1[position], Toast.LENGTH_SHORT).show();
//				}
//			});
//		}

	}
}
