package com.coupon.coupon580.mine.activity;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.data.SharedPreConstants;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.image.GlideHelper;
import com.coupon.coupon580.core.model.SimpleResponse;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.core.utils.SharedPreUtil;
import com.coupon.coupon580.core.widget.MyToolbar;
import com.coupon.coupon580.core.widget.SquareImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateCouponActivity extends BaseActivity {

    private static final int ALBUM_REQUEST_CODE = 100;

    @BindView(R.id.coupon_title_edit)
    EditText cTitle;
    @BindView(R.id.coupon_attr_edit)
    EditText cAttr;
    @BindView(R.id.coupon_content_edit)
    EditText cContent;
    @BindView(R.id.coupon_picture)
    LinearLayout cPicture;
    @BindView(R.id.coupon_picture_hint)
    TextView cPictureText;
    @BindView(R.id.coupon_address_edit)
    EditText cAddress;
    @BindView(R.id.Coupon_send_iv1)
    SquareImageView cImage1;
    @BindView(R.id.Coupon_send_iv2)
    SquareImageView cImage2;
    @BindView(R.id.Coupon_send_iv3)
    SquareImageView cImage3;
    @BindView(R.id.Coupon_send_iv4)
    SquareImageView cImage4;
    @BindView(R.id.Coupon_send_iv5)
    SquareImageView cImage5;
    @BindView(R.id.tool_bar)
    MyToolbar cToolBar;
    @BindView(R.id.coupon_check)
    TextView cCheckbtn;



    private List<File> images = new ArrayList<>(5);
    private int count = 1;




    @Override
    public int getLayoutId() {
        return R.layout.activity_create_coupon;
    }

    @Override
    public void initView() {
        cToolBar.setPadding(0, getStatusBarHeight(), 0, 0);
        cImage1.setVisibility(View.VISIBLE);
        cImage2.setVisibility(View.INVISIBLE);
        cImage3.setVisibility(View.INVISIBLE);
        cImage4.setVisibility(View.INVISIBLE);
        cImage5.setVisibility(View.INVISIBLE);
        initImage();
    }

    private void initImage(){
        GlideHelper.loadImage(mActivity,
                R.drawable.default_image,
                R.color.color_efefef,
                cImage1);
        GlideHelper.loadImage(mActivity,
                R.drawable.default_image,
                R.color.color_efefef,
                cImage2);
        GlideHelper.loadImage(mActivity,
                R.drawable.default_image,
                R.color.color_efefef,
                cImage3);
        GlideHelper.loadImage(mActivity,
                R.drawable.default_image,
                R.color.color_efefef,
                cImage4);
        GlideHelper.loadImage(mActivity,
                R.drawable.default_image,
                R.color.color_efefef,
                cImage5);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.coupon_upload_btn)
    public void onClickCPicture() {
        getPicFromAlbum();

    }

    @OnClick(R.id.coupon_check)
    public void onClickCCreat() {
        if (TextUtils.isEmpty(cTitle.getText().toString())) {
            showToast("请输入优惠券名称");
        }
        else if (TextUtils.isEmpty(cAttr.getText().toString())) {
            showToast("请选择优惠券标签");
        }
        else if (TextUtils.isEmpty(cContent.getText().toString())) {
            showToast("请输入优惠券内容");
        }
        else if (TextUtils.isEmpty(cAddress.getText().toString())) {
            showToast("请输入优惠券地址");
        }
        else {
            OkGo.<SimpleResponse>post(UrlConstants.userSendInfo)
                    .params("title", cTitle.getText().toString())
                    .params("keyword", cAttr.getText().toString())
                    .params("intro", cContent.getText().toString())
                    .params("link", cAddress.getText().toString())
                    .addFileParams("file", images)
                    .execute(new GsonCallback<SimpleResponse>(SimpleResponse.class) {
                        @Override
                        public void onSuccess(Response<SimpleResponse> response) {
                            SimpleResponse body = response.body();
                            if (body.isSuccess()) {
                                showToast("创建成功");
                                //cCheckbtn.setEnabled(false);
                                finish();
                            } else {
                                showToast("发布失败，请检查网络或者重新登陆");
                            }
                        }

                        @Override
                        public void onError(Response<SimpleResponse> response) {
                            super.onError(response);
//                        showToast(getResources().getString(R.string.network_error));
                        }
                    });

            CountDownTimer timer = new CountDownTimer(10 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    cCheckbtn.setEnabled(false);
                    cCheckbtn.setText(millisUntilFinished / 1000 + "s");

                }

                @Override
                public void onFinish() {
                    cCheckbtn.setEnabled(true);
                    cCheckbtn.setText("发布");

                }
            }.start();
        }
    }


    /**
     * 从相册获取图片
     */

    private void getPicFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, ALBUM_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALBUM_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.d(TAG, "onActivityResult: " + data);
            Uri pictureUri = getUri(data);
            if (pictureUri == null) {
                return;
            }
            //获取图片的路径：
            String[] photoPath = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(pictureUri, photoPath, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            displayImage(path);
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    /**
     * 解决小米手机上获取图片路径为null的情况
     *
     * @param intent
     * @return
     */
    public Uri getUri(android.content.Intent intent) {
        Uri uri = intent.getData();
        String type = null;
        try {
            type = intent.getType();

            if (uri.getScheme().equals("file") && (type.contains("image/"))) {
                String path = uri.getEncodedPath();
                if (path != null) {
                    path = Uri.decode(path);
                    ContentResolver cr = this.getContentResolver();
                    StringBuffer buff = new StringBuffer();
                    buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                            .append("'" + path + "'").append(")");
                    Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            new String[]{MediaStore.Images.ImageColumns._ID},
                            buff.toString(), null, null);
                    int index = 0;
                    for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                        index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                        // set _id value
                        index = cur.getInt(index);
                    }
                    if (index == 0) {
                        // do nothing
                    } else {
                        Uri uri_temp = Uri
                                .parse("content://media/external/images/media/"
                                        + index);
                        if (uri_temp != null) {
                            uri = uri_temp;
                        }
                    }
                }
            }
            return uri;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void displayImage(String imagePath) {
        File cImg = new File(imagePath);
        images.add(cImg);
        SharedPreUtil.putStringValue(mActivity, SharedPreConstants.USER_AVATAR_URL, imagePath);
        RefreshImage();
    }

    private void RefreshImage(){
        switch (count) {
            case 1:
                setImages(images,cImage1,cImage2);
                break;
            case 2:
                setImages(images,cImage2,cImage3);
                break;
            case 3:
                setImages(images,cImage3,cImage4);
                break;
            case 4:
                setImages(images,cImage4,cImage5);
                break;
            case 5:
                setImages(images,cImage5);
                break;
        }
        count =count +1;
    }

    private void setImages(List<File> images, SquareImageView cImage,  SquareImageView cImagee){
        if (images.get(count-1) != null) {
            GlideHelper.loadImage(mActivity,
                    images.get(count-1),
                    R.color.color_efefef,
                    cImage);
            cPictureText.setText("已上传"+count+"/5张");
            cImagee.setVisibility(View.VISIBLE);
        }
    }

    private void setImages(List<File> images, SquareImageView cImage){
        if (images.get(count-1) != null) {
            GlideHelper.loadImage(mActivity,
                    images.get(count-1),
                    R.color.color_efefef,
                    cImage);
            cPictureText.setText("已上传"+count+"/5张");
        }
    }


}
