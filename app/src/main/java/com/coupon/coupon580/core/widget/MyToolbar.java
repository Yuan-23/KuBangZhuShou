package com.coupon.coupon580.core.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.coupon.coupon580.R;


/**
 * Created by NoOne on 2017/6/22.
 */

public class MyToolbar extends FrameLayout {

    private ImageView mIvBack;
    private TextView mTvTitle;
    private LinearLayout mLlRight;
    private boolean mIsLightMode;


    public MyToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolbar(Context context) {
        super(context);
    }

    public MyToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    public void setTitle(String s) {
        mTvTitle.setText(s);
    }

    public void removeBackBtn() {
        mIvBack.setVisibility(INVISIBLE);
        mIvBack.setOnClickListener(null);
    }


    private void initView(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.toolbar, this);
        mIvBack = (ImageView) findViewById(R.id.back_btn);
        mTvTitle = (TextView) findViewById(R.id.title);
        mLlRight = findViewById(R.id.ll_right);
        mIvBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyToolbar);
        try {
            if (!TextUtils.isEmpty(typedArray.getString(R.styleable.MyToolbar_title)))
                mTvTitle.setText(typedArray.getString(R.styleable.MyToolbar_title));
            mIsLightMode = typedArray.getBoolean(R.styleable.MyToolbar_light_mode, false);
        } finally {
            typedArray.recycle();
        }

        if (mIsLightMode) {
            mTvTitle.setTextColor(Color.WHITE);
            mIvBack.setColorFilter(Color.WHITE);
        }

        setBackgroundColor(Color.WHITE);
    }

    public void addRightView(View view) {
        mLlRight.addView(view);
    }

    public void addRightText(String text, OnClickListener onClickListener) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(16);
        textView.setOnClickListener(onClickListener);
        mLlRight.addView(textView);
    }

    public void addRightIcon(@DrawableRes int drawableId, OnClickListener onClickListener) {
        ImageView imageView = new ImageView(getContext());
        int width = ConvertUtils.dp2px(24);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(width, width));
        imageView.setImageResource(drawableId);
        imageView.setOnClickListener(onClickListener);
        mLlRight.addView(imageView);
    }
}
