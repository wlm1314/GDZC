package com.gdzc.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.gdzc.R;
import com.gdzc.databinding.LayoutEmptyLoadingBinding;
import com.github.ybq.android.spinkit.sprite.Sprite;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 王少岩 在 2017/3/10 创建了它
 * 加载、空视图
 */

public class EmptyLayout extends FrameLayout {
    public static final int STATUS_HIDE = 1001;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_NO_NET = 2;
    public static final int STATUS_NO_DATA = 3;
    private Context mContext;
    private OnRetryListener mOnRetryListener;
    private int mEmptyStatus = STATUS_LOADING;
    private int mBgColor;

    LayoutEmptyLoadingBinding mBinding;

    public EmptyLayout(Context context) {
        this(context, null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    /**
     * 初始化
     */
    private void init(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.EmptyLayout);
        try {
            mBgColor = a.getColor(R.styleable.EmptyLayout_background_color, Color.WHITE);
        } finally {
            a.recycle();
        }
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.layout_empty_loading, this, true);
        mBinding.emptyLayout.setBackgroundColor(mBgColor);
        _switchEmptyView();
    }

    /**
     * 隐藏视图
     */
    public void hide() {
        mEmptyStatus = STATUS_HIDE;
        _switchEmptyView();
    }

    /**
     * 设置状态
     *
     * @param emptyStatus
     */
    public void setEmptyStatus(@EmptyStatus int emptyStatus) {
        mEmptyStatus = emptyStatus;
        _switchEmptyView();
    }

    /**
     * 获取状态
     *
     * @return 状态
     */
    public int getEmptyStatus() {
        return mEmptyStatus;
    }

    /**
     * 设置异常消息
     *
     * @param msg 显示消息
     */
    public void setEmptyMessage(String msg) {
        mBinding.tvNetError.setText(msg);
    }

    public void hideErrorIcon() {
        mBinding.tvNetError.setCompoundDrawables(null, null, null, null);
    }

//    /**
//     * 设置图标
//     * @param resId 资源ID
//     */
//    public void setEmptyIcon(int resId) {
//        mIvEmptyIcon.setImageResource(resId);
//    }
//
//    /**
//     * 设置图标
//     * @param drawable drawable
//     */
//    public void setEmptyIcon(Drawable drawable) {
//        mIvEmptyIcon.setImageDrawable(drawable);
//    }

    public void setLoadingIcon(Sprite d) {
        mBinding.emptyLoading.setIndeterminateDrawable(d);
    }

    /**
     * 切换视图
     */
    private void _switchEmptyView() {
        switch (mEmptyStatus) {
            case STATUS_LOADING:
                setVisibility(VISIBLE);
                mBinding.rlEmptyContainer.setVisibility(GONE);
                mBinding.emptyLoading.setVisibility(VISIBLE);
                break;
            case STATUS_NO_DATA:
            case STATUS_NO_NET:
                setVisibility(VISIBLE);
                mBinding.emptyLoading.setVisibility(GONE);
                mBinding.rlEmptyContainer.setVisibility(VISIBLE);
                break;
            case STATUS_HIDE:
                setVisibility(GONE);
                break;
        }
    }

    /**
     * 设置重试监听器
     *
     * @param retryListener 监听器
     */
    public void setRetryListener(OnRetryListener retryListener) {
        this.mOnRetryListener = retryListener;
    }

    /**
     * 点击重试监听器
     */
    public interface OnRetryListener {
        void onRetry();
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_LOADING, STATUS_NO_NET, STATUS_NO_DATA})
    public @interface EmptyStatus {
    }
}
