package com.gdzc.moudle.home.view;


import com.gdzc.MainActivity;
import com.gdzc.R;
import com.gdzc.constant.Constants;
import com.gdzc.databinding.ActivitySplashBinding;
import com.gdzc.moudle.base.BaseActivity;

import base.lib.util.NavigateUtils;
import base.lib.util.PreferencesUtils;
import base.lib.util.RxHelper;
import rx.Subscriber;

/**
 * 王少岩 在 2017/3/10 创建了它
 */

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    private boolean mIsSkip = false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        RxHelper.countdown(3)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        doSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        doSkip();
                    }

                    @Override
                    public void onNext(Integer integer) {
                    }
                });
    }

    private void doSkip() {
        if (!mIsSkip) {
            mIsSkip = true;
            if (PreferencesUtils.getBoolean(Constants.sUser_loginFlag, false)) {
                NavigateUtils.startActivity(MainActivity.class);
            }else {
//                NavigateUtils.startActivity(LoginActivity.class);
            }
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        // 不响应后退键
        return;
    }
}
