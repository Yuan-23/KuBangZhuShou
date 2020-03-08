package com.coupon.coupon580.core.data;

import com.coupon.coupon580.R;
import com.coupon.coupon580.core.model.ActivityBean;
import com.coupon.coupon580.core.model.CommodityBean;
import com.coupon.coupon580.core.model.NewCommodityBean;
import com.coupon.coupon580.mine.model.BillBean;
import com.coupon.coupon580.mine.model.CouponBean;
import com.coupon.coupon580.mine.model.ScoreBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author NoOne 2019.04.06
 */
public class CommonDataProvider {

    private static final String TAG = "CommonDataProvider";


    private static class InstanceHolder {
        private static CommonDataProvider sCommonDataProvider = new CommonDataProvider();
    }

    public static CommonDataProvider getInstance() {
        return InstanceHolder.sCommonDataProvider;
    }

    public CommonDataProvider() {
        initData();
    }


    static class SimpleCommodity {
        public String name;
        public String url;
        public String intro;

        public SimpleCommodity(String name, String url, String intro) {
            this.name = name;
            this.url = url;
            this.intro = intro;
        }
    }

    // 轮播图
    private List<Object> mBannerUrls = new ArrayList<>();
    // 商品
    private List<SimpleCommodity> mCommodities = new ArrayList<>();
    // 商品详情图
    private List<String> mCommodityDetailUrls = new ArrayList<>();
    // 品牌名
    private List<String> mBandNames = new ArrayList<>();
    // 活动封面
    private List<String> mActCovers = new ArrayList<>();

    private static Random mRandom = new Random(System.currentTimeMillis());

    private long mLastSyncDataTime = 0;

    private List<CommodityBean> mSportsCommodityBeans = new CopyOnWriteArrayList<>();

    private List<CommodityBean> mEducationCommodityBeans = new CopyOnWriteArrayList<>();

    private List<CommodityBean> mMakeupCommodityBeans = new CopyOnWriteArrayList<>();

    private List<CommodityBean> mBookCommodityBeans = new CopyOnWriteArrayList<>();

