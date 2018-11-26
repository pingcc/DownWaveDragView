package com.example.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.DisplayUtil;
import com.example.user.myapplication.R;

/**
 * Created on 2017/6/30.
 * author chen_ping
 * Email yy_cping@163.com
 * Edit androidStudio
 */

public class CircleView extends View {
    private static final String TAG = "CircleView";
    private Paint mPaint;
    private int mCenterX;
    private int mCenterY;
    private float ract;
    // 动画执行的时间
    private final long mDuration = 2000;
    private final long strokewidth = DisplayUtil.dip2px(getContext(),3);//dp -->px
    private ObjectAnimator animator;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // 初始化操作...
    private void init() {
        mPaint = new Paint();
        // 设置锯齿
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokewidth);
        animator = ObjectAnimator.ofFloat(this, "rotation", 0f, 360.0f);
        animator.setDuration(mDuration);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        // 线性插值器 匀速旋转
        animator.setInterpolator(new LinearInterpolator());

        start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mCenterX = getMeasuredWidth() / 2;//空间的宽度
        mCenterY = getMeasuredHeight() / 2;
        ract = mCenterX-strokewidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.color_9));

        canvas.drawCircle(mCenterX, mCenterY, ract, mPaint);

        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.color_3));

        //rectF 外切矩形的坐上角坐标和右下角坐标
        //startAngle (右边中点开始为0度)起始角度
        //  sweepAngle, 从起点开始扫描到截至的中点角度
  /*      RectF oval = new RectF(ract - mCenterX, ract - mCenterX,
                ract + mCenterX, ract + mCenterX);*/
        RectF oval = new RectF(mCenterX-ract, mCenterX-ract,
                ract*2 + strokewidth,  ract*2 + strokewidth);
        canvas.drawArc(oval, -90, 60, false, mPaint);
    }

    public void stop() {
        animator.end();
    }

    public void start() {
        animator.start();
    }
}
