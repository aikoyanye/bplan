package com.zjh.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zh.babyplan.R;
import com.zjh.store.BitmapUtil;

public class NewGoodsHorizontalListViewAdapter extends BaseAdapter{
	private int[] mIconIDs;
	private String[] mNames;
	private String[] mPrices;
	private Context mContext;
	private LayoutInflater mInflater;
	Bitmap iconBitmap;
	private int selectIndex=-1;
	public NewGoodsHorizontalListViewAdapter(Context context,int[] ids,String[] names,String[] prices) {
		this.mContext=context;
		this.mIconIDs=ids;
		this.mNames=names;
		this.mPrices=prices;
		mInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public int getCount() {
		return mIconIDs.length;
	}
	public Object getItem(int position) {
		return position;
	}
	public long getItemId(int position) {
		return position;
	}
	public View getView(int position,View convertView,ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null) {
			holder=new ViewHolder();
			convertView=mInflater.inflate(R.layout.newgoods_horizontallistview_layout, null);
			holder.mImage=(ImageView) convertView.findViewById(R.id.new_ItemImage);
			holder.mName=(TextView) convertView.findViewById(R.id.new_ItemName);
			holder.mPrice=(TextView) convertView.findViewById(R.id.new_ItemPrice);
			convertView.setTag(holder);
		}
		else {
			holder=(ViewHolder)convertView.getTag();
		}
		if(position==selectIndex) {
			convertView.setSelected(true);
		}else {
			convertView.setSelected(false);
		}
		iconBitmap=getPropThumnail(mIconIDs[position]);
		holder.mImage.setImageBitmap(iconBitmap);
		holder.mName.setText(mNames[position]);
		holder.mPrice.setText("￥"+mPrices[position]);
		return convertView;
	}
	private static class ViewHolder{
		private ImageView mImage;
		private TextView mName;
		private TextView mPrice;
	}
	private Bitmap getPropThumnail(int id) {
		Drawable d=mContext.getResources().getDrawable(id);
		Bitmap b = BitmapUtil.drawableToBitmap(d);
//		Bitmap bb = BitmapUtil.getRoundedCornerBitmap(b, 100);
		int w = mContext.getResources().getDimensionPixelOffset(R.dimen.thumnail_default_width);
		int h = mContext.getResources().getDimensionPixelSize(R.dimen.thumnail_default_height);
		
		Bitmap thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);
		
		return thumBitmap;
	}
	public void setSelectIndex(int i){
		selectIndex = i;
	}
}
