package com.example.zh.babyplan.BabyCenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.zh.babyplan.CheckListViewAdapter;
import com.example.zh.babyplan.CheckListViewItem;
import com.example.zh.babyplan.R;
import com.example.zh.babyplan.Util.JsonUtil;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;
import com.zjh.store.GoodsDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2018/1/18.
 */

public class CheckActivity extends AppCompatActivity {
    ImageButton check_back;
    ListView check_listview;
    CheckListViewAdapter checkListViewAdapter;
    List<CheckListViewItem> checkListViewItemList=new ArrayList<>();
//    private String[] check_time=new String[]{"11111","22222","333333"};
//    private String[] check_height=new String[]{"11","12","13"};
//    private String[] check_weight=new String[]{"111","112","113"};
//    private String[] vaccine=new String[]{"是","否","是"};
    private String[] check_time;
    private String[] check_height;
    private String[] check_weight;
    private String[] vaccine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.center_check);
        ServiceClient.setClientHandler(handler);
        check_back=(ImageButton) findViewById(R.id.check_back);
        check_listview=(ListView) findViewById(R.id.check_listview);
        check_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private Handler handler=new Handler(){
        public boolean sendMessageAtTime(Message msg,long uptimeMills){
            if(msg.what==2300){
                initDATA(JsonUtil.get_some_analysis(String.valueOf(msg.obj)));
            }
            return super.sendMessageAtTime(msg,uptimeMills);
        }
    };
    public void initDATA(List<Map> list){
        int i=0;
        check_time=new String[list.size()];
        check_height=new String[list.size()];
        check_weight=new String[list.size()];
        vaccine=new String[list.size()];
        for(Map map:list){
            check_time[i]=String.valueOf(map.get("created"));
            check_height[i]=String.valueOf(map.get("height"));
            check_weight[i]=String.valueOf(map.get("weight"));
            vaccine[i]=String.valueOf(map.get("vaccine"));
            i++;
        }
        checkListViewItemList.clear();
        init_data();
    }
    public void init_data(){
        String[] date=new String[check_time.length];
        for(int i=0;i<check_time.length;i++){
            CheckListViewItem checkListViewItem=new CheckListViewItem();
            checkListViewItem.setDate(check_time[i]);
            checkListViewItem.setHeight(check_height[i]);
            checkListViewItem.setWeight(check_weight[i]);
            checkListViewItem.setVaccine(vaccine[i]);
            checkListViewItemList.add(checkListViewItem);
            //去掉日期字符串中的“-”,变成int数据
            date[i]=check_time[i].replace("-","");
        }
        checkListViewAdapter=new CheckListViewAdapter(getApplicationContext());
        check_listview.setAdapter(checkListViewAdapter);
        checkListViewAdapter.setCheckListViewItemList(checkListViewItemList);
        if((Integer.parseInt(date[date.length-1])-Integer.parseInt(date[0]))%100==0){
            int mouths=(Integer.parseInt(date[date.length-1])-Integer.parseInt(date[0]))/100;
            if((Integer.parseInt(check_height[date.length-1])-Integer.parseInt(check_height[0]))<=3*mouths){
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckActivity.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("为您推荐一款有助于宝宝成长的产品");
                builder.setPositiveButton("前往", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent=new Intent(CheckActivity.this, GoodsDetailActivity.class);
                        intent.putExtra("item_id",13);
                        intent.putExtra("type","new");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("不需要", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                    }
                });
                builder.create().show();
            }
        }
    }
    //由于listview在scrollview中只能显示一项，所以要动态计算listview的height进行动态设置
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
    private void onShowDialog(String str) {
        new AlertDialog.Builder(CheckActivity.this)
                .setMessage(str)
                .show();
    }
    protected void onStart(){
        super.onStart();
        RestfulClient.get_bb_check_in(38);
    }
}
