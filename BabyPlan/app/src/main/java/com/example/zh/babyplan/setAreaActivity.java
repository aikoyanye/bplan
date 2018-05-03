package com.example.zh.babyplan;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.zh.babyplan.widget.SwitchButtonView;

/**
 * Created by ZH on 2018/1/18.
 */

public class setAreaActivity extends AppCompatActivity implements View.OnClickListener {

    private SwitchButtonView mSwitch;
    private Button btn_shock, btn_ring;
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_setarea);
        soundPool = new SoundPool.Builder().setMaxStreams(3).build();
        soundPool.load(this, R.raw.ss, 1);
        mSwitch= (SwitchButtonView) findViewById(R.id.if_alert);
        btn_ring = (Button) findViewById(R.id.set_ring);
        btn_shock = (Button) findViewById(R.id.set_shock);
        btn_shock.setOnClickListener(this);
        btn_ring.setOnClickListener(this);
        mSwitch.setOnSwitchListener(new SwitchButtonView.onSwitchListener() {
            @Override
            public void onSwitchChanged(boolean isCheck) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.set_ring){
            soundPool.play(1, 1, 1, 0, 0, 1);
        }else if(view.getId() == R.id.set_shock){
            Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
        }
    }
}