package com.example.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.user.myapplication.R;

/**
 * Created by user on 2017/6/14.
 */

public class WaveCircleView extends View {
    private static final String TAG = "WaveCircleView";
    private Paint mPaint;
    private int waveCirCleWidth;
    private int waveCirCleHeight;
    private int centerX;
    private int centerY;
    private float ractStart;
    private float ractMax ;
    private int alpha;

    public WaveCircleView(Context context) {
        super(context);
        init();
    }

    public WaveCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAlpha(alpha);
        mPaint.setAntiAlias(true); //消除锯齿
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.salary_anim_color));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        waveCirCleWidth = getMeasuredWidth();//空间的宽度
        waveCirCleHeight = getMeasuredHeight();

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();
        centerX = waveCirCleWidth / 2;
        centerY = waveCirCleHeight / 2;
        ractStart = Math.min(waveCirCleWidth - paddingBottom - paddingTop, centerY - paddingLeft
                - paddingRight) - 100f;
        ractMax = ractStart + 100f;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                ractStart += 20.0f;
                invalidate();
            }
        }
    };

private int rect;
    @Override
    protected void onDraw(Canvas canvas) {
        //画第一个

        canvas.drawCircle(centerX, centerY, ractMax, mPaint);

        canvas.drawCircle(centerX, centerY, ractMax, mPaint);


        if(ractStart == ractMax-100)
            mPaint.setStyle(Paint.Style.FILL);
        else{
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(5f);
        }
        if (ractStart == ractMax) {

            handler.sendEmptyMessageDelayed(1,500);
            ractStart = ractStart - 100f;
        }else {
            handler.sendEmptyMessageDelayed(1,50);
            mPaint.setAlpha(40);
            canvas.drawCircle(centerX, centerY, ractStart, mPaint);
        }

    }
}
