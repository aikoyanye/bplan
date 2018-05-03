package com.example.zh.babyplan.CommunityBlock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zh.babyplan.BabyCenter.CheckActivity;
import com.example.zh.babyplan.R;

/**
 * Created by ZH on 2018/1/18.
 */

public class checkTopicActivity extends AppCompatActivity {
    private String[] data={"这是第一个话题的名字","这是第二个话题的名字","这是第一个话题的名字","这是第一个话题的名字","这是第一个话题的名字",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_checktopic);
        ListView listView=(ListView) findViewById(R.id.topic_list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(checkTopicActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

    }
}
