package base.lib.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 王少岩 在 2017/3/13 创建了它
 */

public class ActivityManager {
    private static Activity sActivity;
    private static List<Activity> mActivities = new ArrayList<>();

    public static void setCurrentActivity(Activity activity) {
        sActivity = activity;
    }

    public static Activity getActivity() {
        return sActivity;
    }

    public static void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public static void finish() {
        for (Activity activity : mActivities)
            activity.finish();
    }
}
