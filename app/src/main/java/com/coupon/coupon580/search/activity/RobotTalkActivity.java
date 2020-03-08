package com.coupon.coupon580.search.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.VibrateUtils;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.voice.AutoCheck;
import com.coupon.coupon580.core.voice.RecogResult;
import com.coupon.coupon580.core.widget.MyToolbar;
import com.coupon.coupon580.core.widget.RoundTextView;
import com.coupon.coupon580.search.adapter.RobotTalkAdapter;
import com.coupon.coupon580.search.model.MessageBean;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RobotTalkActivity extends BaseActivity implements EventListener {


    @BindView(R.id.tool_bar)
    MyToolbar mToolBar;
    @BindView(R.id.talk_rv)
    RecyclerView mTalkRv;
    @BindView(R.id.content_et)
    EditText mContentEt;
    @BindView(R.id.send_text_tv)
    TextView mSendTextTv;
    @BindView(R.id.text_input_ll)
    LinearLayout mTextInputLl;
    @BindView(R.id.voice_input_rtv)
    RoundTextView mVoiceInputRtv;
    @BindView(R.id.voice_state_ll)
    LinearLayout mVoiceStateLl;
    @BindView(R.id.voice_state_tv)
    RoundTextView mVoiceStateTv;
    @BindView(R.id.voice_state_iv)
    ImageView mVoiceStateIv;

    private RobotTalkAdapter mTalkAdapter;
    private Handler mHandler = new Handler();

    private EventManager asr;

    @Override
    public int getLayoutId() {
        return R.layout.activity_robot_talk;
    }

    @Override
    public void initView() {
        mTalkAdapter = new RobotTalkAdapter();
        mTalkAdapter.bindToRecyclerView(mTalkRv);
        mTalkAdapter.isUseEmpty(false);
        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(700);
        mTalkRv.setItemAnimator(itemAnimator);
    }

    @Override
    public void initData() {
        asr = EventManagerFactory.create(this, "asr");
        asr.registerListener(this);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initListener() {
        mTalkRv.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTalkAdapter.addData(new MessageBean(MessageBean.TYPE_ROBOT, "晚上好，请问你需要什么物品?"));
                    }
                }, 400);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });

        mVoiceInputRtv.setOnTouchListener(new View.OnTouchListener() {
            private float mLastY = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initPermission();
                        mVoiceInputRtv.setBackgroundColor(getResources().getColor(R.color.color_dddddd));
                        onVoiceStateRec();
                        mVoiceStateLl.setVisibility(View.VISIBLE);
                        mLastY = event.getY();
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                VibrateUtils.vibrate(60);
                            }
                        }, 400);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if (Math.abs(event.getY() - mLastY) > v.getMeasuredHeight()) {
                            onVoiceCancel();
                        } else {
                            onVoiceEnd();
                        }
                        mVoiceInputRtv.setText("按住  说话");
                        mVoiceInputRtv.setBackgroundColor(Color.WHITE);
                        mVoiceStateLl.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(event.getY() - mLastY) > v.getMeasuredHeight()) {
                            onVoiceStateCancel();
                        } else {
                            onVoiceStateRec();
                        }
                        break;
                }
                return true;
            }


        });
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        LogUtils.d("name: " + name + "params: " + params);
        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
            RecogResult recogResult = RecogResult.parseJson(params);
            if (recogResult.isFinalResult()) {
                String[] results = recogResult.getResultsRecognition();
                mTalkAdapter.addData(new MessageBean(MessageBean.TYPE_MINE, results[0]));
            }
        }
    }

    /**
     * 未上滑处于录制状态
     */
    private void onVoiceStateRec() {
        mVoiceInputRtv.setText("松开  结束");
        mVoiceStateTv.setText("手指上滑,取消发送");
        mVoiceStateTv.setBackgroundColor(Color.TRANSPARENT);
        mVoiceStateIv.setImageResource(R.drawable.icon_microphone);
    }

    /**
     * 已上滑处于待取消状态
     */
    private void onVoiceStateCancel() {
        mVoiceInputRtv.setText("松开手指,取消发送");
        mVoiceStateTv.setText("松开手指,取消发送");
        mVoiceStateTv.setBackgroundColor(getResources().getColor(R.color.red_60));
        mVoiceStateIv.setImageResource(R.drawable.icon_cancle);
    }

    /**
     * 开始录音
     */
    private void onVoiceStart() {
        Map<String, Object> params = new LinkedHashMap<String, Object>();

        // 基于SDK集成2.1 设置识别参数
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);
        // params.put(SpeechConstant.NLU, "enable");
        // params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 0); // 长语音
        // params.put(SpeechConstant.IN_FILE, "res:///com/baidu/android/voicedemo/16k_test.pcm");
        // params.put(SpeechConstant.VAD, SpeechConstant.VAD_DNN);
        // params.put(SpeechConstant.PID, 1537); // 中文输入法模型，有逗号
        // 请先使用如‘在线识别’界面测试和生成识别参数。 params同ActivityRecog类中myRecognizer.start(params);

        voiceAutoCheck(params);

        String json = null; // 可以替换成自己的json
        json = new JSONObject(params).toString(); // 这里可以替换成你需要测试的json
        asr.send(SpeechConstant.ASR_START, json, null, 0, 0);
        LogUtils.d("onVoiceStart");
    }

    @SuppressLint("HandlerLeak")
    private void voiceAutoCheck(Map<String, Object> params) {
        // 自动检测错误
        (new AutoCheck(getApplicationContext(), new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    AutoCheck autoCheck = (AutoCheck) msg.obj;
                    synchronized (autoCheck) {
                        String message = autoCheck.obtainErrorMessage(); // autoCheck.obtainAllMessage();
                        ; // 可以用下面一行替代，在logcat中查看代码
                        Log.w("AutoCheckMessage", message);
                    }
                }
            }
        }, true)).checkAsr(params);
    }

    /**
     * 结束录音
     */
    private void onVoiceEnd() {
        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
        LogUtils.d("onVoiceEnd");
    }

    /**
     * 取消录音
     */
    private void onVoiceCancel() {
        asr.send(SpeechConstant.ASR_CANCEL, null, null, 0, 0);
        LogUtils.d("onVoiceCancel");
    }

    @Override
    protected void onPause() {
        super.onPause();
        onVoiceCancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        asr.unregisterListener(this);
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.

            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        } else {
            onVoiceStart();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
        onVoiceStart();
    }


    @OnClick({R.id.keyboard_input_btn, R.id.voice_input_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.keyboard_input_btn:
                mVoiceInputRtv.setVisibility(View.INVISIBLE);
                mTextInputLl.setVisibility(View.VISIBLE);
                KeyboardUtils.showSoftInput(mContentEt);
                break;
            case R.id.voice_input_btn:
                mTextInputLl.setVisibility(View.INVISIBLE);
                mVoiceInputRtv.setVisibility(View.VISIBLE);
                break;
        }
    }


    @OnClick(R.id.send_text_tv)
    public void onClickSendText() {
        String msg = mContentEt.getText().toString();
        if (!TextUtils.isEmpty(msg)) {
            mTalkAdapter.addData(new MessageBean(MessageBean.TYPE_MINE, msg));
            mContentEt.setText("");
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mTalkAdapter.addData(new MessageBean(MessageBean.TYPE_ROBOT, "晚上好，请问你需要什么物品?"));
                }
            }, 800);
        }
    }

    @OnClick(R.id.voice_input_rtv)
    public void onClickVoiceInput() {
    }


}
