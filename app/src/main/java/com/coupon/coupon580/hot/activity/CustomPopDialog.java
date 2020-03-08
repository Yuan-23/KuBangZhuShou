package com.coupon.coupon580.hot.activity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coupon.coupon580.R;

public class CustomPopDialog extends Dialog {
    public CustomPopDialog(Context context) {
        super(context);
    }

    public CustomPopDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private Drawable image;

        public Builder(Context context) {
            this.context = context;
        }

        public Drawable getImage() {
            return image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }

        public CustomPopDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CustomPopDialog dialog;
            dialog = new CustomPopDialog(context, R.style.Dialog );
            View layout = inflater.inflate(R.layout.item_dialog, null);
            dialog.addContentView(layout, new ActionBar.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT));
            dialog.setContentView(layout);
            ImageView img = (ImageView)layout.findViewById(R.id.img_qrcode);
            img.setImageDrawable(getImage());
            return dialog;
        }
    }

}
