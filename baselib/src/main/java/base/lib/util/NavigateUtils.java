package base.lib.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


/**
 * Created by 王少岩 on 2016/10/20.
 */

public class NavigateUtils {

    /**
     * 普通跳转
     *
     * @param activityClass
     */
    public static void startActivity(Class<? extends Activity> activityClass) {
        Intent intent = new Intent(ActivityManager.getActivity(), activityClass);
        ActivityManager.getActivity().startActivity(intent);
    }

    public static void startActivity(Class<? extends Activity> activityClass, Bundle bundle) {
        Intent intent = new Intent(ActivityManager.getActivity(), activityClass);
        intent.putExtras(bundle);
        ActivityManager.getActivity().startActivity(intent);
    }

    /**
     * 带返回值的跳转
     *
     * @param activityClass
     * @param requestCode
     */
    public static void startActivityForResult(Class<? extends Activity> activityClass, int requestCode) {
        Intent intent = new Intent(ActivityManager.getActivity(), activityClass);
        ActivityManager.getActivity().startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Class<? extends Activity> activityClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(ActivityManager.getActivity(), activityClass);
        intent.putExtras(bundle);
        ActivityManager.getActivity().startActivityForResult(intent, requestCode);
    }

    /**
     * 打开拨号页面
     *
     * @param tel
     */
    public static void startPhoneActivity(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ActivityManager.getActivity().startActivity(intent);
    }
}
