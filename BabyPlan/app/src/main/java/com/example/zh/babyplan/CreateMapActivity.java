package com.example.zh.babyplan;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.JsonUtil;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateMapActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText map_model_name;
    private Spinner bb_spinner;
    private Button add_map_model;
    private String b_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_map);

        ServiceClient.setClientHandler(handler);

        add_map_model = (Button) findViewById(R.id.add_map_model);
        add_map_model.setOnClickListener(this);
        map_model_name = (EditText) findViewById(R.id.map_model_name);
        bb_spinner = (Spinner) findViewById(R.id.bb_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CreateMapActivity.this, android.R.layout.simple_spinner_item, init_list());
        bb_spinner.setAdapter(arrayAdapter);
        bb_spinner.setOnItemSelectedListener(this);
    }

    public List init_list(){
        List list = new ArrayList();
        for(Map map : FakeDataBase.getList()){
            list.add(String.valueOf(map.get("b_name")));
        }
        return list;
    }

    private Handler handler = new Handler(){
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            if(msg.what == 1201){
                Intent intent = new Intent(CreateMapActivity.this, SetMapModelActivity.class);
                intent.putExtra("mid", Integer.valueOf(String.valueOf(JsonUtil.get_once_analysis(String.valueOf(msg.obj)).get("id"))));
                startActivity(intent);
            }
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };

    public boolean is_valid(){
        if(map_model_name.getText().equals("")){
            return false;
        }
        if(b_name.equals("")){
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add_map_model){
            if(is_valid()){
                RestfulClient.add_map(Integer.valueOf(FakeDataBase.getMml()), String.valueOf(map_model_name.getText()), b_name);

                Toast.makeText(CreateMapActivity.this, "添加成功，请不要重复点击", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        b_name = String.valueOf(adapterView.getItemAtPosition(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
