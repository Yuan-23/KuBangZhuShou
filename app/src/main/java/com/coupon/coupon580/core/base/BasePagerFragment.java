package com.coupon.coupon580.core.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by NoOne on 2017/5/31 0031.
 */

public abstract class BasePagerFragment extends Fragment {

    public boolean isPrepared;
    public boolean isVisible;
    public boolean isLoaded;
    protected String TAG = this.getClass().getSimpleName();
    protected AppCompatActivity mActivity;
    protected Fragment mFragment;
    protected Unbinder unbinder;
    protected boolean isStatusBarTextBlack = true;

    /**
     * 获得全局的，防止使用getActivity()为空
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (AppCompatActivity) getActivity();
        mFragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {

        View view = LayoutInflater.from(mActivity)
                .inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view, savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     *
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 执行数据的加载
     */
    protected abstract void initData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //懒加载
        Log.d(TAG, "setUserVisibleHint " + isVisibleToUser);


        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            if (isPrepared && isLoaded)
                onInvisible();
        }
    }

    protected void onVisible() {
        if (!isLoaded) {
            lazyLoad();
            isLoaded = true;
        }
        setStatusBarTextColor();
    }

    protected void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        Log.d(TAG, "initData");
        initData();
        initListener();
    }

    protected void onInvisible() {

    }

    protected abstract void initListener();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
        if (unbinder != null)
            unbinder.unbind();
        isLoaded = false;
        isPrepared = false;
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
        if (isVisible) {
            setStatusBarTextColor();
        }
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
            Log.d(TAG, "setStatusBarTextColor: " + isStatusBarTextBlack);

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
        Log.d(TAG, "setStatusBarTextBlack: " + statusBarTextBlack);
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
