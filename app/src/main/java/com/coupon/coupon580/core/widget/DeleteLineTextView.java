package com.coupon.coupon580.core.widget;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author NoOne 2019.03.09
 */
public class DeleteLineTextView extends AppCompatTextView {
    public DeleteLineTextView(Context context) {
        this(context, null);
    }

    public DeleteLineTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeleteLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setPaintFlags(getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
