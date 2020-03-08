package com.coupon.coupon580.mine.activity;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.data.SharedPreConstants;
import com.coupon.coupon580.core.data.SharedPreDataHelper;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.event.UserInfoUpdateEvent;
import com.coupon.coupon580.core.image.GlideApp;
import com.coupon.coupon580.core.image.GlideHelper;
import com.coupon.coupon580.core.model.SimpleResponse;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.core.utils.SharedPreUtil;
import com.coupon.coupon580.core.widget.MyToolbar;
import com.coupon.coupon580.mine.model.UserInfoBean;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditInfoActivity extends BaseActivity {


    private static final int ALBUM_REQUEST_CODE = 100;

    @BindView(R.id.tool_bar)
    MyToolbar mToolBar;
    @BindView(R.id.avatar_civ)
    CircleImageView mAvatarCiv;
    @BindView(R.id.nickname_tv)
    EditText mNicknameEt;
    @BindView(R.id.phone_number_tv)
    TextView mPhoneNumberTv;
    @BindView(R.id.man_rb)
    RadioButton mManRb;
    @BindView(R.id.woman_rb)
    RadioButton mWomanRb;

    private File mHeadImg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_info;
    }

    @Override
    public void initView() {
        mToolBar.setPadding(0, getStatusBarHeight(), 0, 0);
        mToolBar.addRightText("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mNicknameEt.getText().toString())) {
                    showToast("用户名不能为空");
                    return;
                }
                /*else if (mHeadImg == null) {
                    showToast("请选择头像");
                    return;
                }*/
                OkGo.<SimpleResponse>post(UrlConstants.updateUserInfo)
                        .params("user_id", SharedPreDataHelper.getUserId())
                        .params("username", mNicknameEt.getText().toString())
                        .params("usersex", mManRb.isChecked() ? "男" : "女")
                        .params("user_logo", mHeadImg)
                        .isMultipart(true)
                        .execute(new GsonCallback<SimpleResponse>(SimpleResponse.class) {
                            @Override
                            public void onSuccess(Response<SimpleResponse> response) {
                                showToast(response.body().msg);
                                if (response.body().isSuccess()) {
                                    EventBus.getDefault().post(new UserInfoUpdateEvent());
                                    finish();
                                }
                            }

                            @Override
                            public void onError(Response<SimpleResponse> response) {
                                super.onError(response);
                                showToast(getResources().getString(R.string.network_error));
                            }
                        });
            }
        });
    }

    @Override
    public void initData() {
        UserInfoBean userInfoBean = SharedPreDataHelper.getUserInfoBean();
        if (userInfoBean != null) {
            GlideHelper.loadImage(mActivity,
                    userInfoBean.getUserAvatar(),
                    R.color.color_efefef,
                    mAvatarCiv);

            RequestManager requestManager = GlideApp.with(mActivity);
            RequestBuilder<File> fileRequestBuilder = requestManager.downloadOnly();
            fileRequestBuilder.listener(new RequestListener<File>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                    mHeadImg = resource;
                    return false;
                }
            });
            fileRequestBuilder.load(userInfoBean.getUserAvatar());
            fileRequestBuilder.preload();


            mNicknameEt.setText(userInfoBean.getName());
            if ("女".equals(userInfoBean.getUserSexId())) {
                mWomanRb.setChecked(true);
            } else {
                mManRb.setChecked(true);
            }
            mPhoneNumberTv.setText(userInfoBean.getUserPhone());
        }
    }

    @Override
    public void initListener() {
        PermissionUtils.permission(PermissionConstants.STORAGE).request();
    }

    @OnClick({R.id.choose_avatar_ll, R.id.nickname_ll, R.id.sex_ll, R.id.phone_number_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_avatar_ll:
                getPicFromAlbum();
                break;
            case R.id.nickname_ll:
                break;
            case R.id.sex_ll:
                break;
            case R.id.phone_number_ll:
                break;
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
        mHeadImg = new File(imagePath);
        GlideHelper.loadImage(mActivity,
                imagePath,
                R.color.color_efefef,
                mAvatarCiv);
        SharedPreUtil.putStringValue(mActivity, SharedPreConstants.USER_AVATAR_URL, imagePath);
    }


}
