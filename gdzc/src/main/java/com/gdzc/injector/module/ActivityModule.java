package com.gdzc.injector.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/19.
 * Activity Module
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity getActivity() {
        return mActivity;
    }

}
