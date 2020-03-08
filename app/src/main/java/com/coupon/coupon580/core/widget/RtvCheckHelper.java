package com.coupon.coupon580.core.widget;

import android.support.annotation.ColorInt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description: Rtv单选多选帮助类
 * @author: NoOne
 * @date: 2019-03-30 14:27
 */
public class RtvCheckHelper {

    public static final int SINGLE_MODE = 0;
    public static final int MULTI_MODE = 1;

    private int mMode;
    private RtvAppearance mCheckedAppearance;
    private RtvAppearance mUncheckedAppearance;

    public RtvCheckHelper(int mode, RtvAppearance checkedAppearance, RtvAppearance uncheckedAppearance) {
        mMode = mode;
        mCheckedAppearance = checkedAppearance;
        mUncheckedAppearance = uncheckedAppearance;
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

    public RtvAppearance getCheckedAppearance() {
        return mCheckedAppearance;
    }

    public void setCheckedAppearance(RtvAppearance checkedAppearance) {
        mCheckedAppearance = checkedAppearance;
    }

    public RtvAppearance getUncheckedAppearance() {
        return mUncheckedAppearance;
    }

    public void setUncheckedAppearance(RtvAppearance uncheckedAppearance) {
        mUncheckedAppearance = uncheckedAppearance;
    }

    private List<RoundTextView> mCheckedViews = new ArrayList<>();
    private List<RoundTextView> mUncheckedViews = new ArrayList<>();

    public void onClick(RoundTextView rtv) {
        if (mUncheckedViews.contains(rtv)) {
            onCheck(rtv);
        } else if (mCheckedViews.contains(rtv)) {
            onUncheck(rtv);
        }
    }

    /**
     * Add
     *
     * @param rtv
     */
    public void addView(RoundTextView rtv) {
        mUncheckedAppearance.decorate(rtv);
        mUncheckedViews.add(rtv);
    }

    /**
     * 选中
     *
     * @param rtv
     */
    public void onCheck(RoundTextView rtv) {
        if (mMode == SINGLE_MODE) {
            // 清空并重置选中列表
            if (!mCheckedViews.isEmpty()) {
                Iterator<RoundTextView> iterator = mCheckedViews.iterator();
                while (iterator.hasNext()) {
                    RoundTextView next = iterator.next();
                    mUncheckedAppearance.decorate(next);
                    mUncheckedViews.add(next);
                    iterator.remove();
                }
            }

            if (!mCheckedViews.contains(rtv)) {
                mCheckedAppearance.decorate(rtv);
                mCheckedViews.add(rtv);
            }
        } else if (mMode == MULTI_MODE) {
            if (!mCheckedViews.contains(rtv)) {
                mUncheckedViews.remove(rtv);
                mCheckedAppearance.decorate(rtv);
                mCheckedViews.add(rtv);
            }
        }
    }

    /**
     * 取消选中
     *
     * @param rtv
     */
    public void onUncheck(RoundTextView rtv) {
        if (mCheckedViews.remove(rtv)) {
            mUncheckedAppearance.decorate(rtv);
            mUncheckedViews.add(rtv);
        }
    }

    /**
     * 重置
     */
    public void reset() {
        if (!mCheckedViews.isEmpty()) {
            Iterator<RoundTextView> iterator = mCheckedViews.iterator();
            while (iterator.hasNext()) {
                RoundTextView next = iterator.next();
                mUncheckedAppearance.decorate(next);
                mUncheckedViews.add(next);
                iterator.remove();
            }
        }
    }

    public List<String> getCheckedTexts() {
        if (mCheckedViews.isEmpty()) {
            return null;
        }
        List<String> checkTexts = new ArrayList<>();
        for (RoundTextView checkedView : mCheckedViews) {
            checkTexts.add(checkedView.getText().toString());
        }

        return checkTexts;
    }

    public List<RoundTextView> getCheckedViews() {
        return mCheckedViews;
    }

    public List<RoundTextView> getUncheckedViews() {
        return mUncheckedViews;
    }

    /**
     * Rtv样式包装类
     */
    public static class RtvAppearance {

        @ColorInt
        private int mBackgroundColor;
        @ColorInt
        private int mBorderColor;
        @ColorInt
        private int mTextColor;

        public RtvAppearance(int backgroundColor, int borderColor, int textColor) {
            mBackgroundColor = backgroundColor;
            mBorderColor = borderColor;
            mTextColor = textColor;
        }

        public int getBackgroundColor() {
            return mBackgroundColor;
        }

        public void setBackgroundColor(int backgroundColor) {
            mBackgroundColor = backgroundColor;
        }

        public int getBorderColor() {
            return mBorderColor;
        }

        public void setBorderColor(int borderColor) {
            mBorderColor = borderColor;
        }

        public int getTextColor() {
            return mTextColor;
        }

        public void setTextColor(int textColor) {
            mTextColor = textColor;
        }

        public void decorate(RoundTextView rtv) {
            if (rtv != null) {
                rtv.setTextColor(mTextColor);
                rtv.setBackgroundAndBorderColor(mBackgroundColor, mBorderColor);
            }
        }
    }
}
