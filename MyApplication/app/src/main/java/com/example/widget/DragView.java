package com.example.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 自定义的view，能够随意拖动。
 */

public class DragView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = "DragView";
    private boolean mInitPos;
    private int mL;//左边离屏幕的距离
    private int mT;//顶端离屏幕的距离
    private int mR;//控件右边离离屏幕的距离
    private int mB;//底部离屏幕的距离
    private float xCoordinate;
    private float yCoordinate;
    private float mXDistance;
    private float mYDistance;
    private int viewWidth;
    private int viewHigth;

    private float mMoveDistance;
    private int mMoveViewX;
    private int mMoveViewY;

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mInitPos = true;
        mXDistance = 0.0f;
        mYDistance = 0.0f;
    }

    public interface DragViewMoveListener {
        void ActionStartWaveAnimal();

        void ActionMoveInVisibleWaveAnimal();

        void ActionMoveVisibleWaveAnimal();

        void ActionEndWaveAnimal();

    }

    public interface DragViewMoveToViewStatusListener {
        void ActionMoveToViewAnimal();

        void ActionMoveOutViewAnimal();


    }

    private DragViewMoveToViewStatusListener mDragViewMoveToViewStatusListener;

    public void setDragViewMoveToViewStatusListener(int x, int y, DragViewMoveToViewStatusListener
            dragViewMoveToViewStatusListener) {
        mMoveViewX = x;
        mMoveViewY = y;
        mDragViewMoveToViewStatusListener = dragViewMoveToViewStatusListener;
    }

    private DragViewMoveListener mDragViewMoveListener;

    public void setStartWaveAnimalListener(float moveDistance, DragViewMoveListener
            dragViewMoveListener) {
        mMoveDistance = moveDistance;
        mDragViewMoveListener = dragViewMoveListener;
    }


    @Override
    public void layout(@Px int l, @Px int t, @Px int r, @Px int b) {
        super.layout(l, t, r, b);

        if (mInitPos) {
            mL = l;
            mT = t;
            mR = r;
            mB = b;

            Log.i(TAG, "layout: l=" + l);
            Log.i(TAG, "layout: getLeft=" + getLeft());
            Log.i(TAG, "layout: t=" + t);
            Log.i(TAG, "layout: getTop=" + getTop());
            Log.i(TAG, "layout: r=" + r);
            Log.i(TAG, "layout: getRight=" + getRight());
            Log.i(TAG, "layout: b=" + b);
            Log.i(TAG, "layout: getBottom=" + getBottom());
            xCoordinate = l + (r - l) / 2;
            yCoordinate = t + (b - t) / 2;
            viewWidth = mR - mL;
            viewHigth = mR - mL;
        }

    }

    private float downX;
    private float downY;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        mInitPos = false;
        if (this.isEnabled()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();


                    if (mDragViewMoveListener != null)
                        mDragViewMoveListener.ActionStartWaveAnimal();
                    break;
                case MotionEvent.ACTION_MOVE:

                    final float xDistance = event.getX() - downX;
                    final float yDistance = event.getY() - downY;


                    if (xDistance != 0 && yDistance != 0) {
                        int l = (int) (getLeft() + xDistance);
                        int r = l + viewWidth;
                        int t = (int) (getTop() + yDistance);
                        int b = t + viewHigth;
                        float moveXCoordinate = l + (r - l) / 2;
                        float moveYCoordinate = t + (b - t) / 2;

                        if (Math.max(Math.abs(moveXCoordinate - xCoordinate), Math.abs
                                (moveYCoordinate - yCoordinate)) > mMoveDistance) {
                            if (mDragViewMoveListener != null)
                                mDragViewMoveListener.ActionMoveInVisibleWaveAnimal();
                        } else {
                            if (mDragViewMoveListener != null)
                                mDragViewMoveListener.ActionMoveVisibleWaveAnimal();
                        }
                        Log.i(TAG, "layout: l =" + l);
                        Log.i(TAG, "layout: mMoveViewX =" + mMoveViewX);
                        Log.i(TAG, "layout: t=" + t);
                        Log.i(TAG, "layout: mMoveViewY=" + mMoveViewY);
                        if ((l < (mMoveViewX - 5)) && (t < (mMoveViewY - 5))) {
                            if (mDragViewMoveToViewStatusListener != null)
                                mDragViewMoveToViewStatusListener.ActionMoveToViewAnimal();
                        } else {
                            if (mDragViewMoveToViewStatusListener != null)
                                mDragViewMoveToViewStatusListener.ActionMoveOutViewAnimal();
                        }

                        Log.i(TAG, "layout: moveXCoordinate=" + (moveXCoordinate - xCoordinate));
                        Log.i(TAG, "layout: moveYCoordinate=" + (moveYCoordinate - yCoordinate));
                        if (l < mL && t < mT) {
                            float distance = (float) Math.sqrt(Math.pow(xCoordinate - Math.abs
                                    (mXDistance), 2) +
                                    Math.pow(yCoordinate - Math.abs(mYDistance), 2));
                            float moveDistance = (float) Math.sqrt(Math.pow(xCoordinate -
                                    moveXCoordinate, 2) + Math.pow(yCoordinate - moveYCoordinate,
                                    2));

                            float changeRate = (moveDistance / distance) * 0.7f;
                            int right = (int) (viewWidth * changeRate + 0.5f);
                            int bottom = (int) (viewHigth * changeRate + 0.5f);
                            Log.i(TAG, "layout: right=" + right);
                            Log.i(TAG, "layout: bottom=" + bottom);
                            Log.i(TAG, "layout: changeRate=" + changeRate);
//                             layout(l,t,l+viewWidth-right, t+viewHigth-bottom);
                            layout(l + right / 2, t + bottom / 2, l + viewWidth - right / 2, t +
                                    viewHigth - bottom / 2);

                        } else
                            layout(l, t, r, b);

                    }
                    break;
                case MotionEvent.ACTION_UP:

                    if (mDragViewMoveListener != null)
                        mDragViewMoveListener.ActionEndWaveAnimal();



                    setPressed(false);
                    //计算当前位置

                    layout(mL, mT, mR, mB);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    if (mDragViewMoveListener != null)
                        mDragViewMoveListener.ActionEndWaveAnimal();


                    setPressed(false);
                    layout(mL, mT, mR, mB);

                    break;
            }
            return true;
        }
        return false;
    }


}
