package com.example.zh.babyplan.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 84481 on 2018/1/30.
 */

public class FakeDataBase {

    private static String phone, p_name, role, head_portait, created, mml;

    private static List<Map<String, String>> com_list;

    // bb列表
    private static List<Map<String, String>> list = new ArrayList<>();

    private static List<Map<String, String >> map_list = new ArrayList<>();

    private static List<List<Map<String, String>>> map_model_list = new ArrayList<>();

    public static List<Map<String, String>> getCom_list() {
        return com_list;
    }

    public static void setCom_list(List<Map<String, String>> com_list) {
        FakeDataBase.com_list = com_list;
    }

    public static List<List<Map<String, String>>> getMap_model_list() {
        return map_model_list;
    }

    public static List<Map<String, String>> getMap_list() {
        return map_list;
    }

    public static void setMap_list(List<Map<String, String>> map_list) {
        FakeDataBase.map_list = map_list;
    }

    public static void setMml(String mml) {
        FakeDataBase.mml = mml;
    }

    public static List<Map<String, String>> getList() {
        return list;
    }

    public static void setList(List<Map<String, String>> list) {
        FakeDataBase.list = list;
    }

    public static String getMml() {
        return mml;
    }

    public static String getP_name() {
        return p_name;
    }

    public static String getCreated() {
        return created;
    }

    public static String getHead_portait() {
        return head_portait;
    }

    public static String getPhone() {
        return phone;
    }

    public static String getRole() {
        return role;
    }

    public static void setCreated(String created) {
        FakeDataBase.created = created;
    }

    public static void setHead_portait(String head_portait) {
        FakeDataBase.head_portait = head_portait;
    }

    public static void setP_name(String p_name) {
        FakeDataBase.p_name = p_name;
    }

    public static void setPhone(String phone) {
        FakeDataBase.phone = phone;
    }

    public static void setRole(String role) {
        FakeDataBase.role = role;
    }

    public static void init_parent_data(Map map){
        FakeDataBase.setCreated(String.valueOf(map.get("created")));
        FakeDataBase.setHead_portait(String.valueOf(map.get("head_portait")));
        FakeDataBase.setP_name(String.valueOf(map.get("p_name")));
        FakeDataBase.setPhone(String.valueOf(map.get("phone")));
        FakeDataBase.setRole(String.valueOf(map.get("role")));
        FakeDataBase.setMml(String.valueOf(map.get("id")));
    }
}
