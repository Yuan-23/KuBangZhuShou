package com.coupon.coupon580.core.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.adapter.SimpleFragmentPagerAdapter;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.home.HomePageFragment;
import com.coupon.coupon580.hot.HotFragment;
import com.coupon.coupon580.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.container_vp)
    ViewPager mContainerVp;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationViewEx mBottomNavigationBar;
   /* @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;*/
    private long mLastClickBackTime = 0L;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mBottomNavigationBar.setTextSize(12);
        mBottomNavigationBar.enableShiftingMode(false);
        mBottomNavigationBar.enableItemShiftingMode(false);
        mBottomNavigationBar.enableAnimation(false);
        mBottomNavigationBar.setItemIconTintList(null);
        mBottomNavigationBar.setTextTintList(1,null);
    }

    @Override
    public void initData() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HotFragment.newInstance());
        fragmentList.add(HomePageFragment.newInstance());
//        fragmentList.add(SearchActivity.newInstance());
        fragmentList.add(MineFragment.newInstance());

        SimpleFragmentPagerAdapter fragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mContainerVp.setAdapter(fragmentPagerAdapter);
        mContainerVp.setOffscreenPageLimit(3);
        mBottomNavigationBar.setupWithViewPager(mContainerVp);
        /*mBottomBar.setContainer(R.id.container_vp)
                .setTitleBeforeAndAfterColor("#f6170b", "#f6170b")
                .addItem(HotFragment.class,
                        "首页",
                        R.drawable.icon_home,
                        R.drawable.icon_home)
                .addItem(HomePageFragment.class,
                        "分类",
                        R.drawable.icon_category,
                        R.drawable.ic_category_s)
                .addItem(MineFragment.class,
                        "我的",
                        R.drawable.icon_mine,
                        R.drawable.ic_mine_s)
                .build();*/
    }

    @Override
    public void initListener() {
        mBottomNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                resetToDefaultIcon();
                switch (item.getItemId()) {
                case R.id.bottom_item_1:
                   item.setIcon(R.drawable.ic_home_s);
                   return true;
                case R.id.bottom_item_2:
                    item.setIcon(R.drawable.ic_category_s);
                    return true;
                case R.id.bottom_item_3:

                    item.setIcon(R.drawable.ic_mine_s);
                    return true;
           }
           return false;
       }

        });
    }
    public void resetToDefaultIcon() {
        MenuItem item_home =  mBottomNavigationBar.getMenu().findItem(R.id.bottom_item_1);
        item_home.setIcon(R.drawable.icon_home);
        MenuItem item_category =  mBottomNavigationBar.getMenu().findItem(R.id.bottom_item_2);
        item_category.setIcon(R.drawable.icon_category);
        MenuItem item_mine =  mBottomNavigationBar.getMenu().findItem(R.id.bottom_item_3);
        item_mine.setIcon(R.drawable.icon_mine);

    }
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastClickBackTime <= 2000L) {
            super.onBackPressed();
        } else {
            showToast("再按一次退出应用");
            mLastClickBackTime = System.currentTimeMillis();
        }
    }
}
