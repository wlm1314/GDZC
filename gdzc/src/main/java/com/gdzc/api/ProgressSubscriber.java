package com.gdzc.api;


import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import base.lib.util.ToastUtils;
import base.lib.widget.progress.ProgressCancelListener;
import base.lib.widget.progress.ProgressDialogHandler;
import rx.Subscriber;

/**
 * Created by 王少岩 on 2017/2/5.
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressSubscriber() {
        mProgressDialogHandler = new ProgressDialogHandler(this, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    protected void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Logger.d("我已经完成了");
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
            ToastUtils.showToast("请求超时,请重试");
        } else if (e instanceof JsonSyntaxException) {
            ToastUtils.showToast("数据解析错误");
        } else if(e instanceof ApiException){
            ToastUtils.showToast(e.getMessage());
        }
        Logger.e(e.getMessage());
        dismissProgressDialog();
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}