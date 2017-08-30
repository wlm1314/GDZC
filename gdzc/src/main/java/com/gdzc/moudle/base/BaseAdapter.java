package com.gdzc.moudle.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdzc.R;

import java.util.List;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class BaseAdapter<T> extends BaseQuickAdapter<T, BaseAdapter.ViewHolder> {

    private int variableId;

    public BaseAdapter(int variableId, int layoutResId, List<T> data) {
        super(layoutResId, data);
        this.variableId = variableId;
    }

    @Override
    protected void convert(ViewHolder helper, T itemViewModel) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(variableId, itemViewModel);
        binding.executePendingBindings();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    public static class ViewHolder extends BaseViewHolder {

        public ViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}
