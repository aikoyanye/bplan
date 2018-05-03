package com.example.zh.babyplan.Util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by 84481 on 2018/1/15.
 */

public class ServiceClient {

    private final static String BB_URL = "http://120.77.153.248:8000/bb/";
    private final static String BB_ABOUT_EAT_URL = "http://120.77.153.248:8000/baby_about_eat/";
    private final static String BB_ABOUT_SHIT_URL = "http://120.77.153.248:8000/baby_about_shit/";
    private final static String BB_ABOUT_SLEEP_URL = "http://120.77.153.248:8000/baby_about_sleep/";
    private final static String PARENT_URL = "http://120.77.153.248:8000/parent/";
    private final static String SUPPORT_URL = "http://120.77.153.248:8000/support/";
    private final static String COMMUNITY_DYNAMICS_URL = "http://120.77.153.248:8000/community_dynamics/";
    private final static String COMMUNITY_DYNAMICS_COLLECTION_URL = "http://120.77.153.248:8000/community_dynamics_collection/";
    private final static String COMMUNITY_DYNAMICS_COMMENT_URL = "http://120.77.153.248:8000/community_dynamics_comment/";
    private final static String ALERT_URL = "http://120.77.153.248:8000/alert/";
    private final static String MAP_MODEL_URL = "http://120.77.153.248:8000/map_model/";
    private final static String MAP_URL = "http://120.77.153.248:8000/map/";
    private final static String IMAGE_URL = "http://120.77.153.248:8000/image/";
    private final static String MAP_MODEL_STATUS_URL = "http://120.77.153.248:8000/map_model_status/";
    private final static String USER_RECENTLY_URL = "http://120.77.153.248:8000/user_recently/";
    private final static String BB_LOCATION_URL = "http://120.77.153.248:8000/bb_location/";
    private final static String NORMAL_GOODS = "http://120.77.153.248:8000/normal_goods/";
    private final static String DISCOUNT_GOODS = "http://120.77.153.248:8000/discount_goods/";
    private final static String GOODS_ATTRIBUTE = "http://120.77.153.248:8000/goods_attribute/";
    private final static String GOODS_COLLECT = "http://120.77.153.248:8000/goods_collect/";
    private final static String ADDRESS = "http://120.77.153.248:8000/address/";
    private final static String STAY = "http://120.77.153.248:8000/stay/";
    private final static String SHOPPING_CART = "http://120.77.153.248:8000/shopping_cart/";
    private final static String GOODS_PICTURE = "http://120.77.153.248:8000/goods_picture/";
    private final static String BB_CHECK_IN = "http://120.77.153.248:8000/bb_check_in/";
    private final static String GET = "_GET";
    private final static String POST = "_POST";
    private final static String PUT = "_PUT";
    private final static String DELETE = "_DELETE";

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static Handler clientHandler, locate_handlder;

    public static void setLocate_handlder(Handler locate_handlder) {
        ServiceClient.locate_handlder = locate_handlder;
    }

    public static void setClientHandler(Handler clientHandler) {
        ServiceClient.clientHandler = clientHandler;
    }

