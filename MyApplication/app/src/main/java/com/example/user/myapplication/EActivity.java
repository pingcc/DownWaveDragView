package com.example.user.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.ImageToUtil;
import com.example.ImageUtils;
import com.example.widget.GlideRoundedCornersTransform;

/**
 * Created by user on 2017/6/8.
 */

public class EActivity extends AppCompatActivity {
    private static final String TAG = "CActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        setContentView(R.layout.activity_e);
        Bitmap bitmap1 = ((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.img1))
                .getBitmap();
        Bitmap bitmap2 = ((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.img2))
                .getBitmap();
        Bitmap bitmap3 = ((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.img3))
                .getBitmap();
        Bitmap bitmap4 = ((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.img4))
                .getBitmap();
        Bitmap bitmap = ImageToUtil.imageToUtil(bitmap1, bitmap2, bitmap3, bitmap4);
        ImageView imageView = (ImageView) findViewById(R.id.image);

        ImageUtils.loadRoundedCornersImage(bitmap, imageView, 20, GlideRoundedCornersTransform
                .CornerType.TOP_LEFT_TOP_RIGHT_BOTTOM_RIGHT);


}

}
