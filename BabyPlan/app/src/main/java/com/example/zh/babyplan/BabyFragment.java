package com.example.zh.babyplan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zh.babyplan.BabyCenter.CheckActivity;
import com.example.zh.babyplan.BabyCenter.CreHealthActivity;
import com.example.zh.babyplan.BabyCenter.FoodActivity;
import com.example.zh.babyplan.BabyCenter.SleepActivity;
import com.example.zh.babyplan.BabyCenter.WasteActivity;
import com.zjh.store.*;
import com.zjh.store.StoreActivity;

/**
 * Created by ZH on 2017/11/20.
 */

public class BabyFragment extends Fragment implements View.OnClickListener {
    private TextView food_tv;
    private TextView waste_tv;
    private TextView sleep_tv;
    private TextView check_tv;
    private TextView crehealth_tv, list_menu_name5, list_menu_name;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.baby_fragment, container, false);
        food_tv = (TextView) view.findViewById(R.id.baby_food);
        waste_tv = (TextView) view.findViewById(R.id.baby_waste);
        sleep_tv = (TextView) view.findViewById(R.id.baby_sleep);
        check_tv = (TextView) view.findViewById(R.id.baby_check);
        crehealth_tv = (TextView) view.findViewById(R.id.baby_crehealth);
        list_menu_name5 = (TextView) view.findViewById(R.id.list_menu_name5);
        list_menu_name = (TextView) view.findViewById(R.id.list_menu_name);
        list_menu_name.setOnClickListener(this);
        food_tv.setOnClickListener(this);
        waste_tv.setOnClickListener(this);
        sleep_tv.setOnClickListener(this);
        check_tv.setOnClickListener(this);
        crehealth_tv.setOnClickListener(this);
        list_menu_name5.setOnClickListener(this);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.baby_food:
                Intent intent1 = new Intent(getActivity(), FoodActivity.class);
                startActivity(intent1);
                break;
            case R.id.baby_waste:
                Intent intent2 = new Intent(getActivity(), WasteActivity.class);
                startActivity(intent2);
                break;
            case R.id.baby_sleep:
                Intent intent3 = new Intent(getActivity(), SleepActivity.class);
                startActivity(intent3);
                break;
            case R.id.baby_check:
                Intent intent4 = new Intent(getActivity(), CheckActivity.class);
                startActivity(intent4);
                break;
            case R.id.baby_crehealth:
                Intent intent5 = new Intent(getActivity(), CreHealthActivity.class);
                startActivity(intent5);
                break;
            case R.id.list_menu_name5:
                Intent intent6 = new Intent(getActivity(), CreateMapActivity.class);
                startActivity(intent6);
                break;
            case R.id.list_menu_name:
                Intent i = new Intent(getActivity(), StoreActivity.class);
                startActivity(i);
                break;
        }
    }
}
