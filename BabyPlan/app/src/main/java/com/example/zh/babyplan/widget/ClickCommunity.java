package com.example.zh.babyplan.widget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zh.babyplan.CommentActivity;
import com.example.zh.babyplan.R;
import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.RestfulClient;

import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.richeditor.RichEditor;

public class ClickCommunity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private Toolbar toolbar;
    private Map<String, String> map = new HashMap<>();
    private TextView tv_problem;
    private RichEditor problem_editor;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_community);
        position = getIntent().getIntExtra("did", 0);
        map = FakeDataBase.getCom_list().get(position);
        toolbar = (Toolbar) findViewById(R.id.community_one);
        toolbar.setTitle(String.valueOf(map.get("topic")));
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);

        tv_problem = (TextView) findViewById(R.id.tv_problem);
        tv_problem.setText(String.valueOf(map.get("problem")));
        problem_editor = (RichEditor) findViewById(R.id.problem_editor);
        problem_editor.setEnabled(false);
        problem_editor.setHtml(String.valueOf(map.get("problem_description")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.community_one, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.comment){
            Intent intent = new Intent(new Intent(ClickCommunity.this, CommentActivity.class));
            intent.putExtra("position", position);
            startActivity(intent);
        }else if(item.getItemId() == R.id.support){
            RestfulClient.add_support(Integer.parseInt(FakeDataBase.getMml()), Integer.valueOf(String.valueOf(FakeDataBase.getCom_list().get(position).get("id"))));
            RestfulClient.update_community_dynamics_support_by_id(Integer.valueOf(String.valueOf(FakeDataBase.getCom_list().get(position).get("id"))));
            Toast.makeText(ClickCommunity.this, "点赞成功", Toast.LENGTH_LONG).show();
        }else if(item.getItemId() == R.id.collection){
            RestfulClient.add_community_dynamics_collection(Integer.parseInt(FakeDataBase.getMml()), Integer.valueOf(String.valueOf(FakeDataBase.getCom_list().get(position).get("id"))));
            Toast.makeText(ClickCommunity.this, "收藏成功", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
