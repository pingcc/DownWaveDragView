package com.example.util;

/**
 * Created on 2017/6/28.
 * author chen_ping
 * Email yy_cping@163.com
 * Edit androidStudio
 */
public class FastClickUtils {

    private static long time;

    private static class SingletonHolder {
        private static final FastClickUtils INSTANCE = new FastClickUtils();
    }

    private FastClickUtils() {
    }

    public static final FastClickUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean isFastClick() {
        long curTime = System.currentTimeMillis();
        if (curTime - time < 400) {
            time = curTime;
            return true;
        } else {
            time = curTime;
            return false;
        }

    }

    /**
     * 自定义时间
     *
     * @param millisecond 毫秒值
     * @return true表示在millisecond内
     */
    public boolean isFastClick(long millisecond) {
        long curTime = System.currentTimeMillis();
        if (curTime - time < millisecond) {
            time = curTime;
            return true;
        } else {
            time = curTime;
            return false;
        }

    }

}
