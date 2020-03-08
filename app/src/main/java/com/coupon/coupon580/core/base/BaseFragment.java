package com.coupon.coupon580.core.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    protected String TAG = this.getClass().getSimpleName();
    protected AppCompatActivity mActivity;
    protected Fragment mFragment;
    protected Unbinder unbinder;
    protected boolean isPrepared;
    protected boolean isVisible;
    protected boolean isLoaded;
    protected boolean isStatusBarTextBlack = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (AppCompatActivity) getActivity();
        mFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity).inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view, savedInstanceState);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        initData(savedInstanceState);
        initListener();
        Log.d(TAG, "onViewCreated");
    }


    protected abstract int getLayoutId();

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initListener();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
        if (unbinder != null)
            unbinder.unbind();
        isLoaded = false;
        isPrepared = false;
        OkGo.getInstance().cancelTag(mFragment);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        setStatusBarTextColor();
    }


    public void showToast(String s) {
        ToastUtils.showShort(s);
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(mActivity, cls);
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(mActivity, cls);
        startActivityForResult(intent, requestCode);
    }

    /**
     * Android 6.0 以上设置状态栏颜色
     */
    protected void setStatusBarTextColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (mActivity == null) {
                return;
            }
            // 如果亮色，设置状态栏文字为黑色
            if (isStatusBarTextBlack) {
                mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }

    }

    public void setStatusBarTextBlack(boolean statusBarTextBlack) {
        isStatusBarTextBlack = statusBarTextBlack;
    }

    /**
     * 获取状态栏高度
     *
     * @return height
     */
    protected int getStatusBarHeight() {
        int height = 0;
        int resourceId = mActivity.getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = mActivity.getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }
}
