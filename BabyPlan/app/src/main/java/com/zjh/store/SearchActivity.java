package com.zjh.store;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.zh.babyplan.R;

/**
 * Created by zjh on 2018/3/6.
 */

public class SearchActivity extends Activity{
    private EditText search;
    private ImageButton back;
    private LinearLayout linearLayout;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        search= (EditText) findViewById(R.id.search_et);
        linearLayout= (LinearLayout) findViewById(R.id.search_layout);
        back= (ImageButton) findViewById(R.id.search_back);
        search.setFocusable(true);
        InputMethodManager imm = (InputMethodManager)SearchActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(search, 0);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                linearLayout.setFocusable(true);
                linearLayout.setFocusableInTouchMode(true);
                linearLayout.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken()
                        ,InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });
    }
}
