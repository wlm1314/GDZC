package base.lib.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import base.lib.R;

/**
 * Created by wanghui on 2017/3/22.
 */

public class AppUtils {
    /**
     * 在屏幕底部显示dialog
     *
     * @param activity
     * @param view
     * @return
     */
    public static Dialog showBottomDialog(Activity activity, View view) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        ((WindowManager) activity.getSystemService(Service.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mDisplayMetrics);
        Dialog dialog = new Dialog(activity, R.style.DialogStyleBottom);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        // 设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = mDisplayMetrics.widthPixels;
        wl.gravity = Gravity.BOTTOM;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);// 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }
}
