package com.oldnum7.adpter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.oldnum7.R;
import com.oldnum7.UserEntity;
import com.oldnum7.adpter.base.BaseQuickAdapter;
import com.oldnum7.adpter.base.BaseViewHolder;
import com.oldnum7.base.App;

import java.util.List;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/9:39
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class UserAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {

    public UserAdapter(@LayoutRes int layoutResId, @Nullable List<UserEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserEntity item) {

        Glide.with(App.getmContext())
                .load(item.getAvatar_url())
                .into((ImageView) helper.getView(R.id.iv_item_avatar));

        helper.setText(R.id.tv_item_login, item.getLogin());

    }

}
