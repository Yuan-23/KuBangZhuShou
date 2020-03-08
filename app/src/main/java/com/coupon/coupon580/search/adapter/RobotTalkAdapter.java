package com.coupon.coupon580.search.adapter;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coupon.coupon580.R;
import com.coupon.coupon580.search.model.MessageBean;

/**
 * @author NoOne 2019.03.24
 */
public class RobotTalkAdapter extends BaseMultiItemQuickAdapter<MessageBean, BaseViewHolder> {

    public RobotTalkAdapter() {
        super(null);
        addItemType(MessageBean.TYPE_ROBOT, R.layout.item_robot_msg);
        addItemType(MessageBean.TYPE_MINE, R.layout.item_mine_msg);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        switch (helper.getItemViewType()) {
            case MessageBean.TYPE_ROBOT:
                helper.setText(R.id.msg_tv, item.getMsg());
                break;
            case MessageBean.TYPE_MINE:
                helper.setText(R.id.msg_tv, item.getMsg());
                break;
            default:
                break;
        }
    }


    @Override
    public void addData(@NonNull MessageBean data) {
        mData.add(data);
        notifyItemInserted(mData.size() + getHeaderLayoutCount());
        getRecyclerView().smoothScrollToPosition(getItemCount());
    }
}
