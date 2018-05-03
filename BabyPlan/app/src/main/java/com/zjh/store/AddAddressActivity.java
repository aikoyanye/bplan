package com.zjh.store;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zh.babyplan.R;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;
import com.kankan.wheel.widget.OnWheelChangedListener;
import com.kankan.wheel.widget.WheelView;
import com.kankan.wheel.widget.adapters.ArrayWheelAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zjh on 2018/3/7.
 */

public class AddAddressActivity extends AddressEditBaseActivity implements OnWheelChangedListener, View.OnClickListener, View.OnFocusChangeListener {
    private LinearLayout linearLayout;
    private LinearLayout add_address_layout;
    private ImageButton back;

    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button mBtnConfirm;
    private boolean isVisible = true;
    private RelativeLayout layout;

    private TextView address_tv;
    private EditText receiver;
    private EditText receiver_telephone;
    private EditText address_detail;
    private TextView save_address;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address_layout);

        ServiceClient.setClientHandler(handler);

        linearLayout= (LinearLayout) findViewById(R.id.add_address_layout);
        add_address_layout= (LinearLayout) findViewById(R.id.add_address_select_layout);
        back= (ImageButton) findViewById(R.id.add_address_back);
        layout= (RelativeLayout) findViewById(R.id.layout);
        address_tv= (TextView) findViewById(R.id.address_tv);
        receiver= (EditText) findViewById(R.id.receiver);
        receiver_telephone= (EditText) findViewById(R.id.receiver_telephone);
        address_detail= (EditText) findViewById(R.id.add_address_detail);
        save_address= (TextView) findViewById(R.id.save_address);
        linearLayout.setFocusable(true);
        linearLayout.setFocusableInTouchMode(true);
        linearLayout.requestFocus();
        layout.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
        add_address_layout.setOnClickListener(new View.OnClickListener() {
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
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                linearLayout.setFocusable(true);
                linearLayout.setFocusableInTouchMode(true);
                linearLayout.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(AddAddressActivity.this.getCurrentFocus().getWindowToken()
                        ,InputMethodManager.HIDE_NOT_ALWAYS);
                layout.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                isVisible = true;
                return false;
            }
        });
        receiver.setOnFocusChangeListener(this);
        receiver_telephone.setOnFocusChangeListener(this);
        address_tv.setOnFocusChangeListener(this);
        address_detail.setOnFocusChangeListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        setUpViews();
        setUpListener();
        setUpData();


        final String address=address_tv.getText().toString()+address_detail.getText().toString();

        save_address.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RestfulClient.add_address(1,0,"username", receiver.getText().toString(), receiver_telephone.getText().toString(), address_tv.getText().toString()+address_detail.getText().toString());
            }
        });
    }

    private Handler handler=new Handler(){
        public boolean sendMessageAtTime(Message msg,long uptimeMills){
            if(msg.what==1901){
                Intent intent=new Intent(AddAddressActivity.this,AddressActivity.class);
                startActivity(intent);
            }
            return super.sendMessageAtTime(msg, uptimeMills);
        }
    };

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
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(AddAddressActivity.this, mProvinceDatas));
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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus) {
            Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
            Matcher m = p.matcher(receiver_telephone.getText().toString());
            if(TextUtils.isEmpty(address_tv.getText().toString())==false&&TextUtils.isEmpty(address_detail.getText().toString())==false&&TextUtils.isEmpty(receiver.getText().toString())==false&&m.matches()) {
                save_address.setBackgroundColor(Color.RED);
            }
            else{
                save_address.setBackgroundColor(Color.LTGRAY);
            }
        }else {
            Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
            Matcher m = p.matcher(receiver_telephone.getText().toString());
            if(TextUtils.isEmpty(address_tv.getText().toString())==false&&TextUtils.isEmpty(address_detail.getText().toString())==false&&TextUtils.isEmpty(receiver.getText().toString())==false&&m.matches()) {
                save_address.setBackgroundColor(Color.RED);
            }
            else{
                save_address.setBackgroundColor(Color.LTGRAY);
            }
        }
    }
}