    private static Handler serviceHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final RequestParams params = new RequestParams();
            final Bundle data = msg.getData();
            if(msg.what == 100){
                params.put("what", GET);
                params.put("pid", data.getInt("pid"));
                client.post(BB_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x100;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 100;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 101){
                params.put("what", POST);
                params.put("pid", data.getInt("pid"));
                params.put("birthday", data.getString("birthday"));
                params.put("sex", data.getString("sex"));
                params.put("b_name", data.getString("b_name"));
//                params.put("head_portrait", "asd");
                client.post(BB_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x101;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 101;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 102){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("pid", data.getInt("pid"));
                params.put("birthday", data.getString("birthday"));
                params.put("sex", data.getString("sex"));
                params.put("b_name", data.getString("b_name"));
                client.post(BB_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x102;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 102;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 103){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(BB_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x103;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 103;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 200){
                params.put("what", GET);
                params.put("bid", data.getInt("bid"));
                client.post(BB_ABOUT_EAT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x200;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 200;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 201){
                params.put("what", POST);
                params.put("type", data.getString("type"));
                params.put("amount", data.getString("amount"));
                params.put("bid", data.getInt("bid"));
                client.post(BB_ABOUT_EAT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg .what = 0x201;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 201;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 202){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("type", data.getString("type"));
                params.put("amount", data.getString("amount"));
                params.put("bid", data.getInt("bid"));
                client.post(BB_ABOUT_EAT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x202;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 202;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 203){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(BB_ABOUT_EAT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x203;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 203;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 300){
                params.put("what", GET);
                params.put("bid", data.getInt("bid"));
                client.post(BB_ABOUT_SHIT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x300;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 300;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 301){
                params.put("what", POST);
                params.put("status", data.getString("status"));
                params.put("bid", data.getInt("bid"));
                client.post(BB_ABOUT_SHIT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x301;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 301;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 302){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("status", data.getString("status"));
                params.put("bid", data.getInt("bid"));
                client.post(BB_ABOUT_SHIT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x302;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 302;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 303){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(BB_ABOUT_SHIT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x303;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 303;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 400){
                params.put("what", GET);
                params.put("bid", data.getInt("bid"));
                client.post(BB_ABOUT_SLEEP_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x400;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 400;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 401){
                params.put("what", POST);
                params.put("bid", data.getInt("bid"));
                client.post(BB_ABOUT_SLEEP_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x401;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 401;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 402){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("bid", data.getInt("bid"));
                params.put("get_up_time", data.getString("get_up_time"));
                client.post(BB_ABOUT_SLEEP_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x402;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 402;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 403){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(BB_ABOUT_SLEEP_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x403;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 403;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 500){
                params.put("what", GET);
                params.put("phone", data.getString("phone"));
                params.put("password", data.getString("password"));
                client.post(PARENT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x500;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 500;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 501){
                params.put("what", POST);
                params.put("phone", data.getString("phone"));
                params.put("p_name", data.getString("p_name"));
                params.put("role", data.getString("role"));
                params.put("password", data.getString("password"));
                client.post(PARENT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x501;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 501;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 504){
                params.put("what", "pic");
                params.put("id", data.getInt("id"));
                params.put("head_portait", data.getString("head_portait"));
                client.post(PARENT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x504;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 504;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 502){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("phone", data.getString("phone"));
                params.put("p_name", data.getString("p_name"));
                params.put("role", data.getString("role"));
                client.post(PARENT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x502;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 502;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 503){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(PARENT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x503;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 503;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 600){
                params.put("what", GET);
                params.put("dynamics_id", data.getInt("dynamics_id"));
                client.post(SUPPORT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x600;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 600;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 601){
                params.put("what", POST);
                params.put("uid", data.getInt("uid"));
                params.put("dynamics_id", data.getInt("dynamics_id"));
                client.post(SUPPORT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x601;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 601;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 602){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("uid", data.getInt("uid"));
                params.put("dynamics_id", data.getInt("dynamics_id"));
                client.post(SUPPORT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x602;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 602;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 603){
                params.put("what", DELETE);
                params.put("uid", data.getInt("uid"));
                params.put("dynamics_id", data.getInt("dynamics_id"));
                client.post(SUPPORT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x603;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 603;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 700){
                params.put("what", GET);
                params.put("up", data.getInt("up"));
                params.put("down", data.getInt("down"));
                client.post(COMMUNITY_DYNAMICS_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x700;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 700;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 704){
                params.put("what", "collect");
                params.put("id", data.getInt("id"));
                client.post(COMMUNITY_DYNAMICS_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x704;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 704;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 705){
                params.put("what", "update_support");
                params.put("id", data.getInt("id"));
                client.post(COMMUNITY_DYNAMICS_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x705;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 705;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            } else if(msg.what == 701){
                params.put("what", POST);
                params.put("topic", data.getString("topic"));
                params.put("problem", data.getString("problem"));
                params.put("problem_description", data.getString("problem_description"));
                params.put("support_num", data.getInt("support_num"));
                params.put("p_name", data.getString("p_name"));
                params.put("head_portait", data.getString("head_portait"));
                client.post(COMMUNITY_DYNAMICS_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x701;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 701;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 702){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("topic", data.getString("topic"));
                params.put("problem", data.getString("problem"));
                params.put("problem_description", data.getString("problem_description"));
                params.put("support_num", data.getInt("support_num"));
                params.put("p_name", data.getString("p_name"));
                client.post(COMMUNITY_DYNAMICS_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x702;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 702;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 703){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(COMMUNITY_DYNAMICS_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x703;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 703;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 800){
                params.put("what", GET);
                params.put("pid", data.getInt("pid"));
                client.post(COMMUNITY_DYNAMICS_COLLECTION_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x800;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 800;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 801){
                params.put("what", POST);
                params.put("pid", data.getInt("pid"));
                params.put("did", data.getInt("did"));
                client.post(COMMUNITY_DYNAMICS_COLLECTION_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x801;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 801;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 802){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("pid", data.getInt("pid"));
                params.put("did", data.getInt("did"));
                client.post(COMMUNITY_DYNAMICS_COLLECTION_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x802;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 802;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 803){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(COMMUNITY_DYNAMICS_COLLECTION_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x803;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 803;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 900){
                params.put("what", GET);
                params.put("dynamics_id", data.getInt("dynamics_id"));
                client.post(COMMUNITY_DYNAMICS_COMMENT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x900;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 900;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 901){
                params.put("what", POST);
                params.put("content", data.getString("content"));
                params.put("dynamics_id", data.getInt("dynamics_id"));
                params.put("uid", data.getString("uid"));
                params.put("rid", data.getInt("rid"));
                client.post(COMMUNITY_DYNAMICS_COMMENT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x901;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 901;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 902){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("content", data.getString("content"));
                params.put("dynamics_id", data.getInt("dynamics_id"));
                params.put("uid", data.getString("uid"));
                params.put("rid", data.getInt("rid"));
                client.post(COMMUNITY_DYNAMICS_COMMENT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x902;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 902;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 903){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(COMMUNITY_DYNAMICS_COMMENT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x903;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 903;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1000){
                params.put("what", GET);
                params.put("bid", data.getInt("bid"));
                client.post(ALERT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1000;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1000;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1001){
                params.put("what", POST);
                params.put("bid", data.getInt("bid"));
                params.put("type", data.getString("type"));
                client.post(ALERT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1001;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1001;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1002){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("bid", data.getInt("bid"));
                params.put("type", data.getString("type"));
                client.post(ALERT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1002;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1002;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1003){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(ALERT_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1003;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1003;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1100){
                params.put("what", GET);
                params.put("mid", data.getInt("mid"));
                client.post(MAP_MODEL_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1100;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1100;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1101){
                params.put("what", POST);
                params.put("mid", data.getInt("mid"));
                params.put("x", data.getDouble("x"));
                params.put("y", data.getDouble("y"));
                client.post(MAP_MODEL_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1101;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1101;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1102){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("mid", data.getInt("mid"));
                params.put("x", data.getDouble("x"));
                params.put("y", data.getDouble("y"));
                client.post(MAP_MODEL_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1102;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1102;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1103){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(MAP_MODEL_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1103;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1103;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1200){
                params.put("what", GET);
                params.put("pid", data.getInt("pid"));
                client.post(MAP_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1200;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1200;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1201){
                params.put("what", POST);
                params.put("pid", data.getInt("pid"));
                params.put("map_name", data.getString("map_name"));
                params.put("b_name", data.getString("b_name"));
                client.post(MAP_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1201;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1201;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1202){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("pid", data.getInt("pid"));
                params.put("b_name", data.getString("b_name"));
                params.put("map_name", data.getString("map_name"));
                client.post(MAP_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1202;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1202;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1203){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(MAP_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1203;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1203;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1300){
                params.put("what", GET);
                params.put("pid", data.getInt("pid"));
                client.post(USER_RECENTLY_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1300;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1300;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1301){
                params.put("what", POST);
                params.put("pid", data.getInt("pid"));
                params.put("dynamics_id", data.getInt("dynamics_id"));
                params.put("topic", data.getString("topic"));
                client.post(USER_RECENTLY_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1301;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1301;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1302){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("pid", data.getInt("pid"));
                params.put("dynamics_id", data.getInt("dynamics_id"));
                params.put("topic", data.getString("topic"));
                client.post(USER_RECENTLY_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1301;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1301;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1303){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(USER_RECENTLY_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1303;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1303;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1304){
                params.put("what", "Recommend");
                params.put("pid", data.getInt("pid"));
                client.post(USER_RECENTLY_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1304;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1304;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1400){
                params.put("what", GET);
                params.put("bid", data.getInt("bid"));
                client.post(BB_LOCATION_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1400;
                        locate_handlder.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1400;
                        msg.obj = s;
                        locate_handlder.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1401){
                params.put("what", POST);
                params.put("bid", data.getInt("data"));
                params.put("x", data.getDouble("x"));
                params.put("y", data.getDouble("y"));
                client.post(BB_LOCATION_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1401;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1401;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1402){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("bid", data.getInt("data"));
                params.put("x", data.getDouble("x"));
                params.put("y", data.getDouble("y"));
                client.post(BB_LOCATION_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1402;
                        locate_handlder.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1402;
                        msg.obj = s;
                        locate_handlder.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1403){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(BB_LOCATION_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1403;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1403;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1500){
                params.put("what", GET);
                client.post(NORMAL_GOODS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1500;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1500;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1501){
                params.put("what", POST);
                params.put("goods_name", data.getString("goods_name"));
                params.put("goods_feature", data.getString("goods_feature"));
                params.put("goods_price", data.getString("goods_price"));
                params.put("goods_group_variety", data.getString("goods_group_variety"));
                params.put("goods_brand", data.getString("goods_brand"));
                params.put("goods_num", data.getString("goods_num"));
                params.put("goods_picture", data.getString("goods_picture"));
                params.put("goods_child_variety", data.getString("goods_child_variety"));
                params.put("goods_oldprice", data.getString("goods_oldprice"));
                client.post(NORMAL_GOODS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1501;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1501;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1502){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("goods_name", data.getString("goods_name"));
                params.put("goods_feature", data.getString("goods_feature"));
                params.put("goods_price", data.getString("goods_price"));
                params.put("goods_group_variety", data.getString("goods_group_variety"));
                params.put("goods_brand", data.getString("goods_brand"));
                params.put("goods_num", data.getString("goods_num"));
                params.put("goods_picture", data.getString("goods_picture"));
                params.put("goods_child_variety", data.getString("goods_child_variety"));
                params.put("goods_oldprice", data.getString("goods_oldprice"));
                client.post(NORMAL_GOODS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1502;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1502;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1503){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(NORMAL_GOODS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1503;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1503;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1600){
                params.put("what", GET);
                client.post(DISCOUNT_GOODS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1600;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1600;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1601){
                params.put("what", POST);
                params.put("goods_name", data.getString("goods_name"));
                params.put("goods_feature", data.getString("goods_feature"));
                params.put("goods_newprice", data.getString("goods_newprice"));
                params.put("goods_oldprice", data.getString("goods_oldprice"));
                params.put("goods_variety", data.getString("goods_variety"));
                params.put("goods_brand", data.getString("goods_brand"));
                params.put("goods_num", data.getString("goods_num"));
                params.put("goods_picture", data.getString("goods_picture"));
                client.post(DISCOUNT_GOODS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1601;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1601;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1602){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("goods_name", data.getString("goods_name"));
                params.put("goods_feature", data.getString("goods_feature"));
                params.put("goods_newprice", data.getString("goods_newprice"));
                params.put("goods_oldprice", data.getString("goods_oldprice"));
                params.put("goods_variety", data.getString("goods_variety"));
                params.put("goods_brand", data.getString("goods_brand"));
                params.put("goods_num", data.getString("goods_num"));
                params.put("goods_picture", data.getString("goods_picture"));
                client.post(DISCOUNT_GOODS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1602;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1602;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1603){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(DISCOUNT_GOODS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1603;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1603;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1700){
                params.put("what", GET);
                params.put("attr_name", data.getString("attr_name"));
                client.post(GOODS_ATTRIBUTE, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1700;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1700;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1701){
                params.put("what", POST);
                params.put("attr_name", data.getString("attr_name"));
                params.put("goods_id", data.getInt("goods_id"));
                params.put("goods_name", data.getString("goods_name"));
                client.post(GOODS_ATTRIBUTE, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1701;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1701;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1702){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("attr_name", data.getString("attr_name"));
                params.put("goods_id", data.getInt("goods_id"));
                params.put("goods_name", data.getString("goods_name"));
                client.post(GOODS_ATTRIBUTE, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1702;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1702;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1703){
                params.put("id", data.getInt("id"));
                params.put("what", DELETE);
                client.post(GOODS_ATTRIBUTE, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1703;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1703;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1800){
                params.put("what", GET);
                params.put("uid", data.getInt("uid"));
                client.post(GOODS_COLLECT, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1800;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1800;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1801){
                params.put("what", POST);
                params.put("goods_id", data.getInt("goods_id"));
                params.put("goods_name", data.getString("goods_name"));
                params.put("goods_feature", data.getString("goods_feature"));
                params.put("goods_price", data.getString("goods_price"));
                params.put("goods_picture", data.getString("goods_picture"));
                params.put("uid", data.getInt("uid"));
                params.put("username", data.getString("username"));
                params.put("is_collect", data.getInt("is_collect"));
                client.post(GOODS_COLLECT, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1801;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1801;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1802){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("goods_id", data.getInt("goods_id"));
                params.put("goods_name", data.getString("goods_name"));
                params.put("goods_feature", data.getInt("goods_feature"));
                params.put("goods_price", data.getString("goods_price"));
                params.put("goods_picture", data.getString("goods_picture"));
                params.put("uid", data.getInt("uid"));
                params.put("username", data.getString("username"));
                params.put("is_collect", data.getInt("is_collect"));
                client.post(GOODS_COLLECT, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1802;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1802;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1803){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(GOODS_COLLECT, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1803;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1803;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1900){
                params.put("what", GET);
                params.put("uid", data.getInt("uid"));
                client.post(ADDRESS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1900;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1900;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1901){
                params.put("what", POST);
                params.put("uid", data.getInt("uid"));
                params.put("username", data.getString("username"));
                params.put("consignee", data.getString("consignee"));
                params.put("phonecode", data.getString("phonecode"));
                params.put("address", data.getString("address"));
                params.put("type", data.getInt("type"));
                client.post(ADDRESS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1901;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1901;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1902){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("uid", data.getInt("uid"));
                params.put("username", data.getString("username"));
                params.put("consignee", data.getString("consignee"));
                params.put("phonecode", data.getString("phonecode"));
                params.put("address", data.getString("address"));
                params.put("type", data.getInt("type"));
                client.post(ADDRESS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1902;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1902;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 1903){
                params.put("what", DELETE);
                params.put("id", data.getString("id"));
                client.post(ADDRESS, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x1903;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 1903;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2000){
                params.put("what", GET);
                params.put("uid", data.getInt("uid"));
                client.post(STAY, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2000;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2000;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2001){
                params.put("what", POST);
                params.put("order_id", data.getInt("order_id"));
                params.put("goods_id", data.getInt("goods_id"));
                params.put("goods_name", data.getString("goods_name"));
                params.put("goods_price", data.getString("goods_price"));
                params.put("total_price", data.getString("total_price"));
                params.put("purchase_quantity", data.getString("purchase_quantity"));
                params.put("goods_attr", data.getString("goods_attr"));
                params.put("goods_picture", data.getString("goods_picture"));
                params.put("type", data.getInt("type"));
                params.put("uid", data.getInt("uid"));
                params.put("username", data.getString("username"));
                params.put("logistics_company_name", data.getString("logistics_company_name"));
                params.put("express_number", data.getString("express_number"));
                params.put("receiver", data.getString("receiver"));
                params.put("receiver_address", data.getString("receiver_address"));
                params.put("receiver_telephone", data.getString("receiver_telephone"));
                client.post(STAY, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2001;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2001;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2002){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("order_id", data.getInt("order_id"));
                params.put("goods_id", data.getInt("goods_id"));
                params.put("goods_name", data.getString("goods_name"));
                params.put("goods_price", data.getString("goods_price"));
                params.put("total_price", data.getString("total_price"));
                params.put("purchase_quantity", data.getString("purchase_quantity"));
                params.put("goods_attr", data.getString("goods_attr"));
                params.put("goods_picture", data.getString("goods_picture"));
                params.put("type", data.getInt("type"));
                params.put("uid", data.getInt("uid"));
                params.put("username", data.getString("username"));
                params.put("logistics_company_name", data.getString("logistics_company_name"));
                params.put("express_number", data.getString("express_number"));
                params.put("receiver", data.getString("receiver"));
                params.put("receiver_address", data.getString("receiver_address"));
                params.put("receiver_telephone", data.getString("receiver_telephone"));
                client.post(STAY, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2002;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2002;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2003){
                params.put("what", DELETE);
                params.put("order_id", data.getInt("order_id"));
                client.post(STAY, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2003;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2003;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2100){
                params.put("what", GET);
                params.put("pid", data.getInt("pid"));
                client.post(SHOPPING_CART, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2100;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2100;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2101){
                params.put("what", POST);
                params.put("goods_name", data.getString("goods_name"));
                params.put("goods_attr", data.getString("goods_attr"));
                params.put("purchase_quantity", data.getString("purchase_quantity"));
                params.put("goods_picture", data.getString("goods_picture"));
                params.put("goods_price", data.getString("goods_price"));
                params.put("pid", data.getInt("pid"));
                params.put("goods_id", data.getInt("goods_id"));
                params.put("receiver", data.getString("receiver"));
                params.put("reveiver_telephone", data.getString("reveiver_telephone"));
                params.put("receiver_address", data.getString("receiver_address"));
                client.post(SHOPPING_CART, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2101;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2101;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2102){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("goods_name", data.getString("goods_name"));
                params.put("goods_attr", data.getString("goods_attr"));
                params.put("purchase_quantity", data.getString("purchase_quantity"));
                params.put("goods_picture", data.getString("goods_picture"));
                params.put("goods_price", data.getString("goods_price"));
                params.put("pid", data.getInt("pid"));
                params.put("goods_id", data.getInt("goods_id"));
                client.post(SHOPPING_CART, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2102;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2102;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2103){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(SHOPPING_CART, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2103;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2103;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2200){
                params.put("what", GET);
                client.post(GOODS_PICTURE, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2200;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2200;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2201){
                params.put("what", POST);
                params.put("picture", data.getString("picture"));
                params.put("goods_id", data.getInt("goods_id"));
                params.put("goods_name", data.getString("goods_name"));
                params.put("type", data.getInt("type"));
                client.post(GOODS_PICTURE, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2201;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2201;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2202){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("picture", data.getString("picture"));
                params.put("goods_id", data.getInt("goods_id"));
                params.put("goods_name", data.getString("goods_name"));
                params.put("type", data.getInt("type"));
                client.post(GOODS_PICTURE, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2202;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2202;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2203){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(GOODS_PICTURE, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2203;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2203;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2300){
                params.put("what", GET);
                params.put("bid", data.getInt("bid"));
                client.post(BB_CHECK_IN, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2300;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2300;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2301){
                params.put("what", POST);
                params.put("height", data.getString("height"));
                params.put("weight", data.getString("weight"));
                params.put("vaccine", data.getString("vaccine"));
                params.put("created", data.getString("created"));
                params.put("bid", data.getInt("bid"));
                client.post(BB_CHECK_IN, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2301;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2301;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2302){
                params.put("what", PUT);
                params.put("id", data.getInt("id"));
                params.put("height", data.getString("height"));
                params.put("weight", data.getString("weight"));
                params.put("vaccine", data.getString("vaccine"));
                params.put("created", data.getString("created"));
                params.put("bid", data.getInt("bid"));
                client.post(BB_CHECK_IN, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 0x2302;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2302;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }else if(msg.what == 2303){
                params.put("what", DELETE);
                params.put("id", data.getInt("id"));
                client.post(BB_CHECK_IN, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Message msg = Message.obtain();
                        msg.what = 2303;
                        clientHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Message msg = Message.obtain();
                        msg.what = 2303;
                        msg.obj = s;
                        clientHandler.sendMessage(msg);
                    }
                });
            }
        }
    };

    public static Handler getServiceHandler() {
        return serviceHandler;
    }
}
