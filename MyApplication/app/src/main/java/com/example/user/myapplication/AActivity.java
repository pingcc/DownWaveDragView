package com.example.user.myapplication;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.example.DisplayUtil;

/**
 * Created by user on 2017/6/8.
 */

public class AActivity extends AppCompatActivity {
    private static final String TAG = "AActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), BActivity.class));
        });
        Button button2 = (Button) findViewById(R.id.button2);
        ObjectAnimator animator = ObjectAnimator.ofFloat(button2, "translationY", 0f, DisplayUtil
                .getScreenHeight(this), 0f);
        animator.setDuration(5000);
        animator.setInterpolator(new BounceInterpolator());
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();
        findViewById(R.id.button2).setOnClickListener(this::enterCActivity);
    }


    private void enterCActivity(View view) {
        startActivity(new Intent(getBaseContext(), CActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy()");
    }
}
