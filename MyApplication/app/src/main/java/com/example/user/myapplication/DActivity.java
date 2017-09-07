package com.example.user.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.BaseApplication;
import com.example.widget.CircleWaveImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2017/6/8.
 */

public class DActivity extends AppCompatActivity {
    private static final String TAG = "DActivity";
    private CircleWaveImageView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        setContentView(R.layout.activity_d);

//        view = (CircleWaveImageView) findViewById(R.id.circleWaveImageView);
        EditText editText = (EditText) findViewById(R.id.et_edit);
        editText.setFilters(new InputFilter[]{
                (source, start, end, dest, dstart, dend) -> {
                    CharSequence title = source;//当前输入的字符

                    int x = start;//当前输入的开始位置（字符）
                    int y = end;//当前输入的结束位置（字符）


                    String text = dest.toString();//已输入完的字符
                    int z = dstart;//光标的开始位置（字符）
                    int d = dend;//光标的结束位置（字符）
                   /* if (dstart < text.length())//光标不能移动到前面输入
                        return "";*/
                  /*   if (dstart != text.length())//光标不能移动到前面输入
                        return "";*/
                    if (TextUtils.equals("", source)) {   //这是删除
                        String deleteChar = text.substring(dstart, dend);
                        if (TextUtils.equals(".", deleteChar)) {//删除的是 .
                            if (text.length() > dend)//输入的text.不是最后一位
                                return ".";
                        }
                    }
                    if (text.contains(".")) {
                        int index = text.indexOf(".");
                        int length = text.substring(index).length();
                        if (length == 2 && dstart > index) {//小数点后面1位
                            return "";
                        }
                        /*if (source.equals("."))//等于.
                            return "";*/
                    }

                    if (text.length() > 2)//最多输入两位
                        return "";
                    if (dstart == 0 && source.equals(".")) {//判断第一位输入.
                        return "";
                    }
                    if ((dstart == 1) && !source.equals("."))//第二位不等于.
                        return "";
                    if ((dstart == 2) && source.equals("."))//第三位等于.
                        return "";
                    if (dstart > 2) //大于三位
                        return "";

                    return null;//可以返回
                }

        });

        Log.i(TAG+"MainThread", BaseApplication.getMainThreadId() + "");
        Log.i(TAG+"MainThread", "ViewRootImpl " + android.os.Process.myPid() + " Thread: " +
                android.os.Process.myTid() + " name " + Thread.currentThread()
                .getName());
        startRunable();

    }

    private void startRunable() {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                Log.i(TAG, "ViewRootImpl " + android.os.Process.myPid() + " Thread: " + android
                        .os.Process.myTid() + " name " + Thread.currentThread().getName());
                Looper.prepare();
                Handler handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        Log.i("handler", "ViewRootImpl " + android.os.Process.myPid() + " Thread: " + android
                                .os.Process.myTid() + " name " + Thread.currentThread().getName());
                    }
                };

                handler.sendEmptyMessage(1);
                Looper.loop();
//                culateMath();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "ViewRootImpl " + android.os.Process.myPid() + " Thread: " +
                                android.os.Process.myTid() + " name " + Thread.currentThread()
                                .getName());

                        Toast.makeText(getBaseContext(), "执行完毕", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        new Thread(r).start();
        new Timer();
        new TimerTask(){

            @Override
            public void run() {
                Log.i("TimerTask", "ViewRootImpl " + android.os.Process.myPid() + " Thread: " + android
                        .os.Process.myTid() + " name " + Thread.currentThread().getName());
            }
        }.run();

    }


    private void culateMath() {


        int[] a = {1, 3, 5, 7, 9, 11, 13, 15};
        IdentityHashMap<Integer, ArrayList<Integer>> map = new IdentityHashMap<>();

        int x, y, z;
        Integer m;
        for (int i = 0; i < a.length; i++) {
            x = a[i];
            for (int j = 0; j < a.length; j++) {
                y = a[j];
                for (int k = 0; k < a.length; k++) {
                    z = a[k];
                    m = new Integer(x + y + z);
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(x);
                    list.add(y);
                    list.add(z);
                    map.put(m, list);
                    if (m == 29) {
                        Log.i(TAG, "m =" + m + " , " + "x=" + x + "," + "z=" + y + "," + "z=" + z);
                    }
                }
            }
        }

        List<Map.Entry<Integer, ArrayList<Integer>>> num =
                new ArrayList<>(map.entrySet());
        Collections.sort(num, new Comparator<Map.Entry<Integer, ArrayList<Integer>>>() {
            @Override
            public int compare(Map.Entry<Integer, ArrayList<Integer>> obj1, Map.Entry<Integer,
                    ArrayList<Integer>> obj2) {

//                return obj1.getKey().compareTo(obj2.getKey());//升序排序
                return obj2.getKey().compareTo(obj1.getKey());  //降序排序
            }
        });

        for (Map.Entry<Integer, ArrayList<Integer>> mapping : num) {
            Log.i("TAG map ", "key:" + mapping.getKey() + " , " + "value:" + mapping.getValue()
                    .toString());
        }
       /* Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Log.i("TAG map ", "key:" + entry.getKey() + " , " + "value:" + entry.getValue()
                    .toString());

        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        view.stop();
        Log.i(TAG, "onDestroy()");
    }
}
