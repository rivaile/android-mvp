package com.oldnum7.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oldnum7.R;
import com.oldnum7.App;

import java.util.List;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/9:39
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class UserAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

    public UserAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {

        Glide.with(App.getmContext())
                .load(item.getHeadImg())
                .into((ImageView) helper.getView(R.id.iv_item_avatar));

        helper.setText(R.id.tv_item_login, item.getUserName());

    }

}
