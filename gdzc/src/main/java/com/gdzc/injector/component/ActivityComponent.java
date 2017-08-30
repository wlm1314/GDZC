package com.gdzc.injector.component;

import android.app.Activity;

import com.gdzc.injector.module.ActivityModule;

import dagger.Component;

/**
 * 王少岩 在 2017/3/15 创建了它
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    // provide
    Activity getActivity();
}
