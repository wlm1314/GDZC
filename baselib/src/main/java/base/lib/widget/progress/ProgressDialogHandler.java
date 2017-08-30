package base.lib.widget.progress;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;

import com.github.ybq.android.spinkit.style.Circle;

import base.lib.R;
import base.lib.util.ActivityManager;

/**
 * Created by 王少岩 on 2017/1/19.
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog pd;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(ProgressCancelListener mProgressCancelListener, boolean cancelable) {
        super();
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {
        if (pd == null) {
            Circle mCircleDrawable = new Circle();
            mCircleDrawable.setBounds(0, 0, 100, 100);
            mCircleDrawable.setColor(Color.WHITE);

            pd = new ProgressDialog(ActivityManager.getActivity(), R.style.custom_dialog);
            pd.setIndeterminate(true);
            pd.setIndeterminateDrawable(mCircleDrawable);
            pd.setCanceledOnTouchOutside(false);
            pd.setMessage("请稍等...");
            pd.show();

            pd.setCancelable(cancelable);

            if (cancelable) {
                pd.setOnCancelListener(dialogInterface -> mProgressCancelListener.onCancelProgress());
            }

            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
