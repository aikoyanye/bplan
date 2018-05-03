package com.example.zh.babyplan;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.example.zh.babyplan.Util.FakeDataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BBLocationMap extends AppCompatActivity {

    private List<Map<String, String>> list;
    private List<LatLng> latLngs = new ArrayList<>();
    private MapView mapView = null;
    private AMap aMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bblocation_map);
        list = FakeDataBase.getMap_model_list().get(getIntent().getIntExtra("mid", 0));
        list_to_latLngs();
        mapView = (MapView) findViewById(R.id.location_map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
            Map<String, String> map = list.get(0);
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(Double.valueOf(map.get("x")), Double.valueOf(map.get("y")))));
            createMarkerAndPol();
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

    public void createMarkerAndPol() {
        for(LatLng latLng : latLngs){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.draggable(true);
            aMap.addMarker(markerOptions);
        }
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.addAll(latLngs);
        polygonOptions.fillColor(Color.LTGRAY);
        polygonOptions.strokeColor(Color.RED);
        polygonOptions.strokeWidth(1);
        aMap.addPolygon(polygonOptions);
    }

    public void list_to_latLngs(){
        for(Map<String, String> map : list){
            latLngs.add(new LatLng(Double.valueOf(map.get("x")), Double.valueOf(map.get("y"))));
        }
    }
}
