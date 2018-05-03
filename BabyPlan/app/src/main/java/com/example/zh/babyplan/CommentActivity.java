package com.example.zh.babyplan;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.JsonUtil;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private int position;
    private RecyclerView recyclerView;
    private List<Comment> cList;
    private CommentAdapter adapter;
    private EditText content;
    private ImageButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        position = getIntent().getIntExtra("position", -1);
        ServiceClient.setClientHandler(handler);
        RestfulClient.get_community_dynamics_comment(Integer.valueOf(String.valueOf(FakeDataBase.getCom_list().get(position).get("id"))));
        recyclerView = (RecyclerView)findViewById(R.id.recycler_viewv);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        toolbar = (Toolbar)findViewById(R.id.comment_toolbar);
        toolbar.setTitle("评论");
        content = (EditText)findViewById(R.id.comment) ;
        send = (ImageButton)findViewById(R.id.btnSendComment) ;
        send.setOnClickListener(this);
    }

    private void init(List<Map> list){
        cList = new ArrayList<>();
        for(Map map : list){
            Comment comment = new Comment(String.valueOf(map.get("uid")), String.valueOf(map.get("content")), String.valueOf(map.get("created")));
            cList.add(comment);
        }
        adapter = new CommentAdapter(cList);
        recyclerView.setAdapter(adapter);
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 900){
                System.out.println(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
                init(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
            }else if(msg.what == 901){
                Intent intent = new Intent(CommentActivity.this, CommentActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnSendComment){
            if(!content.getText().toString().equals("")){
                RestfulClient.add_community_dynamics_comment(Integer.valueOf(String.valueOf(FakeDataBase.getCom_list().get(position).get("id"))), FakeDataBase.getP_name(), 0, String.valueOf(content.getText()));
            }else{
                Toast.makeText(CommentActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onCommentClick(View view){

    }
}
