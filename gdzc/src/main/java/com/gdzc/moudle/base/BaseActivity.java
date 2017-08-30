package com.gdzc.moudle.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.gdzc.R;
import com.gdzc.BR;
import com.gdzc.app.App;
import com.gdzc.injector.component.ActivityComponent;
import com.gdzc.injector.component.AppComponent;
import com.gdzc.injector.component.DaggerActivityComponent;
import com.gdzc.injector.module.ActivityModule;
import com.gdzc.widget.EmptyLayout;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import base.lib.util.ActivityManager;
import base.lib.util.ToastUtils;

/**
 * 王少岩 在 2017/3/10 创建了它
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends RxAppCompatActivity implements IBaseView {
    protected B mBinding;
    private long mClickTime = 0l;
    private static int EXIT_TIMEOUT = 2500;
    protected ActivityComponent mActivityComponent;

    /**
     * 把 EmptyLayout 放在基类统一处理，@Nullable 表明 View 可以为 null
     */
    @Nullable
    protected EmptyLayout mEmptyLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        Logger.d("当前activity："+this);
        mBinding = DataBindingUtil.setContentView(this, attachLayoutRes());
        mActivityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();
        initInjector();
        initViews();
        updateViews(false);
        overridePendingTransition(R.anim.zoom_in_entry, R.anim.zoom_in_exit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.setCurrentActivity(this);
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews(boolean isRefresh);

    /**
     * 初始化 Toolbar
     */
    protected void initToolBar(ViewDataBinding dataBinding, AppBar appBar) {
        dataBinding.setVariable(BR.appbar, appBar);
    }

    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showNetError(final EmptyLayout.OnRetryListener onRetryListener) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(onRetryListener);
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void finishRefresh() {
    }

    /**
     * 获取 AppComponent
     *
     * @return AppComponent
     */
    protected AppComponent getAppComponent() {
        return App.getAppComponent();
    }

    /**
     * 获取ActivityComponent
     *
     * @return mActivityComponent
     */
    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    /**
     * 判断是否是栈底
     *
     * @return
     */
    private boolean isRoot() {
        return isTaskRoot() || (getParent() != null && getParent().isTaskRoot());
    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (isRoot() && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            long second = System.currentTimeMillis();
            if (second - mClickTime < EXIT_TIMEOUT) {
                finish();
                return true;
            } else {
                mClickTime = second;
                ToastUtils.showToast("再按一次返回键退出");
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.zoom_in_entry, R.anim.zoom_in_exit);
    }

    protected void setOnClickListener(View.OnClickListener listener, View... views) {
        if (listener != null)
            for (View view : views) {
                view.setOnClickListener(listener);
            }
    }
}
