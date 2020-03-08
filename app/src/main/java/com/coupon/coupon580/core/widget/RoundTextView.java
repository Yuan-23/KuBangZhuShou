package com.coupon.coupon580.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.coupon.coupon580.R;


/**
 * @author NoOne 2019.03.23
 */
public class RoundTextView extends AppCompatTextView {

    private int mBorderWidth;
    private int mBorderColor;

    public RoundTextView(Context context) {
        this(context, null);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundTextView, defStyleAttr, 0);

        if (attributes != null) {

            mBorderWidth = attributes.getDimensionPixelSize(R.styleable.RoundTextView_rtvBorderWidth, 0);
            mBorderColor = attributes.getColor(R.styleable.RoundTextView_rtvBorderColor, Color.BLACK);
            float cornerRadius = attributes.getDimension(R.styleable.RoundTextView_rtvRadius, 0);
            int bgColor = attributes.getColor(R.styleable.RoundTextView_rtvBgColor, Color.TRANSPARENT);
            attributes.recycle();

            //创建drawable
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(bgColor);
            gradientDrawable.setCornerRadius(cornerRadius);
            if (mBorderWidth > 0) {
                gradientDrawable.setStroke(mBorderWidth, mBorderColor);
            }

            this.setBackground(gradientDrawable);
        }

    }


    @Override
    public void setBackgroundColor(int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) getBackground();
        if (gradientDrawable != null) {
            gradientDrawable.setColor(color);
            invalidate();
        }
    }

    public void setBorderWidth(int borderWidth) {
        GradientDrawable gradientDrawable = (GradientDrawable) getBackground();
        if (gradientDrawable != null && borderWidth > 0) {
            mBorderWidth = borderWidth;
            gradientDrawable.setStroke(mBorderWidth, mBorderColor);
            invalidate();
        }
    }

    public void setBorderColor(int borderColor) {
        GradientDrawable gradientDrawable = (GradientDrawable) getBackground();
        if (gradientDrawable != null) {
            mBorderColor = borderColor;
            gradientDrawable.setStroke(mBorderWidth, mBorderColor);
            invalidate();
        }
    }

    public void setCornerRadius(float cornerRadius) {
        GradientDrawable gradientDrawable = (GradientDrawable) getBackground();
        if (gradientDrawable != null) {
            gradientDrawable.setCornerRadius(cornerRadius);
        }
    }

    public void setBackgroundAndBorderColor(int backgroundColor, int borderColor) {
        GradientDrawable gradientDrawable = (GradientDrawable) getBackground();
        if (gradientDrawable != null) {
            gradientDrawable.setColor(backgroundColor);
            mBorderColor = borderColor;
            gradientDrawable.setStroke(mBorderWidth, mBorderColor);
            invalidate();
        }
    }
}