    /**
     * 初始化数据
     */
    private void initData() {
//        syncData(null);

        mBannerUrls.add(R.drawable.banner);
        mBannerUrls.add(R.drawable.jpny1);
        mBannerUrls.add(R.drawable.jpmy2);
        mBannerUrls.add(R.drawable.zxjy1);
        mBannerUrls.add(R.drawable.zxjy2);
        mBannerUrls.add(R.drawable.mrmz1);
        mBannerUrls.add(R.drawable.mrmz2);
        mBannerUrls.add(R.drawable.ydjs1);
        mBannerUrls.add(R.drawable.ydjs2);
//        mBannerUrls.add(R.drawable.shuji2);
//        mBannerUrls.add(R.drawable.shuji3);
//        mBannerUrls.add(R.drawable.yundong1);
//        mBannerUrls.add(R.drawable.yundong2);
//        mBannerUrls.add(R.drawable.yundong3);
//        mBannerUrls.add(R.drawable.zaixianjiaoyu1);
//        mBannerUrls.add(R.drawable.zaixianjiaoyu2);
//        mBannerUrls.add(R.drawable.zaixianjiaoyu3);


        mCommodities.add(new SimpleCommodity("AEAC夹克男外套",
                "https://img11.360buyimg.com/n8/jfs/t20329/348/2462125706/144360/1c6569e/5b5745fbN195b21bc.jpg",
                "2019春季新款时尚休闲立领茄克衫加厚保暖男装韩版修身潮流上衣帅气"));
        mCommodities.add(new SimpleCommodity("吉普(JEEP)夹克",
                "https://img10.360buyimg.com/n8/jfs/t30673/182/115367087/106399/8a3b8585/5be7ee1dNb77e1cc4.jpg",
                "男薄款外套男装男士立领茄克衫防风衣2019春秋新品男装上衣"));
        mCommodities.add(new SimpleCommodity("LHW 外套",
                "https://img11.360buyimg.com/n8/jfs/t1/9573/14/14759/516729/5c694855Ebb7665df/e677d1d3404b7dd7.jpg",
                "男春秋季男装韩版修身圆领男士夹克衫男生潮流春装衣服简约短外套休闲棒球服褂子上衣"));
        mCommodities.add(new SimpleCommodity("BOYXCO春装",
                "https://img14.360buyimg.com/n8/jfs/t1/21673/27/10539/339776/5c886517E153d5d17/17e2b0c5ceb3e875.jpg",
                "男士春季外套男春秋立领棒球服男生韩版修身休闲工装短款夹克男春装大码薄款衣服男装外套"));
        mCommodities.add(new SimpleCommodity("梦秀琳连衣裙",
                "https://img10.360buyimg.com/n8/jfs/t1/30298/15/6831/109335/5c90d5a4Eec546958/f5ab3421f3601803.jpg",
                "2019夏新品女装春新款夏韩版显瘦时尚套装女士大码性感长袖打底两件套蕾丝套装雪纺连衣裙"));
        mCommodities.add(new SimpleCommodity("361度 男鞋跑步鞋",
                "https://img13.360buyimg.com/n7/jfs/t1/8131/34/15910/147853/5c737a35E08ff18d5/b3676927a933f7ef.jpg",
                "轻便减震透气运动鞋 671832270-3曜石黑/361度白 42"));
        mCommodities.add(new SimpleCommodity("安踏男鞋运动鞋",
                "https://img12.360buyimg.com/n7/jfs/t1/32296/40/9479/254421/5ca5d1d6E4aa7e5fb/a56f2e73715a6fbd.jpg",
                "男士2019春季网面正品跑步鞋休闲旅游轻便老爹鞋耐磨慢跑鞋子官方网店 -10黑/中灰/安踏白 42"));
        mCommodities.add(new SimpleCommodity(" 李宁（LI-NING）男鞋",
                "https://img14.360buyimg.com/n7/jfs/t1/28449/35/6620/201553/5c6223abEc6acc566/6728f957b45b5513.jpg",
                "春季一体织透气耐磨休闲减震新品跑步鞋轻便慢跑运动鞋子 -3新基础黑/银灰 42 (9)"));
        mCommodities.add(new SimpleCommodity("耐克NIKE 男子 跑步鞋 ",
                "https://img14.360buyimg.com/n7/jfs/t1/23994/6/1097/143328/5c0f5d6eEe9d3a801/8f416d29d5edbcfa.jpg",
                "FLEX EXPERIENCE RN 8 运动鞋 AJ5900-013黑色42码"));
        mCommodities.add(new SimpleCommodity("阿迪达斯 ADIDAS 男子 跑步系列",
                "https://img13.360buyimg.com/n7/jfs/t27058/101/1286149534/149812/eef7d5a7/5bc57509N2df2c556.jpg",
                "AEROBOUNCE 2 M 运动 跑步鞋 AC8180 43码 UK9码"));
        mCommodities.add(new SimpleCommodity("阿迪达斯 PureBOOST GO",
                "https://img12.360buyimg.com/n7/jfs/t1/26387/21/5724/147843/5c403fb7Edee82e62/fb8a4fb12003bec7.jpg",
                "ADIDAS 男子 跑步系列 PureBOOST GO 运动 跑步鞋 B37803 41码 UK7.5码"));
        mCommodities.add(new SimpleCommodity("安踏（ANTA）跑步鞋",
                "https://img14.360buyimg.com/n7/jfs/t30670/125/70647729/270064/7237a7af/5be6c56cNf11e7812.jpg",
                "男透气网面跑步鞋 简约缓震耐磨运动鞋 黑/安踏白 8(男41)"));
        mCommodities.add(new SimpleCommodity("李宁LI-NING运动服套装",
                "https://img10.360buyimg.com/n7/jfs/t1/19558/6/2411/166660/5c1c7f47E0c6ba0b3/eff55cf3c54e09b4.jpg",
                "男新款羽毛球服T恤短袖速干短裤春夏季乒乓服装 黑色N945-2+N721 XL"));
        mCommodities.add(new SimpleCommodity("乔丹 男装运动套装",
                "https://img14.360buyimg.com/n7/jfs/t12694/340/2354688823/308924/56be2958/5a3b6aacN5a2aa957.jpg",
                "男两件套针织卫衣卫裤 XWW1382534 灰花灰 L"));
        mCommodities.add(new SimpleCommodity("耐克NIKE 男裤 ",
                "https://img14.360buyimg.com/n7/jfs/t3307/64/370214200/132612/c376dae7/57b3ccfeN49d30b0f.jpg",
                "直筒裤 OH FT CLUB 长裤 804400-010黑色L码"));
        mCommodities.add(new SimpleCommodity("阿迪达斯 ADIDAS 男子长裤 ",
                "https://img11.360buyimg.com/n7/jfs/t1/25274/37/4782/117119/5c356d2fE78efc8c5/7d91f107c6ed0c60.jpg",
                " ADIDAS 男子 足球系列 TIRO19 TR PNT 运动 长裤 D95958 XL码"));


        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t25318/226/1244536152/182181/938b200b/5b8e20eeN13277edb.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t26440/124/308078526/198886/6e3193fd/5b8e20edNdba260ac.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t25804/39/1215304700/181012/b5d629ec/5b8e20eeN23a3c1e2.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t26332/141/310401663/144253/ebcabb06/5b8e20eeN6f0e7a7c.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t26473/147/328283306/192325/d43f0a74/5b8e20eeN6d016fe1.jpg");
        mCommodityDetailUrls.add("https://img30.360buyimg.com/popWaterMark/jfs/t25726/315/1242013109/142714/93079d7/5b8e2141N01497a61.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/cms/jfs/t1/20130/16/12347/1056828/5c98734aE1f266f36/79897b663b12e7bc.jpg");
        mCommodityDetailUrls.add("https://img30.360buyimg.com/popWaterMark/jfs/t24652/51/203634277/335903/91b5ebe7/5b691635Nf74aae2a.jpg");
        mCommodityDetailUrls.add("https://img30.360buyimg.com/popWaterMark/jfs/t25012/58/210778623/280623/94afa6ae/5b691635N540b0819.jpg");
        mCommodityDetailUrls.add("https://img30.360buyimg.com/popWaterMark/jfs/t25369/42/202427920/218477/d77e2d30/5b691635N7670dbcd.jpg");
        mCommodityDetailUrls.add("https://img30.360buyimg.com/popWaterMark/jfs/t24298/91/1725014865/270946/4cb41f7e/5b69178fN35ec20e6.jpg");
        mCommodityDetailUrls.add("https://img.alicdn.com/imgextra/i4/890482188/O1CN011S2947WTlHuAcw6_!!890482188.jpg");
        mCommodityDetailUrls.add("https://img.alicdn.com/imgextra/i3/1993730769/TB2EIZ_d5MnBKNjSZFzXXc_qVXa_!!1993730769.jpg_q90.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t1/7398/17/13297/130630/5c414598Ea7198b2f/3b2edfa9d9727a1e.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t1/15546/32/6274/155367/5c4acb16E5e9e00b6/922f64bca0f92cd3.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t1/1889/17/4599/199202/5b9ce2b8Eb6c1e8ed/0356140ffa0b48e4.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t1/9879/24/13091/180365/5c3ed5cbE3dd7b4a9/0fc330f0b6a2ca00.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t1/28182/34/5299/193977/5c3c3b76Ed3cf8885/b368d2c78c719bd0.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t1/21352/3/5256/168422/5c3c3b76E6825313a/5dfccf1d907b7316.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t1/10872/1/6595/128379/5c21ee1dEff67d112/13bcab5b925f82d2.jpg");
        mCommodityDetailUrls.add("https://img12.360buyimg.com/popWaterMark/jfs/t1/22864/17/3280/214227/5c25c90bEc375f9aa/68b1f9b2eafc20c9.jpg");
        mCommodityDetailUrls.add("https://img.alicdn.com/imgextra/i1/2942806244/O1CN01JGauRC1vznWTyJduR_!!2942806244.jpg");
        mCommodityDetailUrls.add("https://img.alicdn.com/imgextra/i2/2942806244/O1CN01oqctxE1vznXbs0cPN_!!2942806244.jpg");
        mCommodityDetailUrls.add("https://img.alicdn.com/imgextra/i1/2942806244/O1CN01EOaLOy1vznWuETdGi_!!2942806244.jpg");
        mCommodityDetailUrls.add("https://img.alicdn.com/imgextra/i3/2942806244/O1CN010VCuMg1vznWrBpyWi_!!2942806244.jpg");
        mCommodityDetailUrls.add("https://img20.360buyimg.com/popWaterMark/jfs/t1/5623/22/7057/37553/5ba4a64cEc932f500/dd7c2a1838871259.jpg");
        mCommodityDetailUrls.add("https://img12.360buyimg.com/popWaterMark/jfs/t1/1165/24/7094/46233/5ba4a64cEb4f0f570/3b4e9b5832d04c3b.jpg");
        mCommodityDetailUrls.add("https://img13.360buyimg.com/popWaterMark/jfs/t1/5387/32/7131/19180/5ba4a64cE617742a6/ede74540d32758d2.jpg");
        mCommodityDetailUrls.add("https://img13.360buyimg.com/popWaterMark/jfs/t1/2157/13/6675/36920/5ba36ab7Ee8ff6401/1c49b8acb7a0b2be.jpg");
        mCommodityDetailUrls.add("https://img13.360buyimg.com/popWaterMark/jfs/t1/4356/8/6681/22029/5ba36ab7Edfcd4157/b3b0f9c243c2f4cd.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/popWaterMark/jfs/t1/29805/35/3295/46112/5c25c3f5Ef9d33030/095e75f46f481863.jpg");
        mCommodityDetailUrls.add("https://img10.360buyimg.com/imgzone/jfs/t21814/251/2209896855/297445/9de96825/5b4c6120Nd3ee153a.jpg");
        mCommodityDetailUrls.add("https://img30.360buyimg.com/popWaterMark/jfs/t1/18890/6/11282/96655/5c8f3b34E416791b0/f4b8697cf9f60898.jpg");
        mCommodityDetailUrls.add("http://img10.360buyimg.com/imgzone/jfs/t20680/156/1119344029/103392/9c174fc/5b20bcb9Nb5c4c891.jpg");
        mCommodityDetailUrls.add("http://img10.360buyimg.com/imgzone/jfs/t20059/118/1537895016/109433/f784bf34/5b20bcbbNfb61e88c.jpg");


        mBandNames.add("NIKE官方旗舰店");
        mBandNames.add("安踏官方网店");
        mBandNames.add("PUMA官方店");
        mBandNames.add("adidas官方旗舰店");
        mBandNames.add("特步官方旗舰店");
        mBandNames.add("CONVERSE官方旗舰店");
        mBandNames.add("361度官方旗舰店");
        mBandNames.add("JORDAN官方旗舰店");
        mBandNames.add("New Balance旗舰店");
        mBandNames.add("乔丹官方旗舰店");
        mBandNames.add("鸿星尔克官方旗舰店");
        mBandNames.add("匹克官方旗舰店");
        mBandNames.add("skechers运动旗舰店");
        mBandNames.add("vans官方旗舰店");


        mActCovers.add("https://img11.360buyimg.com/cms/jfs/t1/23077/13/14161/215967/5ca4b21eE1a949219/720f599d12224c4f.jpg!q90");
        mActCovers.add("https://img11.360buyimg.com/cms/jfs/t1/16523/22/14096/196749/5ca4b21eEebe5ccb2/c49497926eef4d21.jpg!q90");
        mActCovers.add("https://img11.360buyimg.com/cms/jfs/t1/20553/12/14118/235433/5ca4b21eEc91649ad/8fdfb452c1a5b2a7.jpg!q90");
        mActCovers.add("https://img10.360buyimg.com/imgzone/jfs/t1/19374/39/8043/616895/5c728231Ef7dcb463/975c67ddda5901fc.jpg");
        mActCovers.add("https://img10.360buyimg.com/imgzone/jfs/t1/25808/1/8151/110999/5c728740Ec7c7ad60/94595e56edd12760.jpg");
        mActCovers.add("https://img10.360buyimg.com/imgzone/jfs/t1/9065/38/15769/618309/5c728222E01ef05d8/4a1737a54824ed91.jpg");
        mActCovers.add("https://img10.360buyimg.com/imgzone/jfs/t1/32235/21/3295/328711/5c728221Ea527f0ca/895f32c7756a5509.jpg");
        mActCovers.add("https://img11.360buyimg.com/cms/jfs/t1/33242/35/1372/298972/5ca57526E91bf75e1/5e728811d74c53ce.jpg!q90");
        mActCovers.add("https://img20.360buyimg.com/cms/jfs/t1/24584/23/13600/368763/5ca17546Edb8b4328/58638705ff7a6221.jpg!q90");
        mActCovers.add("https://img13.360buyimg.com/cms/jfs/t1/29593/36/12919/698347/5c9ae6b3Ebee1131d/aafd273a55466bdb.jpg!q90");
        mActCovers.add("https://img30.360buyimg.com/cms/jfs/t1/25010/2/9917/102365/5c823bd3E7e728395/8c98b73e59594fdf.jpg!q90");
        mActCovers.add("https://img13.360buyimg.com/cms/jfs/t1/11757/22/15029/283443/5ca5a20aEb27312f9/1ace5702e515f7ed.jpg");
        mActCovers.add("https://img11.360buyimg.com/cms/jfs/t1/25517/34/8998/253281/5c7a6445E212881da/5ecd376bbe0d65ed.jpg");
        mActCovers.add("https://img12.360buyimg.com/cms/jfs/t1/23615/8/9244/264468/5c7ca413E5d056e69/93d4ae5fcaf6ba47.jpg");
        mActCovers.add("https://img14.360buyimg.com/cms/jfs/t1/15263/11/11964/712458/5c9441c8Eb81a9110/38213c7fa5fcc3f7.jpg");
        mActCovers.add("https://img11.360buyimg.com/cms/jfs/t1/27575/38/3311/346139/5c2598c3E6d5cf15c/e34bdf69271aeaf6.jpg");
        mActCovers.add("https://img13.360buyimg.com/cms/jfs/t1/36327/9/206/633463/5ca7f0daE03e91b4d/c4c0cb7110ffdca4.jpg!q90");
        mActCovers.add("https://img10.360buyimg.com/cms/jfs/t1/18489/21/10412/303369/5c8763acE00594d13/8dbb16bc30126e3a.jpg");
    }


