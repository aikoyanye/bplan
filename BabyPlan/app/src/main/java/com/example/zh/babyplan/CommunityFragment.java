package com.example.zh.babyplan;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.zh.babyplan.CommunityBlock.*;
import com.example.zh.babyplan.Util.CircleImageView;
import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.JsonUtil;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;
import com.example.zh.babyplan.widget.ClickCommunity;
import com.loopj.android.http.Base64;

/**
 * Created by ZH on 2017/11/20.
 */

public class CommunityFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View view;
    private SwipeRefreshLayout swipe_refresh;
    private Toolbar community_toolbar;//初始化上边的状态栏、
    //    下边这里是关于测试滚动内容
//    private Fruit[] fruits = {
//            new Fruit("orange", R.drawable.animal_gou),
//            new Fruit("shu", R.drawable.animal_hu),
//            new Fruit("zhu", R.drawable.animal_ma),
//            new Fruit("ji", R.drawable.animal_hou)
//    };
    private static RecyclerView recyclerView;
    private Fruit[] fruits;
    private List<Fruit> fruitList;
    private FruitAdapter adapter;
    //    测试滚动内容的初始化完毕
// 下边这里是关于下拉刷新的测试内容
    private SwipeRefreshLayout swipeRefreshLayout;

    public CommunityFragment() {
    }
//    下拉刷新的测试内容完毕

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.community_fragment, container, false);
        ServiceClient.setClientHandler(handler);
        community_toolbar = (Toolbar) view.findViewById(R.id.community_toolbar);
        community_toolbar.setTitle("妈妈乐-宝贝计划");
        ((AppCompatActivity) getActivity()).setSupportActionBar(community_toolbar);
        setHasOptionsMenu(true);
//        Toolbard菜单栏的点击事件
        community_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cbar_addart:  /*发表新的帖子*/
                        Intent intent1 = new Intent(getActivity(), addArticleActivity.class);
                        startActivity(intent1);
//                        Toast.makeText(getActivity(),"增加帖子",Toast.LENGTH_LONG).show();
                        break;
//                    case R.id.cbar_topic:   /* 查看所有的话题*/
//                        Intent intent2 = new Intent(getActivity(), checkTopicActivity.class);
//                        startActivity(intent2);
//                        break;
//                    case R.id.cbar_comments:    /*查看自己收到的评论*/
//                        Intent intent3 = new Intent(getActivity(), myCommentActivity.class);
//                        startActivity(intent3);
//                        break;
//                    case R.id.cbar_search:     /*搜索帖子*/
//                        Intent intent4 = new Intent(getActivity(), searchArticleActivity.class);
//                        startActivity(intent4);
//                        break;
                    case R.id.cbar_save:    /*查看自己收藏的帖子*/
                        Intent intent5 = new Intent(getActivity(), mySaveActivity.class);
                        startActivity(intent5);
                        break;
//                    case R.id.cbar_like:    /*查看自己的赞*/
//                        Intent intent6 = new Intent(getActivity(), myLikeActivity.class);
//                        startActivity(intent6);
//                        break;
//                    case R.id.cbar_article:    /* 查看自己发表的所有帖子*/
//                        Intent intent7 = new Intent(getActivity(), myArticleActivity.class);
//                        startActivity(intent7);
//                        break;
                }
                return true;
            }
        });
        swipe_refresh = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipe_refresh.setOnRefreshListener(this);
        swipe_refresh.setColorSchemeColors(Color.GRAY, Color.BLUE);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        RestfulClient.get_community_dynamics(0, 10);
        // 模拟已经登录
//        RestfulClient.get_parent_by_phone("13247564807");
//        RestfulClient.get_bb_by_pid(1);
        RestfulClient.get_map_by_pid(Integer.parseInt(FakeDataBase.getMml()));
//        测试滚动内容的调用
//        initFruits();
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new FruitAdapter(fruitList);
//        recyclerView.setAdapter(adapter);
//        测试滚动内容的调用完毕
//        byte[] bs = Base64.decode(FakeDataBase.getHead_portait(), 100);
//        LoginActivity.iv.setImageBitmap(BitmapFactory.decodeByteArray(bs, 0, bs.length));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ServiceClient.setClientHandler(handler);
    }

    public static RecyclerView getRecyclerView() {
        return recyclerView;
    }

    //            Toolbar的菜单栏重写onCreateOptionsMenu()方法
/*    public boolean onCreateOptionsMenu(Menu menu){
        getActivity().getMenuInflater().inflate(R.menu.community_toolbar_menu,menu);
        return  true;
    }*/
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.community_toolbar_menu, menu);
    }

    private void initFruits(List<Map> list) {
//        fruitList.clear();
//        for (int i = 0; i < 50; i++) {
//            Random random = new Random();
//            int index = random.nextInt(fruits.length);
//            fruitList.add(fruits[index]);
//        }
        fruitList = new ArrayList<>();
        for(Map map : list){
            fruitList.add(new Fruit(String.valueOf(map.get("topic")), String.valueOf(map.get("problem")), String.valueOf(map.get("problem_description")), String.valueOf(map.get("p_name")), String.valueOf(map.get("created")), String.valueOf(map.get("support_num")), String.valueOf(map.get("head_portait"))));
        }

        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        // 下拉
//        recyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
//            @Override
//            public boolean onFling(int velocityX, int velocityY) {
//                RestfulClient.get_community_dynamics(0, 10);
//                return false;
//            }
//        });
    }

    private Handler handler = new Handler(){
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            if(msg.what == 700){
                FakeDataBase.setCom_list(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
                initFruits(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
                swipe_refresh.setRefreshing(false);
            }else if(msg.what == 500){
                init_parent_data(JsonUtil.get_once_analysis(String.valueOf(msg.obj)));
            }else if(msg.what == 100){
                FakeDataBase.setList(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
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

    public void init_parent_data(Map map){
        FakeDataBase.setCreated(String.valueOf(map.get("created")));
        FakeDataBase.setHead_portait(String.valueOf(map.get("head_portait")));
        FakeDataBase.setP_name(String.valueOf(map.get("p_name")));
        FakeDataBase.setPhone(String.valueOf(map.get("phone")));
        FakeDataBase.setRole(String.valueOf(map.get("role")));
        FakeDataBase.setMml(String.valueOf(map.get("id")));
    }

    // 下拉
    @Override
    public void onRefresh() {
        RestfulClient.get_community_dynamics(0, 10);
    }
}
