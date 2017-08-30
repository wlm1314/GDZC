package com.gdzc.injector.module;

import android.content.Context;

import com.gdzc.api.HttpConsts;
import com.gdzc.api.RequestApi;
import com.gdzc.app.App;
import com.gdzc.rxbus.RxBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gdzc.api.RetrofitService.sLoggingInterceptor;
import static com.gdzc.api.RetrofitService.sParamInterceptor;

/**
 * 王少岩 在 2017/3/10 创建了它
 */
@Module
public class AppModule {
    private static App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Context provideAppContext(){
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    RxBus provideRxBus(){
        return new RxBus();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(sParamInterceptor)
                .addInterceptor(sLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HttpConsts.getServer())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    RequestApi provideRequestApi(Retrofit retrofit){
        return retrofit.create(RequestApi.class);
    }
}
