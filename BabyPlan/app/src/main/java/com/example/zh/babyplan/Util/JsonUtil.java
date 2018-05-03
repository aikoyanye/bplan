package com.example.zh.babyplan.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    public static Map get_once_analysis(String json){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject object = new JSONObject(json);
            for (Iterator<String> it = object.keys(); it.hasNext(); ) {
                String key = it.next();
                map.put(key, object.getString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static List get_some_analysis(String json){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            JSONArray array = new JSONArray(json);
            for(int i = 0; i<array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                list.add(get_once_analysis(object.toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
