package com.zjh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zh.babyplan.R;
import com.zjh.store.AddressItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class AddressAdapter extends BaseAdapter{
	private Context mContext;
	private ModifyCountInterface modifyCountInterface;
	private ModifyAddressInterface modifyAddressInterface;
	private List<AddressItem> addressItemList=new ArrayList();
	public AddressAdapter(Context context) {
		mContext=context;
	}
	public void setAddressItemList(List<AddressItem> addressItemList) {
		this.addressItemList=addressItemList;
		notifyDataSetChanged();
	}
	public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
    	this.modifyCountInterface=modifyCountInterface;
    }
	public void setModifyAddressInterface(ModifyAddressInterface modifyAddressInterface) {
		this.modifyAddressInterface=modifyAddressInterface;
	}
	public void setDatas(List datas) {
		addressItemList.addAll(datas);
		notifyDataSetChanged();
	}
	public int getCount() {
		return addressItemList.size();
	}
	public AddressItem getItem(int position) {
		return addressItemList.get(position);
	}
	public long getItemId(int position) {
		return position;
	}
	public View getView(final int position,View convertView,ViewGroup parent) {
		final ViewHolder viewHolder;
		if(convertView==null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.address_listview_layout, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if(addressItemList.get(position).isChecked()) {
			viewHolder.mCheckBox.setChecked(true);
		}
		else {
			viewHolder.mCheckBox.setChecked(false);
		}
		viewHolder.receiver_tv.setText(addressItemList.get(position).getReceiver());
		viewHolder.telephone_tv.setText(addressItemList.get(position).getTelephone());
		viewHolder.address_detail_tv.setText(addressItemList.get(position).getAddress_detail());
		viewHolder.address_delete_layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				modifyCountInterface.childDelete(position);
			}
		});
		viewHolder.address_edit_layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				modifyAddressInterface.modifyAddress(position);
			}
		});
		
		return convertView;
	}
	static class ViewHolder{
		TextView receiver_tv;
		TextView telephone_tv;
		TextView address_detail_tv;
		CheckBox mCheckBox;
		LinearLayout address_delete_layout,address_edit_layout;
		public ViewHolder(View itemView) {
			receiver_tv=(TextView) itemView.findViewById(R.id.receiver);
			telephone_tv=(TextView) itemView.findViewById(R.id.telephone);
			address_detail_tv=(TextView) itemView.findViewById(R.id.address_detail);
			mCheckBox=(CheckBox) itemView.findViewById(R.id.address_checkbox);
			address_delete_layout=(LinearLayout) itemView.findViewById(R.id.address_delete_layout);
			address_edit_layout=(LinearLayout) itemView.findViewById(R.id.address_edit_layout);
			ButterKnife.bind(this, itemView);
		}
	}
	public interface ModifyCountInterface{
		void childDelete(int position);
	}
	public interface ModifyAddressInterface{
		void modifyAddress(int position);
	}
}
