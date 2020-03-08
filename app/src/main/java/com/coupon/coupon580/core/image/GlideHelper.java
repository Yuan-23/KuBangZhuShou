package com.coupon.coupon580.core.image;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coupon.coupon580.R;

/**
 * @author NoOne 2019.04.06
 */
public class GlideHelper {

    public static void loadImage(Context context, Object path, ImageView view) {
        loadImage(context, path, R.drawable.default_image, view);
    }

    public static void loadImage(Context context, Object path, @DrawableRes int placeholder, ImageView view) {
        if (path == null) {
            return;
        }
        GlideApp.with(context)
                .load(path)
                .centerCrop()
                .placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(view);
    }

}
