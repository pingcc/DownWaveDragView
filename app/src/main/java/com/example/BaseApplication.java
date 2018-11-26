package com.example;

import android.app.Application;
import android.content.Context;
import android.os.Process;


/**
 * Created on 2017/6/30.
 * author chen_ping
 * Email yy_cping@163.com
 * Edit androidStudio
 */

public class BaseApplication extends Application {
    private static Context mContext;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //获取主线程id
        mainThreadId = Process.myTid();

    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Context getInstance() {
        return mContext;
    }
}
