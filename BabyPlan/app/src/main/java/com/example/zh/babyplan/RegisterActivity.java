package com.example.zh.babyplan;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_email, et_username, et_role, et_password, et_password2;
    private TextView register_tosubmit, rs_forget_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ServiceClient.setClientHandler(handler);
        rs_forget_password = (TextView) findViewById(R.id.rs_forget_password);
        register_tosubmit = (TextView) findViewById(R.id.register_tosubmit);
        et_email = (EditText) findViewById(R.id.et_email);
        et_username = (EditText) findViewById(R.id.et_username);
        et_role = (EditText) findViewById(R.id.et_role);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password2 = (EditText) findViewById(R.id.et_password2);
        rs_forget_password.setOnClickListener(this);
        register_tosubmit.setOnClickListener(this);
    }

    private Handler handler = new Handler(){
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            if(msg.what == 501){
                Toast.makeText(RegisterActivity.this, "成功，去登录", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.register_tosubmit){
            if(!et_email.getText().toString().equals("") && !et_role.getText().toString().equals("")
                    && !et_username.getText().toString().equals("") && !et_password.getText().toString().equals("")
                    && !et_password2.getText().toString().equals("")){
                if(et_password.getText().toString().equals(et_password2.getText().toString())){
                    RestfulClient.add_parent(et_email.getText().toString(), et_username.getText().toString(), et_role.getText().toString(), et_password.getText().toString());
                }else{
                    Toast.makeText(RegisterActivity.this, "密码不匹配", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(RegisterActivity.this, "信息不能为空", Toast.LENGTH_SHORT).show();
            }
        }else if(view.getId() == R.id.rs_forget_password){
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }
    }
}
