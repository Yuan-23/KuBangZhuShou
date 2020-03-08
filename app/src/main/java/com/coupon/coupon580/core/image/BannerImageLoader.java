package com.coupon.coupon580.core.image;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by NoOne on 2017/7/24.
 */

public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideHelper.loadImage(context, path, imageView);
    }
}

