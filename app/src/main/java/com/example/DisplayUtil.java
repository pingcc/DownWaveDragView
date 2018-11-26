package com.example;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DisplayUtil {
    public static int getSreenWidthPx(Context context) {
        return screenHelper(context).widthPixels;//屏幕的宽度并且将 dp——>px
    }

    public static int getSreenHeightPx(Context context) {
        return screenHelper(context).heightPixels;//屏幕的宽度并且将 dp——>px

    }
    private static DisplayMetrics screenHelper(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //int width = wm.getDefaultDisplay().getWidth()//该方法以过失
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);//获得尺寸
        return dm;
    }

    // ///////////////dip转px, px转dp///////////////////
    // dp = px / 设备密度
    public static int dip2px(Context context,float dip) {
        float density = context.getResources().getDisplayMetrics().density;// 设备密度
        int px = (int) (dip * density + 0.5f);// 3.1->3, 3.9+0.5->4.4->4
        return px;
    }

    public static float px2dip(Context context,int px) {
        float density = context.getResources().getDisplayMetrics().density;// 设备密度
        return px / density;
    }

    /*获取屏幕的宽*/
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context
                .WINDOW_SERVICE);
        int sreenWidth = windowManager.getDefaultDisplay().getWidth();
        return sreenWidth;
    }

    /*获取屏幕的高*/
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context
                .WINDOW_SERVICE);
        int sreenHeight = windowManager.getDefaultDisplay().getHeight();
        return sreenHeight;
    }
}
