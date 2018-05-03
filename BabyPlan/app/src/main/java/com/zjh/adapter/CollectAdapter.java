package com.zjh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zh.babyplan.R;
import com.zjh.store.CollectItem;
import java.util.List;

public class CollectAdapter extends BaseAdapter{
	private List<CollectItem> collectItemList;
	private ModifyCountInterface modifyCountInterface;
	private Context context;
	public CollectAdapter(Context context) {
		this.context=context;
	}
	public void setCollectItemList(List<CollectItem> collectItemList) {
		this.collectItemList=collectItemList;
		notifyDataSetChanged();
	}
	public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
    	this.modifyCountInterface=modifyCountInterface;
    }
	public int getCount() {return collectItemList==null?0:collectItemList.size();}
	public Object getItem(int position) {return collectItemList.get(position);}
	public long getItemId(int position) {return position;}
	
	
	public View getView(final int position,View convertView,ViewGroup parent) {
		final ViewHolder holder;
		if(convertView==null) {
			convertView=LayoutInflater.from(context).inflate(R.layout.collect_listivew_layout, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		final CollectItem collectItem=collectItemList.get(position);
		holder.itemname.setText(collectItem.getItemname());
		holder.itemfeature.setText(collectItem.getItemfeature());
		holder.itemprice.setText(collectItem.getItemprice()+"");
		holder.itemimage.setImageResource(collectItem.getItemimage());
		holder.delete_item.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				modifyCountInterface.childDelete(position);
			}
		});
		return convertView;
	}
	
	//��ʼ���ؼ�
	class ViewHolder{
		ImageView itemimage,delete_item;
		TextView itemname,itemfeature,itemprice;
		public ViewHolder(View itemView) {
			itemimage=(ImageView) itemView.findViewById(R.id.collect_item_imageview);
			delete_item=(ImageView) itemView.findViewById(R.id.collect_delete_item);
			itemname=(TextView) itemView.findViewById(R.id.collect_item_name);
			itemfeature=(TextView) itemView.findViewById(R.id.collect_item_feature);
			itemprice=(TextView) itemView.findViewById(R.id.collect_item_price);
		}
	}
	public interface ModifyCountInterface{
		void childDelete(int position);
	}
}
