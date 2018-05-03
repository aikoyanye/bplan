package com.example.zh.babyplan.CommunityBlock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zh.babyplan.R;

/**
 * Created by ZH on 2018/1/18.
 */

public class searchArticleActivity extends AppCompatActivity {
    private String[] data={"这里随机出现10个帖子（估计是用一个random）","这里随机出现10个帖子","这里随机出现10个帖子","这里随机出现10个帖子","这里随机出现10个帖子",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_searcharticle);
        ListView listView=(ListView) findViewById(R.id.article_list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(searchArticleActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
    }
}