    public List<CommodityBean> getCommodityList(int size) {
        if (size <= 0) {
            size = mRandom.nextInt(4) + 1;
        }
        List<CommodityBean> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(getCommodity());
        }
        return data;
    }

    public CommodityBean getCommodity() {
        CommodityBean bean = new CommodityBean();
        SimpleCommodity simpleCommodity = mCommodities.get(mRandom.nextInt(mCommodities.size()));
        bean.setName(simpleCommodity.name);
        bean.setThumbnail(simpleCommodity.url);
        bean.setIntro(simpleCommodity.intro);
        bean.setStartTime(getRandomStartTime());
        bean.setEndTime(getRandomEndTime());
        setPrice(bean);
        bean.setSoldOutNum(mRandom.nextInt(2000));
        bean.setBannerImgs(getCommodityUrls(0));
        bean.setDetailImgs(getCommodityUrls(0));

        return bean;
    }

    private static void setPrice(CommodityBean bean) {
        // 55~995
        int base = mRandom.nextInt(189) + 11;
        bean.setPriceBefore(base * 5 + 4.99D);
        // 55~(base-5)
        int after = mRandom.nextInt(base - 9) + 11;
        bean.setPriceAfter(after * 5 + 4.99D);
        bean.setCouponPrice((base - after) * 5);
    }

    private static long getRandomStartTime() {
        int nextInt = mRandom.nextInt(5184);
        return System.currentTimeMillis() - nextInt * 1000000L;
    }

    private static long getRandomEndTime() {
        int nextInt = mRandom.nextInt(5184);
        return System.currentTimeMillis() + nextInt * 1000000L;
    }

    private String getCommodityUrls(int size) {
        if (size <= 0) {
            size = mRandom.nextInt(4) + 2;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            sb.append(mCommodityDetailUrls.get(mRandom.nextInt(mCommodityDetailUrls.size()))).append(",");
        }
        sb.append(mCommodityDetailUrls.get(mRandom.nextInt(mCommodityDetailUrls.size())));
        return sb.toString();
    }

    public List<Object> getBannerUrls(int size) {
        if (size <= 0) {
            size = mRandom.nextInt(5) + 4;
        }
        List<Object> data = new ArrayList<>();
        //data.add(R.drawable.banner);
        for (int i = 0; i < size; i++) {
            data.add(mBannerUrls.get(i));
        }
        return data;
    }

    /*public List<ActivityBean> getActivityList(int size) {
        if (size <= 0) {
            size = mRandom.nextInt(8) + 1;
        }
        List<ActivityBean> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(getActivityBean(3));
        }
        return data;
    }*/

    public ActivityBean getActivityBean(int commoditySize) {
        if (commoditySize <= 0) {
            commoditySize = mRandom.nextInt(6) + 3;
        }
        ActivityBean bean = new ActivityBean();
        bean.setName(mBandNames.get(mRandom.nextInt(mBandNames.size())));
        bean.setUrl(mActCovers.get(mRandom.nextInt(mActCovers.size())));
        bean.setStartTime(getRandomStartTime());
        bean.setEndTime(getRandomEndTime());
        bean.setRecommendCommodity(getCommodityList(commoditySize));
        return bean;
    }

    private Comparator<BillBean> mBillBeanComparator = new Comparator<BillBean>() {
        @Override
        public int compare(BillBean o1, BillBean o2) {
            return (o2.getTime() - o1.getTime()) > 0L ? 1 : -1;
        }
    };


    public List<BillBean> getBillList(int size) {
        if (size <= 0) {
            size = mRandom.nextInt(16) + 8;
        }
        List<BillBean> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BillBean bean = new BillBean();
            int moneyNum = mRandom.nextInt(100) + 1;
            bean.setMoneyNum(moneyNum / 100D);
            bean.setType(mRandom.nextInt(2));
            bean.setTime(getRandomStartTime());
            data.add(bean);
        }

        Collections.sort(data, mBillBeanComparator);

        return data;
    }

    public List<ScoreBean> getScoreList(int size) {
        if (size <= 0) {
            size = mRandom.nextInt(16) + 8;
        }
        List<ScoreBean> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ScoreBean bean = new ScoreBean();
            int moneyNum = mRandom.nextInt(100) + 1;
            bean.setScoreNum(10);
            bean.setTime(getRandomStartTime());
            data.add(bean);
        }
        //Collections.sort(data, mBillBeanComparator);

        return data;
    }


    public List<CouponBean> getCouponList(int size) {
        if (size <= 0) {
            size = mRandom.nextInt(8) + 8;
        }
        List<CouponBean> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            CouponBean bean = new CouponBean();
            bean.setStartTime(getRandomStartTime());
            bean.setEndTime(getRandomEndTime());
            bean.setName(mBandNames.get(mRandom.nextInt(mBandNames.size())));
            bean.setCouponMoney((mRandom.nextInt(20) + 1) * 5);
            bean.setIntro(bean.getCouponMoney() % 10 == 5 ? "无门槛" : "满" + bean.getCouponMoney() / 2 + "元");
            data.add(bean);
        }
        return data;
    }

    public List<NewCommodityBean> getNewCommodityList(int size) {
        if (size <= 0) {
            size = mRandom.nextInt(8) + 8;
        }
        List<NewCommodityBean> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            NewCommodityBean bean = new NewCommodityBean();
            bean.setName(mBandNames.get(mRandom.nextInt(mBandNames.size())));
            bean.setLink("null");
            bean.setStatus("1");
            data.add(bean);
        }
        return data;
    }

    public List<NewCommodityBean> getNewCommodityList2(int size) {
        if (size <= 0) {
            size = mRandom.nextInt(8) + 8;
        }
        List<NewCommodityBean> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            NewCommodityBean bean = new NewCommodityBean();
            bean.setName(mBandNames.get(mRandom.nextInt(mBandNames.size())));
            bean.setLink("null");
            bean.setStatus("2");
            data.add(bean);
        }
        return data;
    }

}
