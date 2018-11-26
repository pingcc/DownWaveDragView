package com.example.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.user.myapplication.R;

/**
 * Created on 2017/9/6.
 * author chen_ping
 * Email yy_cping@163.com
 * Edit androidStudio
 */

public class CustomRecordRelayoutLayout extends RelativeLayout{
    public CustomRecordRelayoutLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.voice_sending,this);
    }
}
