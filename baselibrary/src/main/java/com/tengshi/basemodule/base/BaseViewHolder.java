package com.tengshi.basemodule.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * 作者: adam
 * 日期: 3/3/21 - 5:01 PM
 * 邮箱: itgaojian@163.com
 * 描述:
 */
public class BaseViewHolder<VB extends ViewBinding> extends RecyclerView.ViewHolder {
    public VB mHolderBinding;

    public BaseViewHolder(@NonNull VB holderBinding) {
        super(holderBinding.getRoot());
        this.mHolderBinding = holderBinding;
    }
}
