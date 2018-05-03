package com.example.zh.babyplan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zh.babyplan.Util.FakeDataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2017/11/20.
 */

public class AddressFragment extends Fragment {

    private static RecyclerView location_recycle;
    private BB_location_adapter adapter;
    private List<BB_location> locations = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.address_fragment,container,false);
        location_recycle = (RecyclerView) view.findViewById(R.id.location_recycle);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        location_recycle.setLayoutManager(layoutManager);
        init_adapter();
        return view;
    }

    public static RecyclerView getLocation_recycle() {
        return location_recycle;
    }

    public void init_adapter(){
        for(Map map : FakeDataBase.getMap_list()){
            locations.add(new BB_location(String.valueOf(map.get("b_name")), String.valueOf(map.get("map_name"))));
        }
        adapter = new BB_location_adapter(locations);
        location_recycle.setAdapter(adapter);
    }
}
