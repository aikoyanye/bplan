package com.zjh.store;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zh.babyplan.R;
import com.kankan.wheel.widget.OnWheelChangedListener;
import com.kankan.wheel.widget.WheelView;
import com.kankan.wheel.widget.adapters.ArrayWheelAdapter;

public class AddressEditActivity extends AddressEditBaseActivity implements OnClickListener,OnWheelChangedListener {
	private LinearLayout linearLayout;
	private LinearLayout address_select_layout;
	private ImageButton back;
	
	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;
	private Button mBtnConfirm;
	private boolean isVisible = true;
	private RelativeLayout layout;
	
	private TextView address_tv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_edit_layout);

		linearLayout=(LinearLayout) findViewById(R.id.address_edit_layout);
		address_select_layout=(LinearLayout) findViewById(R.id.address_select_layout);
		back=(ImageButton) findViewById(R.id.address_edit_back);
		layout=(RelativeLayout) findViewById(R.id.layout);
		address_tv=(TextView) findViewById(R.id.address_tv);
		linearLayout.setFocusable(true);  
        linearLayout.setFocusableInTouchMode(true);  
        linearLayout.requestFocus();  
		layout.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域

		address_select_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    isVisible = false;
                    layout.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                } else {
                    layout.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                    isVisible = true;
                }
            }
        });
		
		linearLayout.setOnTouchListener(new OnTouchListener() {    
            public boolean onTouch(View v, MotionEvent event) {
                linearLayout.setFocusable(true);  
                linearLayout.setFocusableInTouchMode(true);  
                linearLayout.requestFocus();  
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(AddressEditActivity.this.getCurrentFocus().getWindowToken()
                ,InputMethodManager.HIDE_NOT_ALWAYS);
                layout.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                isVisible = true;
                return false;  
            }  
        });
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		
		setUpViews();
		setUpListener();
		setUpData();
	}
	private void setUpViews() {
		mViewProvince = (WheelView) findViewById(R.id.id_province);
		mViewCity = (WheelView) findViewById(R.id.id_city);
		mViewDistrict = (WheelView) findViewById(R.id.id_district);
		mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
	}
	private void setUpListener() {
		// 添加change事件
    	mViewProvince.addChangingListener(this);
		// 添加change事件
    	mViewCity.addChangingListener(this);
		// 添加change事件
    	mViewDistrict.addChangingListener(this);
		// 添加onclick事件
    	mBtnConfirm.setOnClickListener(this);
    }
	private void setUpData() {
		initProvinceDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(AddressEditActivity.this, mProvinceDatas));
		// 设置可见条目数量
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		mViewDistrict.setVisibleItems(7);
		updateCities();
		updateAreas();
	}
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		// TODO Auto-generated method stub
		if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		} else if (wheel == mViewDistrict) {
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		}
	}
	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
		}
		mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
		mViewDistrict.setCurrentItem(0);
	}
	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mViewCity.setCurrentItem(0);
		updateAreas();
	}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_confirm:
			showSelectedResult();
			break;
		default:
			break;
		}
	}

	private void showSelectedResult() {
//		Toast.makeText(AddressEditActivity.this, "当前选中:"+mCurrentProviceName+","+mCurrentCityName+","
//				+mCurrentDistrictName+","+mCurrentZipCode, Toast.LENGTH_SHORT).show();
		address_tv.setText("  "+mCurrentProviceName+" "+mCurrentCityName+" "+mCurrentDistrictName);
		layout.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
        isVisible = true;
	}
}
