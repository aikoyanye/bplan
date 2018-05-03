package com.example.zh.babyplan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2018/1/18.
 */

public class checkBabyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<BB> bList;
    private BBAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_checkbaby);

        ServiceClient.setClientHandler(handler);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_viewc);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        init_adapter();
    }

    private void init_adapter(){
        bList = new ArrayList<>();
        for(Map map : FakeDataBase.getList()){
            BB bb = new BB(String.valueOf(map.get("b_name")), String.valueOf(map.get("birthday")), String.valueOf(map.get("sex")));
            bList.add(bb);
        }
        adapter = new BBAdapter(bList);
        recyclerView.setAdapter(adapter);
    }

    public void onBBClick(View view){
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        show_dialog(childAdapterPosition);
    }

    private void show_dialog(final int childAdapterPosition){
        AlertDialog.Builder builder = new AlertDialog.Builder(checkBabyActivity.this);
        builder.setTitle("请选择操作");
        builder.setMessage("你想要？");
        builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(checkBabyActivity.this, BBInfoActivity.class);
                intent.putExtra("position", childAdapterPosition);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RestfulClient.delete_bb(Integer.valueOf(FakeDataBase.getList().get(childAdapterPosition).get("id")));
                FakeDataBase.getList().remove(childAdapterPosition);
            }
        });
        builder.show();
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 103){
                Toast.makeText(checkBabyActivity.this, "YEP", Toast.LENGTH_LONG).show();
                startActivity(new Intent(checkBabyActivity.this, checkBabyActivity.class));
                finish();
            }
        }
    };
}
