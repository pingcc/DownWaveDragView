package com.example;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.widget.GlideRoundedCornersTransform;

import java.io.ByteArrayOutputStream;

/**
 * Created on 2017/7/19.
 * author chen_ping
 * Email yy_cping@163.com
 * Edit androidStudio
 */

public class ImageUtils {
    public static void loadImage(String url, ImageView image) {
        Glide.with(image.getContext())
                .load(url)
                .into(image);
    }

    private static void loadImage(String url, ImageView image,RequestOptions requestOptions) {
        Glide.with(image.getContext())
                .load(url)
                .apply(requestOptions)
                .into(image);
    }
    private static void loadImage(byte[] bytes, ImageView image,RequestOptions requestOptions) {
        Glide.with(image.getContext())
                .asBitmap()
                .load(bytes)
                .apply(requestOptions)
                .into(image);
    }
    public static void loadCircleImage(String url, ImageView image) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        loadImage(url,image,requestOptions);
    }

    public static void loadRoundedCornersImage(String url, ImageView image, float radius) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new RoundedCorners((int) (dp2px(radius) + 0.5f)));
        loadImage(url,image,requestOptions);
    }

    public static void loadRoundedCornersImage(String url, ImageView image, float radius,
                                               GlideRoundedCornersTransform.CornerType cornerType) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.optionalTransform(new GlideRoundedCornersTransform((int) (dp2px
                (radius) + 0.5f),
                cornerType));
        loadImage(url,image,requestOptions);
    }

    public static void loadRoundedCornersImage(Bitmap bm, ImageView image, float radius,
                                               GlideRoundedCornersTransform.CornerType cornerType) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.optionalTransform(new GlideRoundedCornersTransform((int) (dp2px
                (radius) + 0.5f),
                cornerType));
        loadImage(Bitmap2Bytes(bm),image,requestOptions);
    }
    /**
     * 把Bitmap转Byte
     */
    public static byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    public static float dp2px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getDisplayMetrics());
    }
    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }
}
