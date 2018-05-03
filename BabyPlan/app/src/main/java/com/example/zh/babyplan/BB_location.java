package com.example.zh.babyplan;

/**
 * Created by 84481 on 2018/1/31.
 */

public class BB_location {
    private String map_name, map_bb;

    public BB_location(String map_bb, String map_name){
        this.map_bb = map_bb;
        this.map_name = map_name;
    }

    public String getMap_bb() {
        return map_bb;
    }

    public String getMap_name() {
        return map_name;
    }
}
