package com.example.zh.babyplan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zh.babyplan.Util.CircleImageView;
import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.JsonUtil;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;
import com.loopj.android.http.Base64;

import java.util.Map;

/**
 * Created by ZH on 2017/11/20.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText login_email, login_password;
    private TextView login_tologin, tv_forget_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ServiceClient.setClientHandler(handler);
        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        login_tologin = (TextView) findViewById(R.id.login_tologin);
        tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
        login_tologin.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        check_permission();
    }

    private Handler handler = new Handler(){
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            if(msg.what == 500){
                // 登录成功
                FakeDataBase.init_parent_data(JsonUtil.get_once_analysis(String.valueOf(msg.obj)));
//                RestfulClient.get_map_by_pid(Integer.parseInt(FakeDataBase.getMml()));
                RestfulClient.get_bb_by_pid(Integer.parseInt(FakeDataBase.getMml()));
            }else if(msg.what == 0x500){
                // 登录失败
                Toast.makeText(LoginActivity.this, "信息不匹配，请重试", Toast.LENGTH_SHORT).show();
            }else if(msg.what == 100){
                FakeDataBase.setList(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }else if(msg.what == 1200){
                FakeDataBase.setMap_list(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
                FakeDataBase.getMap_model_list().clear();
                for(Map map : FakeDataBase.getMap_list()){
                    RestfulClient.get_map_model_by_mid(Integer.valueOf(String.valueOf(map.get("id"))));
                }
            }else if(msg.what == 1100){
                FakeDataBase.getMap_model_list().add(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
            }
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_tologin){
            if(!login_email.getText().toString().equals("") && !login_password.getText().toString().equals("")){
                RestfulClient.get_parent_by_phone(login_email.getText().toString(), login_password.getText().toString());
            }else{
                Toast.makeText(LoginActivity.this, "信息不能为空", Toast.LENGTH_SHORT).show();
            }
        }else if(view.getId() == R.id.tv_forget_password){
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
//            RestfulClient.delete_stay(2018401001);
        }
    }

    public void check_permission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED){
            // 没有权限，获取权限
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE",
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.ACCESS_COARSE_LOCATION"}, 3);
        }else{
            // 有权限

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 3){
            if(grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // 允许

            }else{
                // 拒绝
                Toast.makeText(LoginActivity.this, "没有权限会导致\n地图、上传图片不能使用", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
