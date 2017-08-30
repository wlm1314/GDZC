package base.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import base.lib.R;


//自定义的view对象.
public class CellView extends FrameLayout {

    protected final SparseArray<View> childrenArray = new SparseArray<View>();

    private int labelViewId;
    private int infoViewId;
    private int subViewId;
    private int flagViewId;
    private int tipViewId;
    private int leftIconId;
    private int topIconId;
    private int rightIconId;
    private int bottomIconId;


    public CellView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        readStyleAttributes(context, attrs, defStyle);
    }

    public CellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        readStyleAttributes(context, attrs, 0);
    }

    public CellView(Context context) {
        super(context);
    }


    protected void readStyleAttributes(Context context, AttributeSet attrs, int defStyle) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.cell_view, 0, defStyle);

        initLayout(context, a);

        labelViewId = a.getResourceId(R.styleable.cell_view_labelId, R.id.label_view);
        infoViewId = a.getResourceId(R.styleable.cell_view_infoId, R.id.info_view);
        subViewId = a.getResourceId(R.styleable.cell_view_subId, R.id.sub_view);
        flagViewId = a.getResourceId(R.styleable.cell_view_flagId, R.id.flag_view);
        tipViewId = a.getResourceId(R.styleable.cell_view_tipId, R.id.tip_view);

        leftIconId = a.getResourceId(R.styleable.cell_view_iconLeftId, R.id.left_icon);
        topIconId = a.getResourceId(R.styleable.cell_view_iconTopId, R.id.top_icon);
        rightIconId = a.getResourceId(R.styleable.cell_view_iconRightId, R.id.right_icon);
        bottomIconId = a.getResourceId(R.styleable.cell_view_iconBottomId, R.id.bottom_icon);

        Drawable iconLeft = a.getDrawable(R.styleable.cell_view_iconLeft);
        Drawable iconTop = a.getDrawable(R.styleable.cell_view_iconTop);
        Drawable iconRight = a.getDrawable(R.styleable.cell_view_iconRight);
        Drawable iconBottom = a.getDrawable(R.styleable.cell_view_iconBottom);
        setLeftIcon(iconLeft);
        setTopIcon(iconTop);
        setRightIcon(iconRight);
        setBottomIcon(iconBottom);

        String labelText = a.getString(R.styleable.cell_view_labelText);
        String infoText = a.getString(R.styleable.cell_view_infoText);
        String subText = a.getString(R.styleable.cell_view_subText);
        String flagText = a.getString(R.styleable.cell_view_flagText);

        setLabelText(labelText);
        setInfoText(infoText);
        setSubText(subText);
        setFlagText(flagText);

        String labelHint = a.getString(R.styleable.cell_view_labelHint);
        String infoHint = a.getString(R.styleable.cell_view_infoHint);
        String subHint = a.getString(R.styleable.cell_view_subHint);

        if (labelHint != null) {
            setLabelHint(labelHint);
        }
        if (infoHint != null) {
            setInfoHint(infoHint);
        }
        if (subHint != null) {
            setSubHint(subHint);
        }

        int labelColor = a.getColor(R.styleable.cell_view_labelColor, -1);
        int infoColor = a.getColor(R.styleable.cell_view_infoColor, -1);
        int subColor = a.getColor(R.styleable.cell_view_subColor, -1);
        int flagColor = a.getColor(R.styleable.cell_view_flagColor, -1);

        setLabelTextColor(labelColor);
        setInfoTextColor(infoColor);
        setSubTextColor(subColor);
        setFlagTextColor(flagColor);

        a.recycle();
    }

    protected void initLayout(Context context, TypedArray a) {
        int layoutId = a.getResourceId(R.styleable.cell_view_contentLayout, -1);
        if (layoutId == -1) {
            return;
        }

        boolean haveToUseCover = a.getBoolean(R.styleable.cell_view_cover, true);
        if (!haveToUseCover) {
            View.inflate(context, layoutId, this);
            return;
        }

        View.inflate(context, R.layout.cell_view_layout, this);
        //如果使用默认包裹布局,则平布用户自定义的icon id
        leftIconId = R.id.left_icon;
        rightIconId = R.id.right_icon;
        // 防止用户传人的layout中id冲突
        getChild(leftIconId);
        getChild(R.id.right_icon);

        ViewGroup contentView = (ViewGroup) getChild(R.id.content_layout);
        View.inflate(context, layoutId, contentView);

    }

    public View getChild(int id) {
        View child = childrenArray.get(id);
        if (child != null) {
            return child;
        }
        child = findViewById(id);

        if (child != null) {
            childrenArray.put(id, child);
        }
        return child;
    }

    /*labelView*/
    public View getLabelView() {
        return getChild(labelViewId);
    }

    public void setLabelTextColor(int color) {
        View view = getLabelView();
        setTextColor(view, color);
    }

    public void setLabelText(String text) {
        View view = getLabelView();
        setText(view, text);
    }

    public String getLabelText() {
        View view = getLabelView();
        return getText(view);
    }

    public void setLabelHint(String hint) {
        View view = getLabelView();
        setHint(view, hint);
    }

    /*infoView*/
    public View getInfoView() {
        return getChild(infoViewId);
    }

    public void setInfoText(String text) {
        View view = getInfoView();
        setText(view, text);
    }

    public String getInfoText() {
        View view = getInfoView();
        return getText(view);
    }

    public void setInfoTextColor(int color) {
        View view = getInfoView();
        setTextColor(view, color);
    }

    public void setInfoHint(String hint) {
        View view = getInfoView();
        setHint(view, hint);
    }

    /*subView*/
    public View getSubView() {
        return getChild(subViewId);
    }

    public void setSubText(String text) {
        View view = getSubView();
        setText(view, text);
    }

    public String getSubText() {
        View view = getSubView();
        return getText(view);
    }

    public void setSubTextColor(int color) {
        View view = getSubView();
        setTextColor(view, color);
    }

    public void setSubHint(String hint) {
        View view = getSubView();
        setHint(view, hint);
    }
    /*flagView*/

    /**
     * TagView,如果没有要显示的内容则隐藏,如果有显示的内容则显示
     *
     * @return
     */
    public View getFlagView() {
        return getChild(flagViewId);
    }
    public View getTipView() {
        return getChild(tipViewId);
    }
    public void setTipFlagViewVisiable(Boolean visiable){
        getTipView().setVisibility(visiable?View.VISIBLE:View.GONE);
    }

    public void setFlagText(String text) {
        View flagView = getFlagView();

        if (flagView == null) {
            return;
        }

        if (TextUtils.isEmpty(text) || "0".equals(text)) {
            flagView.setVisibility(GONE);
        } else {
            flagView.setVisibility(VISIBLE);
        }

        setText(flagView, text);
    }

    public String getFlagText() {
        View flagView = getFlagView();
        return getText(flagView);
    }

    public void setFlagTextColor(int color) {
        View view = getFlagView();
        setTextColor(view, color);
    }

    public ImageView getLeftIcon() {
        return (ImageView) getChild(leftIconId);
    }

    public void setLeftIcon(Drawable drawable) {
        setIcon(getLeftIcon(), drawable);
    }

    public void showLeftIcon(boolean isShow) {
        showIcon(getLeftIcon(), isShow);
    }

    public ImageView getTopIcon() {
        return (ImageView) getChild(topIconId);
    }

    public void setTopIcon(Drawable drawable) {
        setIcon(getTopIcon(), drawable);
    }

    public void showTopIcon(boolean isShow) {
        showIcon(getTopIcon(), isShow);
    }


    public ImageView getRightIcon() {
        return (ImageView) getChild(rightIconId);
    }

    public void setRightIcon(Drawable drawable) {
        setIcon(getRightIcon(), drawable);
    }

    public void showRightIcon(boolean isShow) {
        showIcon(getRightIcon(), isShow);
    }

    public ImageView getBottomIcon() {
        return (ImageView) getChild(bottomIconId);
    }

    public void setBottomIcon(Drawable drawable) {
        setIcon(getBottomIcon(), drawable);
    }

    public void showBottomIcon(boolean isShow) {
        showIcon(getBottomIcon(), isShow);
    }

    // TODO 这两个方法以后可以加上反射,如果有setText(String)方法就可以
    protected void setText(View view, String text) {
        if (view == null) {
            return;
        }
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
    }

    protected String getText(View view) {
        if (view != null && view instanceof TextView) {
            return ((TextView) view).getText().toString();
        }
        return "";
    }

    protected void setHint(View view, String text) {
        if (view == null) {
            return;
        }
        if (view instanceof TextView) {
            ((TextView) view).setHint(text);
        }
    }

    protected void setTextColor(View view, int color) {
        if (view == null || color == -1) {
            return;
        }
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(color);
        }
    }

    public void setIcon(ImageView icon, Drawable drawable) {
        if (icon == null) {
            return;
        }
        icon.setImageDrawable(drawable);
        if (drawable != null) {
            showIcon(icon, true);
        }
    }

    protected void showIcon(ImageView icon, boolean isShow) {
        icon.setVisibility(isShow ? VISIBLE : GONE);
    }

}