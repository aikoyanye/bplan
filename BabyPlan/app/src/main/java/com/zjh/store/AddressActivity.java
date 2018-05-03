package com.zjh.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zh.babyplan.R;
import com.example.zh.babyplan.Util.JsonUtil;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;
import com.zjh.adapter.AddressAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public class AddressActivity extends Activity implements AddressAdapter.ModifyCountInterface,AddressAdapter.ModifyAddressInterface {
	private String[] receiver_str;
	private String[] telephone_str;
	private String[] address_detail_str;
	ListView address_list;
	List<AddressItem> addressItemList=new ArrayList();
	private ImageButton back;
	private AddressAdapter addressAdapter;
	private TextView add_address;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_layout);

        ServiceClient.setClientHandler(handler);

		address_list=(ListView) findViewById(R.id.address_listview);
		back=(ImageButton) findViewById(R.id.address_back);
		add_address= (TextView) findViewById(R.id.add_address);
		add_address.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(AddressActivity.this,AddAddressActivity.class);
				startActivity(intent);
			}
		});
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		ButterKnife.bind(this);

		//设为默认单选
		address_list.setOnItemClickListener(new OnItemClickListener() {
			int currentNum=-1;
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				for(AddressItem addressItem:addressItemList) {
					addressItem.setChecked(false);
				}
				if(currentNum==-1) {///选中
					addressItemList.get(position).setChecked(true);
					currentNum=position;
				}else if(currentNum==position) {//同一个item选中变未选中
					for(AddressItem addressItem:addressItemList) {
						addressItem.setChecked(false);
					}
					currentNum=-1;
				}else if(currentNum!=position) {//不是同一个item选中当前的，取消上一个选中的
					for(AddressItem addressItem:addressItemList) {
						addressItem.setChecked(false);
					}
					addressItemList.get(position).setChecked(true);
					currentNum=position;
				}
				Toast.makeText(parent.getContext(), addressItemList.get(position).getReceiver(), Toast.LENGTH_SHORT).show();
				addressAdapter.notifyDataSetChanged();
			}
		});
	}

	private Handler handler=new Handler(){
        public boolean sendMessageAtTime(Message msg,long uptimeMills){
            if(msg.what==1900){
                init_data(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
            }
            return super.sendMessageAtTime(msg, uptimeMills);
        }
    };

    private void init_data(List<Map> list){
        int i = 0;
        address_detail_str = new String[list.size()];
        receiver_str = new String[list.size()];
        telephone_str = new String[list.size()];
        for(Map map : list){
            receiver_str[i]=String.valueOf(map.get("consignee"));
            telephone_str[i]=String.valueOf(map.get("phonecode"));
            address_detail_str[i] = String.valueOf(map.get("address"));
            i++;
        }
		initData();
    }

	protected void initData() {
		for(int i=0;i<receiver_str.length;i++) {
			AddressItem addressItem=new AddressItem();
			addressItem.setReceiver(receiver_str[i]);
			addressItem.setTelephone(telephone_str[i]);
			addressItem.setAddress_datail(address_detail_str[i]);
			addressItemList.add(addressItem);
		}
		addressAdapter = new AddressAdapter(getApplicationContext());
		addressAdapter.setModifyCountInterface(this);
		addressAdapter.setModifyAddressInterface(this);
		address_list.setAdapter(addressAdapter);
		addressAdapter.setAddressItemList(addressItemList);
	}

	public void childDelete(int position) {
		addressItemList.remove(position);
		addressAdapter.notifyDataSetChanged();
	}
	public void modifyAddress(int position) {
		Intent intent=new Intent(this,AddressEditActivity.class);
		startActivity(intent);
	}
	protected void onStart(){
        super.onStart();
        RestfulClient.get_address(1);
    }
}
