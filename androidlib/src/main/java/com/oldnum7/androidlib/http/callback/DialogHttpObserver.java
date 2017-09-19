package com.oldnum7.androidlib.http.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Window;

import io.reactivex.annotations.NonNull;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/09/04/15:29
 *       desc   : 加载对话框...
 *       version: 1.0
 * </pre>
 */
public abstract class DialogHttpObserver<T> extends HttpObserver<T> implements DialogInterface.OnCancelListener {

    private static final String TAG = DialogHttpObserver.class.getSimpleName();
    private ProgressDialog dialog;

    private void initDialog(Activity activity) {
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
        dialog.setOnCancelListener(this);
    }

    public DialogHttpObserver(Activity activity) {
        initDialog(activity);
    }

    @Override
    protected void onStart() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Log.e(TAG, "network has bean canceled...");
        dispose();
    }
}
