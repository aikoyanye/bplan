package com.zjh.store;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.zh.babyplan.R;
import com.zjh.adapter.CollectAdapter;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends Activity implements CollectAdapter.ModifyCountInterface {
	private int[] collect_ItemImage=new int[] {R.mipmap.goods,R.mipmap.goods,R.mipmap.goods,R.mipmap.goods};
	private String[] collect_ItemName=new String[] {"舒适达牙膏1","舒适达牙膏2","舒适达牙膏3","舒适达牙膏4"};
	private String[] collect_ItemFeature=new String[] {"中国人都在用","美国人也在用","加拿大人也在用","地球人都用"};
	private double[] collect_ItemPrice=new double[] {8.00,80.00,88.00,18.00};
	ListView list_collect;
	private CollectAdapter collectAdapter;
	private List<CollectItem> collectItemList = new ArrayList<>();

	private ImageButton back;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collect);
		list_collect=(ListView) findViewById(R.id.collect_listview);
		back=(ImageButton) findViewById(R.id.collect_back);
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		initData();
	}
	protected void initData() {
		for(int i=0;i<collect_ItemImage.length;i++) {
			CollectItem collectItem=new CollectItem();
			collectItem.setItemimage(collect_ItemImage[i]);
			collectItem.setItemname(collect_ItemName[i]);
			collectItem.setItemfeature(collect_ItemFeature[i]);
			collectItem.setItemprice(collect_ItemPrice[i]);
			collectItemList.add(collectItem);
		}
		collectAdapter = new CollectAdapter(getApplicationContext());
		collectAdapter.setModifyCountInterface(this);
		list_collect.setAdapter(collectAdapter);
		collectAdapter.setCollectItemList(collectItemList);
	}
	public void childDelete(int position) {
		collectItemList.remove(position);
		collectAdapter.notifyDataSetChanged();
	}
}