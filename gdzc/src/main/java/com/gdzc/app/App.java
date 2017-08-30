package com.gdzc.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.gdzc.injector.component.AppComponent;
import com.gdzc.injector.component.DaggerAppComponent;
import com.gdzc.injector.module.AppModule;

import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;


/**
 * 王少岩 在 2017/3/14 创建了它
 */

public class App extends Application {

    private static App mInstance;
    private static AppComponent sAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mInstance = this;
        initInjector();
        initConfig();
    }

    public static App getInstance(){
        return mInstance;
    }

    /**
     * 初始化注射器
     */
    public void initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据
        sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    /**
     * 初始化配置
     */
    private void initConfig(){
        ToastUtils.init(getApplicationContext());
        PreferencesUtils.init(getApplicationContext());
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
