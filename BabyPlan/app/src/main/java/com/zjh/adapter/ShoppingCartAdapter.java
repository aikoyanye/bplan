package com.zjh.adapter;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zh.babyplan.R;
import com.zjh.store.ShoppingCartItem;
import com.zjh.store.StringUtil;

import java.util.List;

public class ShoppingCartAdapter extends BaseAdapter{
	private List<ShoppingCartItem> shoppingCartItemList;
	private CheckInterface checkInterface;
	private ModifyCountInterface modifyCountInterface;
	private Context context;

	public ShoppingCartAdapter(Context context) {
		this.context = context;
	}

	public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
		this.shoppingCartItemList = shoppingCartItemList;
		notifyDataSetChanged();
	}



	/**
	 * 单选接口
	 */
	public void setCheckInterface(CheckInterface checkInterface) {
		this.checkInterface = checkInterface;
	}

	/**
	 * 改变商品数量接口
	 */
	public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
		this.modifyCountInterface=modifyCountInterface;
	}

	public int getCount() {return shoppingCartItemList==null?0:shoppingCartItemList.size();}
	public Object getItem(int position) {return shoppingCartItemList.get(position);}
	public long getItemId(int position) {return position;}



	public View getView(final int position,View convertView,ViewGroup parent) {
		final ViewHolder holder;
		if(convertView==null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item_listview_layout, parent,false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder)convertView.getTag();
		}
		final ShoppingCartItem shoppingCartItem = shoppingCartItemList.get(position);
		boolean choosed = shoppingCartItem.isChoosed();
		if(choosed) {
			holder.ckOneChose.setChecked(true);
		}else {
			holder.ckOneChose.setChecked(false);
		}
		String attribute = shoppingCartItem.getItemattr();
		if(!StringUtil.isEmpty(attribute)) {
			holder.itemattr.setText(attribute);
		}else {
			holder.itemattr.setText(shoppingCartItem.getItemattr());
		}
		holder.itemname.setText(shoppingCartItem.getItemname());
		holder.itemprice.setText(shoppingCartItem.getItemprice()+"");
		holder.itemamount.setText(shoppingCartItem.getItemamount()+"");
		holder.itemimage.setImageResource(shoppingCartItem.getItemimage());
		//单选框按钮
		holder.ckOneChose.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				shoppingCartItem.setChoosed(((CheckBox)v).isChecked());
				checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
			}
		});
		//增加按钮
		holder.increasebtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				modifyCountInterface.doIncrease(position, holder.itemamount, holder.ckOneChose.isChecked());//向外暴露接口
			}
		});
		//删减按钮
		holder.decreasebtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				modifyCountInterface.doDecrease(position, holder.itemamount, holder.ckOneChose.isChecked());//向外暴露接口
			}
		});
		//删除弹窗
		final Builder alert = new AlertDialog.Builder(context);
		holder.delete_item.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//                alert.setTitle("操作提示");
//                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
//                alert.setNegativeButton("取消",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                return;
//                            }
//                        });
//                alert.setPositiveButton("确定",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                modifyCountInterface.childDelete(position);//删除 目前只是从item中移除
//                            }
//                        });
//                alert.create().show();
				modifyCountInterface.childDelete(position);//删除 目前只是从item中移除
			}
		});
		return convertView;
	}

	//初始化控件
	class ViewHolder{
		ImageView itemimage,delete_item;
		TextView itemname,itemattr,itemprice,itemamount,decreasebtn,increasebtn;
		CheckBox ckOneChose;
		LinearLayout amountEdit;
		public ViewHolder(View itemView) {
			itemimage = (ImageView) itemView.findViewById(R.id.shopping_cart_item_imageview);
			delete_item = (ImageView) itemView.findViewById(R.id.delete_item);
			itemname = (TextView) itemView.findViewById(R.id.shopping_cart_item_name);
			itemattr = (TextView) itemView.findViewById(R.id.shopping_cart_item_attribute);
			itemprice = (TextView) itemView.findViewById(R.id.shopping_cart_item_price);
			itemamount = (TextView) itemView.findViewById(R.id.shopping_cart_item_amount);
			decreasebtn = (TextView) itemView.findViewById(R.id.decrease_button);
			increasebtn = (TextView) itemView.findViewById(R.id.increase_button);
			ckOneChose = (CheckBox) itemView.findViewById(R.id.select);
			amountEdit = (LinearLayout) itemView.findViewById(R.id.edit_amount);
		}
	}

	/**
	 * 复选框接口
	 */
	public interface CheckInterface{
		void checkGroup(int position,boolean isChecked);
	}

	/**
	 * 改变数量的接口
	 */
	public interface ModifyCountInterface{
		//增加操作  position元素位置   showCountView用于展示变化后数量的View isChecked子元素是否被选中
		void doIncrease(int position,View showCountView,boolean isChecked);
		//删减操作 position元素位置     showCountView用于展示变化后数量的View  isChecked子元素是否被选中
		void doDecrease(int position,View showCountView,boolean isChecked);
		//删除子item
		void childDelete(int position);
	}
}
