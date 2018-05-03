package com.example.zh.babyplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zh.babyplan.widget.SwitchButtonView;

/**
 * Created by ZH on 2018/1/18.
 */

public class StoreActivity extends AppCompatActivity {
    private SwitchButtonView mSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_store);
        mSwitch= (SwitchButtonView) findViewById(R.id.if_alert);
        mSwitch.setOnSwitchListener(new SwitchButtonView.onSwitchListener() {
            @Override
            public void onSwitchChanged(boolean isCheck) {
            }
        });
    }
}