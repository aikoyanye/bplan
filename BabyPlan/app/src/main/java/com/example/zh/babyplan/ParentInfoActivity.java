package com.example.zh.babyplan;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.JsonUtil;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;

import java.util.Map;

/**
 * Created by ZH on 2018/1/18.
 */

public class ParentInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText p_mml, p_name, p_created, p_role, p_phone;
    private Button put_parent_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_parentinfo);
        ServiceClient.setClientHandler(handler);
        p_mml = (EditText) findViewById(R.id.p_mml);
        p_mml.setText(FakeDataBase.getMml());
        p_name = (EditText) findViewById(R.id.p_name);
        p_name.setText(FakeDataBase.getP_name());
        p_created = (EditText) findViewById(R.id.p_created);
        p_created.setText(FakeDataBase.getCreated());
        p_role = (EditText) findViewById(R.id.p_role);
        p_role.setText(FakeDataBase.getRole());
        p_phone = (EditText) findViewById(R.id.p_phone);
        p_phone.setText(FakeDataBase.getPhone());
        put_parent_info = (Button) findViewById(R.id.put_parent_info);
        put_parent_info.setOnClickListener(this);
    }

    private Handler handler = new Handler(){
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            if(msg.what == 502){
                init_parent_data(JsonUtil.get_once_analysis(String.valueOf(msg.obj)));
                MainActivity.username.setText(FakeDataBase.getP_name());
                MainActivity.mail.setText(FakeDataBase.getPhone());
                Toast.makeText(ParentInfoActivity.this, "YEP", Toast.LENGTH_LONG).show();
            }
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };

    public void init_parent_data(Map map){
        FakeDataBase.setCreated(String.valueOf(map.get("created")));
        FakeDataBase.setHead_portait(String.valueOf(map.get("head_portait")));
        FakeDataBase.setP_name(String.valueOf(map.get("p_name")));
        FakeDataBase.setPhone(String.valueOf(map.get("phone")));
        FakeDataBase.setRole(String.valueOf(map.get("role")));
        FakeDataBase.setMml(String.valueOf(map.get("id")));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.put_parent_info){
            RestfulClient.put_parent(Integer.valueOf(String.valueOf(p_mml.getText())), String.valueOf(p_phone.getText()), String.valueOf(p_name.getText()), String.valueOf(p_role.getText()));
        }
    }
}
