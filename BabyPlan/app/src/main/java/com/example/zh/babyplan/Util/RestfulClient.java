package com.example.zh.babyplan.Util;

import android.os.Bundle;
import android.os.Message;

public class RestfulClient {

    // 根据父母id获取bb
    public static void get_bb_by_pid(int pid){
        Message msg = Message.obtain();
        msg.what = 100;
        Bundle data = new Bundle();
        data.putInt("pid", pid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 添加bb
    public static void add_bb(int pid, String sex, String b_name, String birthday){
        Message msg = Message.obtain();
        msg.what = 101;
        Bundle data = new Bundle();
        data.putInt("pid", pid);
        data.putString("sex", sex);
        data.putString("b_name", b_name);
        data.putString("birthday", birthday);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 修改bb， 没有修改也要填充原本的数据
    public static void put_bb(int id, int pid, String sex, String b_name, String birthday){
        Message msg = Message.obtain();
        msg.what = 102;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("pid", pid);
        data.putString("sex", sex);
        data.putString("b_name", b_name);
        data.putString("birthday", birthday);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 删除bb
    public static void delete_bb(int id){
        Message msg = Message.obtain();
        msg.what = 103;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // get bb_about_eat
    public static void get_bb_about_eat_by_bid(int bid){
        Message msg = Message.obtain();
        msg.what = 200;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // add bb_about_eat
    public static void add_bb_about_eat(String type, String amount, int bid){
        Message msg = Message.obtain();
        msg.what = 201;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        data.putString("type", type);
        data.putString("amount", amount);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // put bb_about_eat
    public static void put_bb_about_eat(int id, String type, String amount, int bid){
        Message msg = Message.obtain();
        msg.what = 202;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("bid", bid);
        data.putString("type", type);
        data.putString("amount", amount);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // delete bb_about_eat
    public static void delete_bb_about_eat(int id){
        Message msg = Message.obtain();
        msg.what = 203;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // get bb_about_shit
    public static void get_bb_about_shit_by_bid(int bid){
        Message msg = Message.obtain();
        msg.what = 300;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // add bb_about_shit
    public static void add_bb_about_shit(String status, int bid){
        Message msg = Message.obtain();
        msg.what = 301;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        data.putString("status", status);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // put bb_about_shit
    public static void put_bb_about_shit(int id, String status, int bid){
        Message msg = Message.obtain();
        msg.what = 302;
        Bundle data = new Bundle();
        data.putString("status", status);
        data.putInt("id", id);
        data.putInt("bid", bid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // delete bb_about_shit
    public static void delete_bb_about_shit(int id){
        Message msg = Message.obtain();
        msg.what = 303;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // get bb_about_sleep
    public static void get_bb_about_sleep_by_bid(int bid){
        Message msg = Message.obtain();
        msg.what = 400;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // add bb_about_sleep
    public static void add_bb_about_sleep(int bid){
        Message msg = Message.obtain();
        msg.what = 401;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // put bb_about_sleep
    public static void put_bb_about_sleep(int id, int bid, String get_up_time){
        Message msg = Message.obtain();
        msg.what = 402;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("bid", bid);
        data.putString("get_up_time", get_up_time);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    //delete bb_about_sleep
    public static void delete_bb_about_sleep(int id){
        Message msg = Message.obtain();
        msg.what = 403;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // parent get by phone
    public static void get_parent_by_phone(String phone, String password){
        Message msg = Message.obtain();
        msg.what = 500;
        Bundle data = new Bundle();
        data.putString("phone", phone);
        data.putString("password", password);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // parent add
    public static void add_parent(String phone, String p_name, String role, String password){
        Message msg = Message.obtain();
        msg.what = 501;
        Bundle data = new Bundle();
        data.putString("phone", phone);
        data.putString("p_name", p_name);
        data.putString("role", role);
        data.putString("password", password);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_parent_head(int id, String head){
        Message msg = Message.obtain();
        msg.what = 504;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putString("head_portait", head);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // parent put
    public static void put_parent(int id, String phone, String p_name, String role){
        Message msg = Message.obtain();
        msg.what = 502;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putString("phone", phone);
        data.putString("p_name", p_name);
        data.putString("role", role);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // parent delete
    public static void delete_parent(int id){
        Message msg = Message.obtain();
        msg.what = 503;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // support get
    public static void get_support_by_dynamics_id(int dynamics_id){
        Message msg = Message.obtain();
        msg.what = 600;
        Bundle data = new Bundle();
        data.putInt("dynamics_id", dynamics_id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // support add
    public static void add_support(int uid, int dynamics_id){
        Message msg = Message.obtain();
        msg.what = 601;
        Bundle data = new Bundle();
        data.putInt("uid", uid);
        data.putInt("dynamics_id", dynamics_id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // support put
    public static void put_support(int id, int uid, int dynamics_id){
        Message msg = Message.obtain();
        msg.what = 602;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("uid", uid);
        data.putInt("dynamics_id", dynamics_id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // delete support
    public static void delete_support(int uid, int dynamics_id){
        Message msg = Message.obtain();
        msg.what = 603;
        Bundle data = new Bundle();
        data.putInt("uid", uid);
        data.putInt("dynamics_id", dynamics_id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 社区动态 get 需要更定上标和下标
    public static void get_community_dynamics(int up, int down){
        Message msg = Message.obtain();
        msg.what = 700;
        Bundle data = new Bundle();
        data.putInt("up", up);
        data.putInt("down", down);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 社区动态  逐条获取
    public static void get_community_dynamics_by_id(int id){
        Message msg = Message.obtain();
        msg.what = 704;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 更新社区赞
    public static void update_community_dynamics_support_by_id(int id){
        Message msg = Message.obtain();
        msg.what = 705;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 添加 社区动态
    public static void add_community_dynamics(String topic, String problem, String problem_description, int support_num, String p_name, String head_portait){
        Message msg = Message.obtain();
        msg.what = 701;
        Bundle data = new Bundle();
        data.putString("topic", topic);
        data.putString("problem", problem);
        data.putString("problem_description", problem_description);
        data.putInt("support_num", support_num);
        data.putString("p_name", p_name);
        data.putString("head_portait", head_portait);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 修改 社区动态
    public static void put_community_dynamics(int id, String topic, String problem, String problem_description, int support_num, String p_name){
        Message msg = Message.obtain();
        msg.what = 702;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putString("topic", topic);
        data.putString("problem", problem);
        data.putString("problem_description", problem_description);
        data.putInt("support_num", support_num);
        data.putString("p_name", p_name);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 删除 社区动态
    public static void delete_community_dynamics(int id){
        Message msg = Message.obtain();
        msg.what = 703;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 用户社区动态收藏 get
    public static void get_community_dynamics_collection(int pid){
        Message msg = Message.obtain();
        msg.what = 800;
        Bundle data = new Bundle();
        data.putInt("pid", pid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 用户社区动态收藏 add
    public static void add_community_dynamics_collection(int pid, int did){
        Message msg = Message.obtain();
        msg.what = 801;
        Bundle data = new Bundle();
        data.putInt("pid", pid);
        data.putInt("did", did);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 用户社区动态收藏 put
    public static void put_community_dynamics_collection(int id, int pid, int did){
        Message msg = Message.obtain();
        msg.what = 802;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("pid", pid);
        data.putInt("did", did);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 用户社区动态收藏 delete
    public static void delete_community_dynamics_collection(int id){
        Message msg = Message.obtain();
        msg.what = 803;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 社区动态评论 get
    public static void get_community_dynamics_comment(int dynamics_id){
        Message msg = Message.obtain();
        msg.what = 900;
        Bundle data = new Bundle();
        data.putInt("dynamics_id", dynamics_id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 社区动态评论 add
    public static void add_community_dynamics_comment(int dynamics_id, String uid, int rid, String content){
        Message msg = Message.obtain();
        msg.what = 901;
        Bundle data = new Bundle();
        data.putString("content", content);
        data.putInt("dynamics_id", dynamics_id);
        data.putString("uid", uid);
        data.putInt("rid", rid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 社区动态评论   put
    public static void put_community_dynamics_comment(int id, int dynamics_id, String uid, int rid, String content){
        Message msg = Message.obtain();
        msg.what = 902;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putString("content", content);
        data.putInt("dynamics_id", dynamics_id);
        data.putString("uid", uid);
        data.putInt("rid", rid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 社区动态评论 delete
    public static void delete_community_dynamics_comment(int id){
        Message msg = Message.obtain();
        msg.what = 903;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // alert get
    public static void get_alert_by_bid(int bid){
        Message msg = Message.obtain();
        msg.what = 1000;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // alert add
    public static void add_alert(int bid, String type){
        Message msg = Message.obtain();
        msg.what = 1001;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        data.putString("type", type);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // alert put
    public static void put_alert(int id, int bid, String type){
        Message msg = Message.obtain();
        msg.what = 1002;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("bid", bid);
        data.putString("type", type);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // alert delete
    public static void delete_alert(int id){
        Message msg = Message.obtain();
        msg.what = 1003;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // map_model get
    public static void get_map_model_by_mid(int mid){
        Message msg = Message.obtain();
        msg.what = 1100;
        Bundle data = new Bundle();
        data.putInt("mid", mid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // map_model add
    public static void add_map_model(int mid, double x, double y){
        Message msg = Message.obtain();
        msg.what = 1101;
        Bundle data = new Bundle();
        data.putInt("mid", mid);
        data.putDouble("x", x);
        data.putDouble("y", y);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // map_model put
    public static void put_map_model(int id, int mid, double x, double y){
        Message msg = Message.obtain();
        msg.what = 1102;
        Bundle data = new Bundle();
        data.putInt("mid", mid);
        data.putDouble("x", x);
        data.putDouble("y", y);
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // map_model delete
    public static void delete_map_model(int id){
        Message msg = Message.obtain();
        msg.what = 1103;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // map get
    public static void get_map_by_pid(int pid){
        Message msg = Message.obtain();
        msg.what = 1200;
        Bundle data = new Bundle();
        data.putInt("pid", pid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // map add
    public static void add_map(int pid, String map_name, String b_name){
        Message msg = Message.obtain();
        msg.what = 1201;
        Bundle data = new Bundle();
        data.putInt("pid", pid);
        data.putString("b_name", b_name);
        data.putString("map_name", map_name);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // put map
    public static void put_map(int id, int pid, String map_name, String b_name){
        Message msg = Message.obtain();
        msg.what = 1202;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("pid", pid);
        data.putString("b_name", b_name);
        data.putString("map_name", map_name);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // map delete
    public static void delete_map(int id){
        Message msg = Message.obtain();
        msg.what = 1203;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 用户最近浏览 get
    public static void get_user_recently_browse(int pid){
        Message msg = Message.obtain();
        msg.what = 1300;
        Bundle data = new Bundle();
        data.putInt("pid", pid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 用户最近浏览 add
    public static void add_user_recently_browse(int pid, int dynamics_id, String topic){
        Message msg = Message.obtain();
        msg.what = 1301;
        Bundle data = new Bundle();
        data.putInt("pid", pid);
        data.putInt("dynamics_id", dynamics_id);
        data.putString("topic", topic);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 用户最近浏览 put
    public static void put_user_recently_browse(int id, int pid, int dynamics_id, String topic){
        Message msg = Message.obtain();
        msg.what = 1302;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("pid", pid);
        data.putInt("dynamics_id", dynamics_id);
        data.putString("topic", topic);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    // 用户最近浏览 delete
    public static void delete_user_recently_browse(int id){
        Message msg = Message.obtain();
        msg.what = 1303;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_user_recently_browse_recommend(int pid){
        Message msg = Message.obtain();
        msg.what = 1304;
        Bundle data = new Bundle();
        data.putInt("pid", pid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_bb_location(int bid){
        Message msg = Message.obtain();
        msg.what = 1400;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_bb_location(int bid, double x, double y){
        Message msg = Message.obtain();
        msg.what = 1401;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        data.putDouble("x", x);
        data.putDouble("y", y);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void put_bb_location(int id, int bid, double x, double y){
        Message msg = Message.obtain();
        msg.what = 1402;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        data.putInt("id", id);
        data.putDouble("x", x);
        data.putDouble("y", y);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void delete_bb_location(int id){
        Message msg = Message.obtain();
        msg.what = 1403;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_normal_goods(){
        Message msg = Message.obtain();
        msg.what = 1500;
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_normal_goods(String goods_name, String goods_feature,
                                        String goods_price, String goods_group_variety,
                                        String goods_brand, String goods_num, String goods_picture,
                                        String goods_child_variety, String goods_oldprice){
        Message msg = Message.obtain();
        msg.what = 1501;
        Bundle data = new Bundle();
        data.putString("goods_name", goods_name);
        data.putString("goods_feature", goods_feature);
        data.putString("goods_price", goods_price);
        data.putString("goods_group_variety", goods_group_variety);
        data.putString("goods_brand", goods_brand);
        data.putString("goods_num", goods_num);
        data.putString("goods_picture", goods_picture);
        data.putString("goods_child_variety", goods_child_variety);
        data.putString("goods_oldprice", goods_oldprice);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void put_normal_goods(int id, String goods_name, String goods_feature,
                                        String goods_price, String goods_group_variety,
                                        String goods_brand, String goods_num, String goods_picture,
                                        String goods_child_variety, String goods_oldprice){
        Message msg = Message.obtain();
        msg.what = 1502;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putString("goods_name", goods_name);
        data.putString("goods_feature", goods_feature);
        data.putString("goods_price", goods_price);
        data.putString("goods_group_variety", goods_group_variety);
        data.putString("goods_brand", goods_brand);
        data.putString("goods_num", goods_num);
        data.putString("goods_picture", goods_picture);
        data.putString("goods_child_variety", goods_child_variety);
        data.putString("goods_oldprice", goods_oldprice);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void delete_normal_goods(int id){
        Message msg = Message.obtain();
        msg.what = 1503;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_discount_goods(){
        Message msg = Message.obtain();
        msg.what = 1600;
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_discount_goods(String goods_name, String goods_feature, String goods_newprice, String goods_oldprice, String goods_variety, String goods_brand, String goods_num, String goods_picture){
        Message msg = Message.obtain();
        msg.what = 1601;
        Bundle data = new Bundle();
        data.putString("goods_name", goods_name);
        data.putString("goods_feature", goods_feature);
        data.putString("goods_newprice", goods_newprice);
        data.putString("goods_oldprice", goods_oldprice);
        data.putString("goods_variety", goods_variety);
        data.putString("goods_brand", goods_brand);
        data.putString("goods_num", goods_num);
        data.putString("goods_picture", goods_picture);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void put_discount_goods(int id, String goods_name, String goods_feature, String goods_newprice, String goods_oldprice, String goods_variety, String goods_brand, String goods_num, String goods_picture){
        Message msg = Message.obtain();
        msg.what = 1602;
        Bundle data = new Bundle();
        data.putString("goods_name", goods_name);
        data.putString("goods_feature", goods_feature);
        data.putString("goods_newprice", goods_newprice);
        data.putString("goods_oldprice", goods_oldprice);
        data.putString("goods_variety", goods_variety);
        data.putString("goods_brand", goods_brand);
        data.putString("goods_num", goods_num);
        data.putString("goods_picture", goods_picture);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void delete_discount_goods(int id){
        Message msg = Message.obtain();
        msg.what = 1603;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_goods_attribute(String attr_name){
        Message msg = Message.obtain();
        msg.what = 1700;
        Bundle data = new Bundle();
        data.putString("attr_name", attr_name);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_goods_attribute(String attr_name, String goods_name, int goods_id){
        Message msg = Message.obtain();
        msg.what = 1701;
        Bundle data = new Bundle();
        data.putString("attr_name", attr_name);
        data.putString("goods_name", goods_name);
        data.putInt("goods_id", goods_id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void put_goods_attribute(int id, String attr_name, String goods_name, int goods_id){
        Message msg = Message.obtain();
        msg.what = 1702;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putString("attr_name", attr_name);
        data.putString("goods_name", goods_name);
        data.putInt("goods_id", goods_id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void delete_goods_attribute(int id){
        Message msg = Message.obtain();
        msg.what = 1703;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_goods_collect(int uid){
        Message msg = Message.obtain();
        msg.what = 1800;
        Bundle data = new Bundle();
        data.putInt("uid", uid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_goods_collect(int goods_id, int uid, String goods_name, String goods_feature, String goods_price, String goods_picture, String username, int is_collect){
        Message msg = Message.obtain();
        msg.what = 1801;
        Bundle data = new Bundle();
        data.putInt("goods_id", goods_id);
        data.putInt("uid", uid);
        data.putString("goods_name", goods_name);
        data.putString("goods_feature", goods_feature);
        data.putString("goods_price", goods_price);
        data.putString("goods_picture", goods_picture);
        data.putString("username", username);
        data.putInt("is_collect", is_collect);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void put_goods_collect(int id, int goods_id, int uid, String goods_name, String goods_feature, String goods_price, String goods_picture, String username, int is_collect){
        Message msg = Message.obtain();
        msg.what = 1802;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("goods_id", goods_id);
        data.putInt("uid", uid);
        data.putString("goods_name", goods_name);
        data.putString("goods_feature", goods_feature);
        data.putString("goods_price", goods_price);
        data.putString("goods_picture", goods_picture);
        data.putString("username", username);
        data.putInt("is_collect", is_collect);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void delete_goods_collect(int id){
        Message msg = Message.obtain();
        msg.what = 1803;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_address(int uid){
        Message msg = Message.obtain();
        msg.what = 1900;
        Bundle data = new Bundle();
        data.putInt("uid", uid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_address(int uid, int type, String username, String consignee, String phonecode, String address){
        Message msg = Message.obtain();
        msg.what = 1901;
        Bundle data = new Bundle();
        data.putInt("uid", uid);
        data.putInt("type", type);
        data.putString("username", username);
        data.putString("consignee", consignee);
        data.putString("phonecode", phonecode);
        data.putString("address", address);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void put_address(int id, int uid, int type, String username, String consignee, String phonecode, String address){
        Message msg = Message.obtain();
        msg.what = 1902;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("uid", uid);
        data.putInt("type", type);
        data.putString("username", username);
        data.putString("consignee", consignee);
        data.putString("phonecode", phonecode);
        data.putString("address", address);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void delete_address(int id){
        Message msg = Message.obtain();
        msg.what = 1903;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_stay(int uid){
        Message msg = Message.obtain();
        msg.what = 2000;
        Bundle data = new Bundle();
        data.putInt("uid", uid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_stay(int order_id, int goods_id, int type, int uid, String goods_name, String goods_price,
                                String total_price, String purchase_quantity, String goods_attr, String goods_picture,
                                String username, String logistics_company_name, String express_number,
                                String receiver, String receiver_address, String receiver_telephone){
        Message msg = Message.obtain();
        msg.what = 2001;
        Bundle data = new Bundle();
        data.putInt("order_id", order_id);
        data.putInt("goods_id", goods_id);
        data.putInt("type", type);
        data.putInt("uid", uid);
        data.putString("goods_name", goods_name);
        data.putString("goods_price", goods_price);
        data.putString("total_price", total_price);
        data.putString("purchase_quantity", purchase_quantity);
        data.putString("goods_attr", goods_attr);
        data.putString("goods_picture", goods_picture);
        data.putString("username", username);
        data.putString("logistics_company_name", logistics_company_name);
        data.putString("express_number", express_number);
        data.putString("receiver", receiver);
        data.putString("receiver_address", receiver_address);
        data.putString("receiver_telephone", receiver_telephone);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void put_stay(int id, int order_id, int goods_id, int type, int uid, String goods_name, String goods_price,
                                String total_price, String purchase_quantity, String goods_attr, String goods_picture,
                                String username, String logistics_company_name, String express_number,
                                String receiver, String receiver_address, String receiver_telephone){
        Message msg = Message.obtain();
        msg.what = 2002;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("order_id", order_id);
        data.putInt("goods_id", goods_id);
        data.putInt("type", type);
        data.putInt("uid", uid);
        data.putString("goods_name", goods_name);
        data.putString("goods_price", goods_price);
        data.putString("total_price", total_price);
        data.putString("purchase_quantity", purchase_quantity);
        data.putString("goods_attr", goods_attr);
        data.putString("goods_picture", goods_picture);
        data.putString("username", username);
        data.putString("logistics_company_name", logistics_company_name);
        data.putString("express_number", express_number);
        data.putString("receiver", receiver);
        data.putString("receiver_address", receiver_address);
        data.putString("receiver_telephone", receiver_telephone);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void delete_stay(int order_id){
        Message msg = Message.obtain();
        msg.what = 2003;
        Bundle data = new Bundle();
        data.putInt("order_id", order_id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_shopping_cart(int pid){
        Message msg = Message.obtain();
        msg.what = 2100;
        Bundle data = new Bundle();
        data.putInt("pid", pid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_shopping_cart(String goods_name, String goods_attr,
                                         String purchase_quantity, String goods_picture,
                                         String goods_price, int pid, int goods_id){
        Message msg = Message.obtain();
        msg.what = 2101;
        Bundle data = new Bundle();
        data.putString("goods_name", goods_name);
        data.putString("goods_attr", goods_attr);
        data.putString("purchase_quantity", purchase_quantity);
        data.putString("goods_picture", goods_picture);
        data.putString("goods_price", goods_price);
        data.putInt("pid", pid);
        data.putInt("goods_id", goods_id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void put_shopping_cart(int id, String goods_name, String goods_attr,
                                         String purchase_quantity, String goods_picture,
                                         String goods_price, int pid, int goods_id){
        Message msg = Message.obtain();
        msg.what = 2102;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putString("goods_name", goods_name);
        data.putString("goods_attr", goods_attr);
        data.putString("purchase_quantity", purchase_quantity);
        data.putString("goods_picture", goods_picture);
        data.putString("goods_price", goods_price);
        data.putInt("pid", pid);
        data.putInt("goods_id", goods_id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void delete_shopping_cart(int id){
        Message msg = Message.obtain();
        msg.what = 2103;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_goods_picture(){
        Message msg = Message.obtain();
        msg.what = 2200;
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_goods_picture(String picture, String goods_name, int goods_id, int type){
        Message msg = Message.obtain();
        msg.what = 2201;
        Bundle data = new Bundle();
        data.putInt("goods_id", goods_id);
        data.putInt("type", type);
        data.putString("picture", picture);
        data.putString("goods_name", goods_name);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void put_goods_picture(int id, String picture, String goods_name, int goods_id, int type){
        Message msg = Message.obtain();
        msg.what = 2202;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("goods_id", goods_id);
        data.putInt("type", type);
        data.putString("picture", picture);
        data.putString("goods_name", goods_name);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void delete_goods_picture(int id){
        Message msg = Message.obtain();
        msg.what = 2203;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void get_bb_check_in(int bid){
        Message msg = Message.obtain();
        msg.what = 2300;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void add_bb_check_in(int bid, String height, String weight, String vaccine,
                                       String created){
        Message msg = Message.obtain();
        msg.what = 2301;
        Bundle data = new Bundle();
        data.putInt("bid", bid);
        data.putString("height", height);
        data.putString("weight", weight);
        data.putString("vaccine", vaccine);
        data.putString("created", created);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void put_bb_check_in(int id, int bid, String height, String weight, String vaccine,
                                       String created){
        Message msg = Message.obtain();
        msg.what = 2302;
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putInt("bid", bid);
        data.putString("height", height);
        data.putString("weight", weight);
        data.putString("vaccine", vaccine);
        data.putString("created", created);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }

    public static void delete_bb_check_in(int id){
        Message msg = Message.obtain();
        msg.what = 2303;
        Bundle data = new Bundle();
        data.putInt("id", id);
        msg.setData(data);
        ServiceClient.getServiceHandler().sendMessage(msg);
    }
}
