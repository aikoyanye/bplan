package com.example.zh.babyplan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.JsonUtil;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;

import java.util.Map;

public class BBInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private RadioButton b_info_sex;
    private Button put;
    private DatePicker datePicker;
    private String b_birthday, sex = "女";
    private int position;
    private Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbinfo);
        ServiceClient.setClientHandler(handler);
        position = getIntent().getIntExtra("position", 0);

        name = (EditText)findViewById(R.id.b_info_name);
        b_info_sex = (RadioButton)findViewById(R.id.b_info_sex);
        b_info_sex.setChecked(true);
        put = (Button)findViewById(R.id.put_bb_info);
        datePicker = (DatePicker)findViewById(R.id.b_info_birthday);
        datePicker.setOnClickListener(this);
        put.setOnClickListener(this);
        init();
    }

    private void init(){
        map = FakeDataBase.getList().get(position);
        name.setText(String.valueOf(map.get("b_name")));
        b_birthday = String.valueOf(map.get("birthday"));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.b_info_birthday){
            DatePickerDialog datePickerDialog = new DatePickerDialog(BBInfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int mon, int day) {
                    b_birthday = year + "-" + mon + "-" + day;
                }
            }, 2018, 1, 10);
            datePickerDialog.show();
        }else if(view.getId() == R.id.put_bb_info){
            if(b_info_sex.isChecked()){
                sex = "男";
            }
            RestfulClient.put_bb(Integer.valueOf(String.valueOf(map.get("id"))), Integer.valueOf(String.valueOf(map.get("pid"))), sex, String.valueOf(name.getText()), b_birthday);
        }
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 102){
                RestfulClient.get_bb_by_pid(Integer.parseInt(FakeDataBase.getMml()));
                Toast.makeText(BBInfoActivity.this, "YEP", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BBInfoActivity.this, checkBabyActivity.class));
                finish();
            }else if(msg.what == 100){
                FakeDataBase.setList(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
            }
        }
    };
}
