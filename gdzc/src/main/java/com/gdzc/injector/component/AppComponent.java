package com.gdzc.injector.component;

import android.content.Context;

import com.gdzc.api.RequestApi;
import com.gdzc.injector.module.AppModule;
import com.gdzc.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * 王少岩 在 2017/3/10 创建了它
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    // provide
    Context getContext();
    RxBus getRxBus();
    Retrofit getRetrofit();
    RequestApi getRequestApi();
}
