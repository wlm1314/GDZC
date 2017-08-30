package com.gdzc.rxbus.event;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 王少岩 在 2017/3/22 创建了它
 */

public class CommonEvent {
    public static final int FLAG_COMPLETE = 1001;
    public static final int FLAG_NOCOMPLETE = 1002;

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({FLAG_COMPLETE, FLAG_NOCOMPLETE})
    public @interface EventType {
    }

    public int eventType;

    public CommonEvent(@EventType int eventType) {
        this.eventType = eventType;
    }

}
