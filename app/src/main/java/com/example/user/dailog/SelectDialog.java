package com.example.user.dailog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.DimensionUtils;
import com.example.user.myapplication.R;
import com.example.util.FastClickUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by mac on 2017/6/8.
 * <p>
 * 两种选择的dialog
 * android建造者模式
 */

public class SelectDialog extends BaseDialog {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_subtitle)
    TextView mTvSubtitle;
    @BindView(R.id.btn_left)
    Button mBtnLeft;
    @BindView(R.id.btn_right)
    Button mBtnRight;
    @BindView(R.id.view_line_title_below)
    View mLineTitleBelow;
    @BindView(R.id.view_line_subtitle_below)
    View mLineSubtitleBelow;
    @BindView(R.id.view_line_middle)
    View mLineMiddle;

    private String mTitle;
    private String mLeftText;
    private String mRightText;

    private View.OnClickListener mLeftClick;
    private View.OnClickListener mRightClick;
    private String mSubtitle;
    private boolean mShowLeftBtn;
    private boolean mShowRightBtn;
    private boolean mShowTitle;


    private int mRightBtnTextColor = 0;
    private int mLeftBtnTextColor = 0;
    private int mLeftBackgroundColor = 0;
    private int mRightBackgroundColor = 0;
    private int mLeftBackgroundDrawable = 0;
    private int mRightBackgroundDrawable = 0;

    public SelectDialog(@NonNull Context context) {
        this(context, true, true);
    }

    public SelectDialog(@NonNull Context context, boolean showLeftBtn, boolean showRightBtn) {
        super(context, R.style.SelectStyle);
        mShowLeftBtn = showLeftBtn;
        mShowRightBtn = showRightBtn;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_select;
    }

    @Override
    public void initDialogData() {//show 之后调用，控件已注入
        if (!TextUtils.isEmpty(mTitle))
            mTvTitle.setText(mTitle);
        if (!TextUtils.isEmpty(mLeftText))
            mBtnLeft.setText(mLeftText);
        if (!TextUtils.isEmpty(mRightText))
            mBtnRight.setText(mRightText);
        if (!TextUtils.isEmpty(mSubtitle))
            mTvSubtitle.setText(mSubtitle);

        isShowButton();
        isShowTitle();

        // 左边和右边按钮文字
        if (mLeftBtnTextColor != 0)
            mBtnLeft.setTextColor(ContextCompat.getColor(getContext(), mLeftBtnTextColor));
        if (mRightBtnTextColor != 0)
            mBtnRight.setTextColor(ContextCompat.getColor(getContext(), mRightBtnTextColor));

        if (mLeftBackgroundColor != 0)
            mBtnLeft.setBackgroundColor(mLeftBackgroundColor);
        if (mRightBackgroundColor != 0)
            mBtnRight.setBackgroundColor(mRightBackgroundColor);
        if (mLeftBackgroundDrawable != 0)
            mBtnLeft.setBackgroundResource(mLeftBackgroundDrawable);
        if (mRightBackgroundDrawable != 0)
            mBtnRight.setBackgroundResource(mRightBackgroundDrawable);
    }

    private void isShowTitle() {
        if(!mShowTitle){
            int dimens =(int)(DimensionUtils.dp2px(40)+0.5f);
            mTvSubtitle.setPadding(dimens,dimens,dimens,dimens);
        }
        mTvTitle.setVisibility(mShowTitle ? View.VISIBLE : View.GONE);
        mLineTitleBelow.setVisibility(mShowTitle ? View.VISIBLE : View.GONE);
    }

    /**
     * 是否显示左边和右边按钮
     **/
    private void isShowButton() {
        mBtnLeft.setVisibility(mShowLeftBtn ? View.VISIBLE : View.GONE);
        mBtnRight.setVisibility(mShowRightBtn ? View.VISIBLE : View.GONE);
        mLineMiddle.setVisibility(!mShowLeftBtn || !mShowRightBtn ? View.GONE : View.VISIBLE);
        mLineSubtitleBelow.setVisibility(!mShowLeftBtn || !mShowRightBtn ? View.GONE : View.VISIBLE);
    }


    @OnClick({R.id.btn_left, R.id.btn_right})
    public void onViewClicked(View view) {
        cancel();
        if (FastClickUtils.getInstance().isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_left:
                if (mLeftClick != null) {
                    mLeftClick.onClick(view);
                }

                break;
            case R.id.btn_right:
                if (mRightClick != null) {
                    mRightClick.onClick(view);
                }
                break;
        }
    }


    public SelectDialog setTitleText(String title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
        this.mTitle = title;
        return this;
    }

    public SelectDialog setTitleText(@StringRes int stingRes) {
        if (mTvTitle != null) {
            mTvTitle.setText(stingRes);
        }
        this.mTitle = getContext().getString(stingRes);
        return this;
    }

    public SelectDialog setSubtitleText(@StringRes int stingRes) {
        if (mTvSubtitle != null) {
            mTvSubtitle.setText(stingRes);
        }
        this.mSubtitle = getContext().getString(stingRes);
        return this;
    }

    public SelectDialog setSubtitleText(String title) {
        if (mTvSubtitle != null) {
            mTvSubtitle.setText(title);
        }
        this.mSubtitle = title;
        return this;
    }


    public SelectDialog setLeftText(String title) {
        if (mBtnLeft != null) {
            mBtnLeft.setText(title);
        }
        this.mLeftText = title;
        return this;
    }

    public SelectDialog setLeftText(@StringRes int stingRes) {
        if (mBtnLeft != null) {
            mBtnLeft.setText(stingRes);
        }
        this.mLeftText = getContext().getString(stingRes);
        return this;
    }

    public SelectDialog setRightText(String title) {
        if (mBtnRight != null) {
            mBtnRight.setText(title);
        }
        this.mRightText = title;
        return this;
    }

    public SelectDialog setRightText(@StringRes int stingRes) {
        if (mBtnRight != null) {
            mBtnRight.setText(stingRes);
        }
        this.mRightText = getContext().getString(stingRes);
        return this;
    }


    public SelectDialog setLeftClickListener(View.OnClickListener onClickListener) {

        this.mLeftClick = onClickListener;
        return this;
    }

    public SelectDialog setRightClickListener(View.OnClickListener onClickListener) {
        this.mRightClick = onClickListener;
        return this;
    }

    public SelectDialog setShowTitle(boolean showTitle) {
        this.mShowTitle = showTitle;
        return this;
    }

    public SelectDialog setLeftBtnTextColor(int leftBtnTextColor) {
        this.mLeftBtnTextColor = leftBtnTextColor;
        return this;
    }


    public SelectDialog setRightBtnTextColor(int rightTextColor) {
        this.mRightBtnTextColor = rightTextColor;
        return this;
    }

    public SelectDialog setLeftBackgroundColor(int leftBackgroundColor) {
        this.mLeftBackgroundColor = leftBackgroundColor;
        return this;
    }

    public SelectDialog setRightBackgroundColor(int rightBackgroundColor) {
        this.mRightBackgroundColor = rightBackgroundColor;
        return this;
    }
    public SelectDialog setRightBackgroundDrawable(int rightBackgroundDrawable) {
        this.mRightBackgroundDrawable = rightBackgroundDrawable;
        return this;
    }
    public SelectDialog setLeftBackgroundDrawable(int leftBackgroundDrawable) {
        this.mLeftBackgroundDrawable = leftBackgroundDrawable;
        return this;
    }
}
