package com.example.junweiliu.simpleindicatorview;

import android.os.Handler;

import com.zjh.store.StoreActivity;

import java.util.TimerTask;

public class BannerTimerTask extends TimerTask {
    /**
     * handler
     */
    Handler handler;

    public BannerTimerTask(Handler handler) {
        super();
        this.handler = handler;
    }

    @Override
    public void run() {
        handler.sendEmptyMessage(StoreActivity.AUTOBANNER_CODE);
    }
}