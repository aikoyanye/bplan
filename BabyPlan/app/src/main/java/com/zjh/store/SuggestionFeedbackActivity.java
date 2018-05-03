package com.zjh.store;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zh.babyplan.R;

import butterknife.OnFocusChange;

public class SuggestionFeedbackActivity extends Activity{
	private EditText suggestion_et;
	private EditText suggestion_telephone_et;
	private TextView suggestion_submit;
	private ImageButton back;
	private LinearLayout linearLayout;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suggestion_feedback_layout);
		suggestion_et=(EditText) findViewById(R.id.suggestion_et);
		suggestion_telephone_et=(EditText) findViewById(R.id.suggestion_telephone);
		suggestion_submit=(TextView) findViewById(R.id.suggestion_submit);
		back=(ImageButton) findViewById(R.id.suggestion_feedback_back);
		linearLayout=(LinearLayout) findViewById(R.id.suggestion_feedback_layout);
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		suggestion_telephone_et.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus) {
					Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");  
					Matcher m = p.matcher(suggestion_telephone_et.getText().toString());
					if(TextUtils.isEmpty(suggestion_et.getText().toString())==false&&m.matches()) {
						suggestion_submit.setBackgroundColor(Color.RED);
					}
					else{
						suggestion_submit.setBackgroundColor(Color.LTGRAY);
					}
				}else {
					Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");  
					Matcher m = p.matcher(suggestion_telephone_et.getText().toString()); 
					if(TextUtils.isEmpty(suggestion_et.getText().toString())==false&&m.matches()) {
						suggestion_submit.setBackgroundColor(Color.RED);
					}
					else{
						suggestion_submit.setBackgroundColor(Color.LTGRAY);
					}
				}
			}
		});
		linearLayout.setOnTouchListener(new OnTouchListener() {    
            public boolean onTouch(View v, MotionEvent event) {  
                linearLayout.setFocusable(true);  
                linearLayout.setFocusableInTouchMode(true);  
                linearLayout.requestFocus();  
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(SuggestionFeedbackActivity.this.getCurrentFocus().getWindowToken()
                ,InputMethodManager.HIDE_NOT_ALWAYS);
                return false;  
            }  
        });
	}  
}
