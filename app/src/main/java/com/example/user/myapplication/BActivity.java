package com.example.user.myapplication;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by user on 2017/6/8.
 */

public class BActivity extends AppCompatActivity {
    private static final String TAG = "BActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate()");
        TextView textView= (TextView) findViewById(R.id.textView);
        ObjectAnimator animator=ObjectAnimator.ofFloat(textView,"alpha",1,0,1);
        animator.setDuration(2000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();
        findViewById(R.id.button2).setOnClickListener((view) ->{
            startActivity(new Intent(getBaseContext(),CActivity.class));
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy()");
    }
}
