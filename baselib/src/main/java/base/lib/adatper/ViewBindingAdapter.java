package base.lib.adatper;

import android.databinding.BindingAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kelin.mvvmlight.command.ReplyCommand;


/**
 * 王少岩 在 2017/3/21 创建了它
 */
public final class ViewBindingAdapter {

    @BindingAdapter({"toolbar_navigationIcon"})
    public static void setToolBarNavigation(Toolbar toolbar, int resId) {
        if (resId > 0)
            toolbar.setNavigationIcon(resId);
        else
            toolbar.setNavigationIcon(null);
    }

    @BindingAdapter({"naviCommand"})
    public static void naviCommand(Toolbar toolbar, final ReplyCommand clickCommand) {
        toolbar.setNavigationOnClickListener(v -> {
            if (clickCommand != null) {
                clickCommand.execute();
            }
        });
    }

    @BindingAdapter(value = {"clickCommand", "param"})
    public static void clickCommand(View view, final ReplyCommand<Object> clickCommand, Object object) {
        view.setOnClickListener(v -> {
            if (clickCommand != null) {
                clickCommand.execute(object);
            }
        });
    }

    @BindingAdapter({"my_drawableLeft"})
    public static void setMyDrableLeft(TextView textView, int resId) {
         textView.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }
}

