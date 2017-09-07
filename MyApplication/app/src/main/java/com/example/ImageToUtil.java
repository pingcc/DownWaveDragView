package com.example;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created on 2017/8/14.
 * author chen_ping
 * Email yy_cping@163.com
 * Edit androidStudio
 */

public class ImageToUtil {
    public static Bitmap imageToUtil(Bitmap leftTopBitmap, Bitmap rightTopBitmap, Bitmap
            leftBottomBitmap, Bitmap rightBottomBitmap) {
        int[] newValueWidth=compare(new int[]{leftTopBitmap.getWidth(),rightTopBitmap.getWidth(),leftBottomBitmap.getWidth(),rightBottomBitmap.getWidth()});
        int[] newValueHeight=compare(new int[]{leftTopBitmap.getHeight(),rightTopBitmap.getHeight(),leftBottomBitmap.getHeight(),rightBottomBitmap.getHeight()});

        final int newWidth = newValueWidth[0] + newValueWidth[1];
        final int newHeight = newValueHeight[0] + newValueHeight[1];

        Bitmap newBitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(leftTopBitmap,null,new Rect(0,0,200,200),null);
        canvas.drawBitmap(rightTopBitmap,null,new Rect(200,0,400,200),null);
        canvas.drawBitmap(leftBottomBitmap,null,new Rect(0,200,200,400),null);
        canvas.drawBitmap(rightBottomBitmap,null,new Rect(200,200,400,400),null);
        return newBitmap;
    }

    /**
     * 从大到下排序，冒泡排序法
     * @param values
     * @return
     */
    private static int[] compare(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = 0; j < values.length - i - 1; j++) {
                int tamp;
                if (values[j] < values[j + 1]) {
                    tamp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = tamp;
                }
            }

        }
        return values;
    }

}
