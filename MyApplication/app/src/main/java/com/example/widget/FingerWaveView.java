package com.example.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.user.myapplication.R;


/**
 * Created on 2017/9/4.
 * author chen_ping
 * Email yy_cping@163.com
 * Edit androidStudio
 */


public class FingerWaveView extends View {

    private Paint mPaint;

    // 中心点x 坐标, 和 Y 坐标
    private int mCenterX;
    private int mCenterY;

    // 开始绘制的圆的半径 : 默认 183dp
    private int mStartRadius;

    // 结束绘制的圆的半径
    private int mEndRadius;

    // 动画执行的时间
    private final long mDuration = 1000;


    // 变化时的圆的半径,  这个是变值...
    private int mCurrentRadius;

    // 圆环的半径, =  最大圆的半径  -  最小圆的半径(开始是的半径)
    private int mDurationWidth;

    private final int mStartAlpha = 100;

    private ValueAnimator mValueAnimator;



    public FingerWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FingerCaveImageView);

         mStartRadius = (int)(ta.getDimension(R.styleable.FingerCaveImageView_cave_radius,-1f)+0.5f);
        init();
    }



    // 初始化操作...
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.wave_color));
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
            postInvalidate();
        });

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
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
        int secondRadius = mCurrentRadius + (int) (mDurationWidth / 2.0f + 0.5f);
        if (secondRadius > mEndRadius) {
            secondRadius = mStartRadius + secondRadius - mEndRadius;
        }
        int secondAlpha = (int) (mStartAlpha * (1 - (secondRadius - mStartRadius) * 1.0f / mDurationWidth) + 0.5);
        mPaint.setAlpha(secondAlpha);
        canvas.drawCircle(mCenterX, mCenterY, secondRadius, mPaint);


        // 绘制第三个圆
        int thirdRadius = mCurrentRadius + (int) (mDurationWidth / 2.0f * 2 + 0.5f);
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