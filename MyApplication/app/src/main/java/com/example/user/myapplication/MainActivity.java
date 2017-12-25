package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.dailog.SelectDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.textView)
    TextView textView;
    private SelectDialog selectDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        textView.setText("sdajhiasdiuh");
//        selectDialog = new SelectDialog(this,true,true);
        selectDialog = new SelectDialog(this,false,true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    private void snakeText(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(getBaseContext(),CActivity.class));
                break;
            case R.id.fab:
                snakeText(view);
                break;
            case R.id.button2:
                // 更新
                selectDialog
                        .setTitleText("有版本更新")
                       .setShowTitle(true)
                        .setRightBtnTextColor(R.color.color_white)
                        .setRightBackgroundDrawable(R.drawable.selector_default_button_angle_8)
                        .setSubtitleText("新版本更新，优化界面,\n修复一些不为人知的BUG。")
                        .setRightClickListener(v -> {
                        })
                        .show();
                break;
        }
    }


}
