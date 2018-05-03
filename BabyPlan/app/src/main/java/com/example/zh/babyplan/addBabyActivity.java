package com.example.zh.babyplan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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

import java.util.Calendar;

/**
 * Created by ZH on 2018/1/18.
 */

public class addBabyActivity extends AppCompatActivity implements View.OnClickListener {
    private DatePicker datePicker;
    private int year;
    private int month;
    private int day;
    private String sex, birthday = "";
    private EditText bb_name;
    private RadioButton sex_radio;
    private Button bb_insert_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_addbaby);
        ServiceClient.setClientHandler(handler);
        bb_insert_button = (Button) findViewById(R.id.addbaby_submit);
        bb_insert_button.setOnClickListener(this);
        bb_name = (EditText) findViewById(R.id.bb_name);
        sex_radio = (RadioButton) findViewById(R.id.sex_radio);
        sex_radio.setChecked(true);
        datePicker = (DatePicker) findViewById(R.id.birth_date);
        datePicker.setOnClickListener(this);
//        initDate();
    }

    public void initDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public boolean is_valid(){
        if(sex_radio.isChecked()){
            sex = "男";
        }else{
            sex = "女";
        }
        if(bb_name.getText().equals("")){
            return false;
        }
        if(birthday.equals("")){
            return false;
        }
        return true;
    }

    public Handler handler = new Handler(){
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            if(msg.what == 101){
                Toast.makeText(addBabyActivity.this, "YEP", Toast.LENGTH_LONG).show();
                FakeDataBase.getList().add(JsonUtil.get_once_analysis(String.valueOf(msg.obj)));
            }
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addbaby_submit){
            if(is_valid()){
                RestfulClient.add_bb(Integer.parseInt(FakeDataBase.getMml()), sex, String.valueOf(bb_name.getText()), birthday);
            }
        }else if(view.getId() == R.id.birth_date){
            DatePickerDialog datePickerDialog = new DatePickerDialog(addBabyActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int mon, int day) {
                    mon += 1;
                    birthday = year + "-" + mon + "-" + day;
                }
            }, 2018, 1, 10);
            datePickerDialog.show();
        }
    }
}
