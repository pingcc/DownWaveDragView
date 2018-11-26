package com.example;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Administrator on 2017/3/23.
 */

public class DimensionUtils {

    /**
     * dp转px
     *
     * @param value
     * @return
     */
    public static float dp2px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param value
     * @return
     */
    public static float sp2px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, getDisplayMetrics());
    }

    /**
     * 获取屏幕信息
     *
     * @return
     */
    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
        //        return HobayApplication.getsInstance().getResources().getDisplayMetrics();
    }

}
