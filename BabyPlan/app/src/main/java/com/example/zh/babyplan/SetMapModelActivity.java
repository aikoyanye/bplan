package com.example.zh.babyplan;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolygonOptions;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;

import java.util.ArrayList;
import java.util.List;

public class SetMapModelActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private MapView mapView = null;
    private AMap aMap = null;
    private List<LatLng> latLngs = new ArrayList<>();
    private Toolbar toolbar;
    private MyLocationStyle myLocationStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_map_model);
        ServiceClient.setClientHandler(handler);
        toolbar = (Toolbar) findViewById(R.id.set_map_medol_toolbar);
        toolbar.setTitle("设置地理围栏模板");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setOnMapLongClickListener(mapLongClickListener);
            getBluePoint();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    public void getBluePoint() {
        myLocationStyle = new MyLocationStyle();
        // 定位间隔，只在连续定位模式下生效
        //myLocationStyle.interval(2000);
        // 只定位一次
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        // 设置蓝点
        aMap.setMyLocationStyle(myLocationStyle);
        // 显示蓝点
        aMap.setMyLocationEnabled(true);
    }

    AMap.OnMapLongClickListener mapLongClickListener = new AMap.OnMapLongClickListener() {
        @Override
        public void onMapLongClick(LatLng latLng) {
            createMarker(latLng);
        }
    };

    public void createMarker(LatLng latLng) {
        int marker_num = latLngs.size() + 1;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("第" + marker_num + "个标点");
        markerOptions.snippet(null);
        markerOptions.draggable(true);
        aMap.addMarker(markerOptions);
        latLngs.add(latLng);
    }

    public void createPolygonOptions() {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.addAll(latLngs);
        polygonOptions.fillColor(Color.LTGRAY);
        polygonOptions.strokeColor(Color.RED);
        polygonOptions.strokeWidth(1);
        aMap.addPolygon(polygonOptions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.set_map_model_menu, menu);
        return true;
    }

    private Handler handler = new Handler(){
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            if(msg.what == 1101){
                startActivity(new Intent(SetMapModelActivity.this, MainActivity.class));
            }
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };

    public void add_map_model(){
        for(LatLng latLng : latLngs){
            RestfulClient.add_map_model(getIntent().getIntExtra("mid", 0), latLng.latitude, latLng.longitude);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.create_map_model){
            createPolygonOptions();
            add_map_model();
        }else if(item.getItemId() == R.id.reset_map_model){
            latLngs.clear();
            aMap.clear();
            Toast.makeText(SetMapModelActivity.this, "请等待蓝色箭标出现在长按标点", Toast.LENGTH_LONG).show();

        }
        return true;
    }
}
