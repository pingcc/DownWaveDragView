package com.example.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.widget.DragView.DragViewMoveToViewStatusListener;


/**
 * 发送语音提示控件
 */
public class VoiceSendingView extends RelativeLayout {


    private final FingerWaveView view_custom;
    private final ImageView iv_drag_del;
    private final ImageView ivExecute;
    private final DragView dvExecuteDrag;
    private boolean isHoldVoiceBtn;
    private final TextView tvFingerStatus;


    public VoiceSendingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.voice_sending, this);
        view_custom = (FingerWaveView) findViewById(R.id.view_custom);
        iv_drag_del = (ImageView) findViewById(R.id.iv_drag_del);
        ivExecute = (ImageView) findViewById(R.id.iv_execute);
        dvExecuteDrag = (DragView) findViewById(R.id.iv_execute_drag);
        tvFingerStatus = (TextView) findViewById(R.id.tv_finger_status);
        updateVoiceView();
        iv_drag_del.post(new Runnable() {
            @Override
            public void run() {
                setDvDragListener();
            }
        });

        dvExecuteDrag.setStartWaveAnimalListener(50, new DragView.DragViewMoveListener() {
            @Override
            public void ActionStartWaveAnimal() {
                isHoldVoiceBtn = true;
                updateVoiceView();
            }

            @Override
            public void ActionMoveInVisibleWaveAnimal() {
                view_custom.setVisibility(INVISIBLE);
            }

            @Override
            public void ActionMoveVisibleWaveAnimal() {
                view_custom.setVisibility(VISIBLE);
            }

            @Override
            public void ActionEndWaveAnimal() {
                isHoldVoiceBtn = false;
                updateVoiceView();
                if (iv_drag_del.isSelected()) {
                    Toast.makeText(getContext(), "执行取消语音发送动作", Toast.LENGTH_SHORT).show();
                    iv_drag_del.setSelected(false);
                }else
                    Toast.makeText(getContext(), "执行发送语音发送动作", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setDvDragListener() {
        int viewWidth = iv_drag_del.getWidth();
        int viewHeigth = iv_drag_del.getHeight();
        RelativeLayout.LayoutParams params = (LayoutParams) iv_drag_del.getLayoutParams();
        int topMargin = params.topMargin;
        int leftMargin = params.leftMargin;
        Log.i("控件宽度", viewWidth + leftMargin + "");
        Log.i("控件高度", viewHeigth + topMargin + "");
        dvExecuteDrag.setDragViewMoveToViewStatusListener(viewWidth + leftMargin, viewHeigth +
                topMargin, new DragViewMoveToViewStatusListener() {
            @Override
            public void ActionMoveToViewAnimal() {
                iv_drag_del.setSelected(true);
            }

            @Override
            public void ActionMoveOutViewAnimal() {
                iv_drag_del.setSelected(false);
            }


        });
    }

    public void updateVoiceView() {
        if (isHoldVoiceBtn) {
            tvFingerStatus.setText(getResources().getString(R.string.chat_up_finger_release));
            view_custom.setVisibility(VISIBLE);
            showRecording();
        } else {
            tvFingerStatus.setText(getResources().getString(R.string.chat_up_finger_press));
            view_custom.setVisibility(INVISIBLE);
            showRelease();
        }
    }

    public void showRecording() {
        view_custom.start();
    }

    public void showRelease() {
        view_custom.stop();
    }

}
