package com.coupon.coupon580.hot.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.data.CommonDataProvider;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.image.BannerImageLoader;
import com.coupon.coupon580.core.image.GlideHelper;
import com.coupon.coupon580.core.model.NewCommodityBean;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.home.model.PageCommodityBean;
import com.coupon.coupon580.hot.activity.CommodityDetailActivity;
import com.coupon.coupon580.hot.activity.CommodityListActivity;
import com.coupon.coupon580.hot.activity.CustomPopDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * @author NoOne 2019.03.04
 */
public class HotMainHeaderView {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.hot_commodity_rv)
    RecyclerView mHotCommodityRv;

    private HotCommodityAdapter mCommodityAdapter;

    private View mView;
    private Context mContext;

    private int mCurrentPage = 1;

    public HotMainHeaderView(Context context) {
        mContext = context;
        mView = View.inflate(mContext, R.layout.hot_main_header_layout, null);
        ButterKnife.bind(this, mView);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mBanner.setImageLoader(new BannerImageLoader());
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);

        mCommodityAdapter = new HotCommodityAdapter();
        mCommodityAdapter.bindToRecyclerView(mHotCommodityRv);
        mCommodityAdapter.setEmptyView(R.layout.empty_view);
    }

    private void initData() {
        mBanner.setImages(CommonDataProvider.getInstance().getBannerUrls(9));
        mBanner.start();
        loadData();
    }

    private void loadData() {
        OkGo.<PageCommodityBean>get(UrlConstants.hotPage + "/" + mCurrentPage)
                .execute(new GsonCallback<PageCommodityBean>(PageCommodityBean.class) {
                    @Override
                    public void onSuccess(Response<PageCommodityBean> response) {
                        if (response.body().result == null){
                            loadData();
                        }
                        mCurrentPage++;
                        List<NewCommodityBean> result = response.body().result;
                        List<NewCommodityBean> data = new ArrayList<>();
                        if (result != null) {
                            for (NewCommodityBean newCommodityBean : result) {
                                if (newCommodityBean != null) {
                                    data.add(newCommodityBean);
                                }
                            }
                        }
                        if (data.isEmpty() || mCurrentPage == response.body().pageSum) {
                            mCurrentPage = 1;
                            return;
                        }
                        mCommodityAdapter.setNewData(data);
                    }

                    @Override
                    public void onError(Response<PageCommodityBean> response) {
                        super.onError(response);
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showQrcode(){
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.qrcode);
        CustomPopDialog.Builder dialogBuild = new CustomPopDialog.Builder(mContext);
        dialogBuild.setImage(drawable);
        CustomPopDialog dialog = dialogBuild.create();
        dialog.setCanceledOnTouchOutside(true);// 点击外部区域关闭
        dialog.show();
    }

    private void initListener() {
        mCommodityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewCommodityBean item = mCommodityAdapter.getItem(position);
                if (item != null) {
                    CommodityDetailActivity.startActivity(mContext, item.getCouponInfoId());
                }
            }
        });

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                showQrcode();
            }
        });
    }




    public View getView() {
        return mView;
    }

    @OnClick(R.id.header_bottom)
    public void onClickRef() {
        loadData();
    }


    @OnClick(R.id.banner)
    public void onClickBan(){
        showQrcode();
    }


    @OnClick({R.id.hot_more_tv})
    public void onCLickMoreBtn() {
        startActivity(CommodityListActivity.class);
    }


    private static class HotCommodityAdapter extends BaseQuickAdapter<NewCommodityBean, BaseViewHolder> {
        public HotCommodityAdapter() {
            super(R.layout.item_simple_commodity);
        }

        @Override
        protected void convert(BaseViewHolder helper, NewCommodityBean item) {
            GlideHelper.loadImage(mContext, item.getImage(), (ImageView) helper.getView(R.id.thumbnail_iv));
            helper.setText(R.id.name_tv, item.getName());
        }
    }

    public void startBanner() {
        mBanner.startAutoPlay();
    }

    public void stopBanner() {
        mBanner.stopAutoPlay();
    }
}
