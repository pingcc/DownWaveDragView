package com.example.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.DimensionUtils;
import com.example.user.myapplication.R;

/**
 * Created by gongxueyong on 2017/6/1.
 * 通过自定义 ImageView, 实现水波纹动画效果......
 * 实现思路 :
 * 1. 开启一个从0 到 1, 市场为 2000 的属性动画,  重复模式设置 为无限轮播...
 * 2. 监听动画的执行过程,  在过程中获取要绘制波浪(?) 的半径参数, 并开始重新绘制(即调用invalidate 方法  --> onDraw() 方法)
 * 3. 在 onDraw 方法通过动画过程中得到的参数开始绘制原型波浪...
 * 4. 重复动画
 */

public class CircleWaveImageView extends View {

    private Paint mPaint;

    // 中心点x 坐标, 和 Y 坐标
    private int mCenterX;
    private int mCenterY;

    // 开始绘制的圆的半径 : 默认 183dp
    private  int mStartRadius = (int) ((DimensionUtils.dp2px(183) + 0.5f) / 2);

    // 结束绘制的圆的半径
    private int mEndRadius;

    // 动画执行的时间
    private final long mDuration = 2000;


    // 变化时的圆的半径,  这个是变值...
    private int mCurrentRadius;

    // 圆环的半径, =  最大圆的半径  -  最小圆的半径(开始是的半径)
    private int mDurationWidth;

    private final int mStartAlpha = 40;

    private ValueAnimator mValueAnimator;

    public CircleWaveImageView(Context context) {
        super(context);
        init();
    }

    public CircleWaveImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleWaveImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 初始化操作...
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.salary_anim_color));
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        // 设置锯齿
        mPaint.setAntiAlias(true);

        // 创建一个属性动画,  从0 过度到 1
        mValueAnimator = ValueAnimator.ofFloat(0, 1.0f);
        // 设置时长
        mValueAnimator.setDuration(mDuration);
        // 重复次数为无限...
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        // 线性插值器
        mValueAnimator.setInterpolator(new LinearInterpolator());
        // 监听过程, 在过程中处理参数变化
        mValueAnimator.addUpdateListener(animation -> {

            float value = (float) animation.getAnimatedValue();

            mCurrentRadius = (int) (mStartRadius + mDurationWidth * value + 0.5);

            // 开始重新绘制
            invalidate();
        });

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mCenterX = getMeasuredWidth() / 2;
        mCenterY = getMeasuredHeight() / 2;

        // 结束 绘制圆的半径为控件总宽度 高度, 的一般
        mEndRadius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2;

        mDurationWidth = mEndRadius - mStartRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 绘制第一个圆
        // 设置画笔透明度
        int firstAlpha = (int) (mStartAlpha * (1 - (mCurrentRadius - mStartRadius) * 1.0f / mDurationWidth) + 0.5);
        mPaint.setAlpha(firstAlpha);
        canvas.drawCircle(mCenterX, mCenterY, mCurrentRadius, mPaint);


        // 绘制第二个圆
        int secondRadius = mCurrentRadius + (int) (mDurationWidth / 3.0f + 0.5f);
        if (secondRadius > mEndRadius) {
            secondRadius = mStartRadius + secondRadius - mEndRadius;
        }
        int secondAlpha = (int) (mStartAlpha * (1 - (secondRadius - mStartRadius) * 1.0f / mDurationWidth) + 0.5);
        mPaint.setAlpha(secondAlpha);
        canvas.drawCircle(mCenterX, mCenterY, secondRadius, mPaint);


        // 绘制第三个圆
        int thirdRadius = mCurrentRadius + (int) (mDurationWidth / 3.0f * 2 + 0.5f);
        if (thirdRadius > mEndRadius) {
            thirdRadius = mStartRadius + thirdRadius - mEndRadius;
        }
        int thirdAlpha = (int) (mStartAlpha * (1 - (thirdRadius - mStartRadius) * 1.0f / mDurationWidth) + 0.5);
        mPaint.setAlpha(thirdAlpha);
        canvas.drawCircle(mCenterX, mCenterY, thirdRadius, mPaint);
    }

    public void stop() {
        mValueAnimator.end();
    }

    public void start() {
        mValueAnimator.start();
    }
}