package com.example.zh.babyplan.BabyCenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zh.babyplan.MainActivity;
import com.example.zh.babyplan.R;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;
import com.example.zh.babyplan.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Created by ZH on 2018/1/18.
 */

public class FoodActivity extends AppCompatActivity implements View.OnClickListener{
    EditText height;
    EditText weight;
    RadioGroup vaccine_whether;
    RadioButton vaccine_yes;
    RadioButton vaccine_no;
    String yes_no="是";
    RelativeLayout selectDate;
    TextView currentDate;
    CustomDatePicker customDatePicker;
    Button cancle;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.center_food);
        ServiceClient.setClientHandler(handler);
        ButterKnife.bind(this);
        height=(EditText) findViewById(R.id.height);
        weight=(EditText) findViewById(R.id.weight);
        vaccine_whether=(RadioGroup) findViewById(R.id.vaccine_radiogroup);
        vaccine_yes=(RadioButton) findViewById(R.id.vaccine_yes);
        vaccine_no=(RadioButton) findViewById(R.id.vaccine_no);
        selectDate=(RelativeLayout) findViewById(R.id.selectDate);
        selectDate.setOnClickListener(this);
        currentDate=(TextView) findViewById(R.id.currentDate);
        cancle=(Button) findViewById(R.id.cancle);
        submit=(Button) findViewById(R.id.submit);
        initDatePicker();
        vaccine_whether.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton y_n=(RadioButton)FoodActivity.this.findViewById(vaccine_whether.getCheckedRadioButtonId());
                yes_no=y_n.getText().toString();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //把身高、体重、是否注射疫苗插入数据库
                RestfulClient.add_bb_check_in(38,String.valueOf(height.getText()),String.valueOf(weight.getText())
                ,yes_no,String.valueOf(currentDate.getText()));
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意FLAG设置
                startActivity(intent);
                finish();
            }
        });
    }
    private Handler handler=new Handler(){
        public boolean sendMessageAtTime(Message msg,long uptimeMills){
            if(msg.what==2301){
            }
            return super.sendMessageAtTime(msg, uptimeMills);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectDate:
                // 日期格式为yyyy-MM-dd
                customDatePicker.show(currentDate.getText().toString());
                break;
        }
    }
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        currentDate.setText(now.split(" ")[0]);

        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                currentDate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(false); // 不允许循环滚动
    }
}
