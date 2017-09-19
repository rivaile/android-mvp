package com.oldnum7.androidlib.mvp.status;

import android.view.View;


public interface OnShowHideViewListener {

    void onShowView(View view, int id);

    void onHideView(View view, int id);
}
