package com.gdzc.api;


import com.gdzc.BuildConfig;
import com.gdzc.constant.Constants;

import base.lib.util.PreferencesUtils;


/**
 * Created by 王少岩 on 2016/9/14.
 */
public class HttpConsts {

    public static final String SERVER = "http://120.76.218.161/";//正式网地址
    public static final String SERVER_TEXT = "http://120.76.218.161/";//测试网地址

    /**
     * 设置服务地址
     *
     * @param server
     */
    public static void setServer(String server) {
        if (BuildConfig.DEBUG) {
            PreferencesUtils.saveString(Constants.sServerTest, server);
        } else {
            PreferencesUtils.saveString(Constants.sServer, server);
        }
    }

    public static String getServer() {
        if (BuildConfig.DEBUG) {
            return PreferencesUtils.getString(Constants.sServerTest, SERVER_TEXT);
        } else {
            return PreferencesUtils.getString(Constants.sServer, SERVER);
        }
    }
}
