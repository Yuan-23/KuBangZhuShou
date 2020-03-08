package com.coupon.coupon580.search.activity;

import android.content.Intent;

import android.text.TextUtils;

import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;

import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.bCallBack;

import butterknife.BindView;

/**
 * @author NoOne 2019.03.04
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_view)
    SearchView mSearchView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        setStatusBarTextColor();
        setStatusBarTextBlack(false);
       /* LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mSearchView.getLayoutParams();
        int statusBarHeight = getStatusBarHeight();
        layoutParams.height = layoutParams.height + statusBarHeight;
        mSearchView.setLayoutParams(layoutParams);
        mSearchView.setPadding(0, statusBarHeight, 0, 0);*/
        /*ImageView closeBtn = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeBtn.setColorFilter(getResources().getColor(R.color.color_42d3fd));*/
    }
    @Override
    public void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initListener() {
        mSearchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAction(String keyword,int type ) {
                onSearch(keyword,type);
            }

            /*@Override
            public void SearchAction_Catogory(String s1, String s2) {

            }*/
        });

        mSearchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAction() {
                finish();
            }
        });

    }


    private void onSearch(final String keyword,final int type) {
        if (TextUtils.isEmpty(keyword)) {
            showToast("请输入搜索关键词");
            return;
        }
        //KeyboardUtils.hideSoftInput(mSearchView);
        Intent intent = new Intent(mActivity, SearchResultActivity.class);
        intent.putExtra(SearchResultActivity.BUNDLE_KEY_WORD, keyword);
        intent.putExtra(SearchResultActivity.BUNDLE_TYPE, String.valueOf(type));
        startActivity(intent);
}



}
