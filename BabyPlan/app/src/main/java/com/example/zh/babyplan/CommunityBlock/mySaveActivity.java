package com.example.zh.babyplan.CommunityBlock;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zh.babyplan.CommunityFragment;
import com.example.zh.babyplan.Fruit;
import com.example.zh.babyplan.FruitAdapter;
import com.example.zh.babyplan.MainActivity;
import com.example.zh.babyplan.R;
import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.JsonUtil;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;
import com.example.zh.babyplan.widget.ClickCommunity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class mySaveActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Fruit> list;
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_mysave);
        ServiceClient.setClientHandler(handler);

        recyclerView = (RecyclerView)findViewById(R.id.recycle_view_save);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<Fruit>();
        RestfulClient.get_community_dynamics_collection(Integer.parseInt(FakeDataBase.getMml()));
    }

    private void init(Map map){
        list.add(new Fruit(String.valueOf(map.get("topic")), String.valueOf(map.get("problem")), String.valueOf(map.get("problem_description")), String.valueOf(map.get("p_name")), String.valueOf(map.get("created")), String.valueOf(map.get("support_num")), String.valueOf(map.get("head_portait"))));
        adapter = new FruitAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void get_comm(List<Map> list){
        for(Map map : list){
            RestfulClient.get_community_dynamics_by_id(Integer.valueOf(String.valueOf(map.get("did"))));
        }
    }

    public void onCommClick(View view) {
        int childAdapterPosition = CommunityFragment.getRecyclerView().getChildAdapterPosition(view);
        Intent intent = new Intent(mySaveActivity.this, ClickCommunity.class);
        intent.putExtra("did", childAdapterPosition);
        startActivity(intent);
    }

    public Handler handler = new Handler(){
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            if(msg.what == 800){
                get_comm(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
            }else if(msg.what == 704){
                init(JsonUtil.get_once_analysis(String.valueOf(msg.obj)));
            }
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };
}
