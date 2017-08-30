package com.gdzc.moudle.base;

import android.databinding.ObservableField;

import com.gdzc.R;
import com.kelin.mvvmlight.command.ReplyCommand;

import base.lib.util.ActivityManager;
import rx.functions.Action0;

/**
 * Created by 王少岩 on 2016/12/20.
 */

public class AppBar {
    public final ObservableField<Integer> navigation = new ObservableField<>(R.mipmap.ic_arrow_left);
    public final ObservableField<String> title = new ObservableField<>("");
    public final ObservableField<String> rightText = new ObservableField<>("");

    public ReplyCommand leftCommon = new ReplyCommand(() -> ActivityManager.getActivity().finish());
    public ReplyCommand rightCommon;

    public AppBar(String title) {
        this.title.set(title);
    }

    public AppBar(String title, boolean showLeft) {
        this.title.set(title);
        navigation.set(showLeft ? R.mipmap.ic_arrow_left : 0);
    }

    public AppBar(String title, int resId) {
        this.title.set(title);
        navigation.set(resId);
    }

    public AppBar(String title, int resId, Action0 command) {
        this.title.set(title);
        navigation.set(resId);
        this.leftCommon = new ReplyCommand(command);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setRight(String rightText, Action0 command) {
        this.rightText.set(rightText);
        this.rightCommon = new ReplyCommand(command);
    }

    public void showLeft() {
        navigation.set(R.mipmap.ic_arrow_left);
    }

    public void setleftCommon(Action0 common){
        this.leftCommon = new ReplyCommand(common);
    }
}
