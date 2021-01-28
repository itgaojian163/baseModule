package com.tengshi.basemodule.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tengshi.basemodule.R;
import com.tengshi.basemodule.retrofitNet.BaseUrlApi;

import java.util.List;

/**
 * 作者 : Adam on 2018/11/14.
 * 邮箱 : itgaojian@163.com
 * 描述 : <功能描述>
 */
public class BaseShowPhotoAdapter extends BaseRecyclerAdapter<String, BasePhotoHolder> {
    public BaseShowPhotoAdapter(Context ctx, List<String> list) {
        super(ctx, list);
    }

    @Override
    public BasePhotoHolder createHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_show_photo, parent, false);
        return new BasePhotoHolder(itemView);
    }

    @Override
    public void bindHolder(BasePhotoHolder showPhotoHolder, int i) {
        RequestOptions transform = new RequestOptions()
                .error(R.drawable.ic_img_default)
                .placeholder(R.drawable.ic_img_default)
                .transform(new GlideRoundTransform(mContext, 5));
        Glide.with(mContext)
                .load(BaseUrlApi.BASE_SYSTEM_IP + mData.get(i))
                .apply(transform)
                .into(showPhotoHolder.mIvPhoto);
        if (listener != null) {
            showPhotoHolder.itemView.setOnClickListener(v -> listener.clickListener(getData(), i));
        }
    }

    public interface OnItemClick {
        void clickListener(List<String> data, int pos);
    }

    private OnItemClick listener;

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}

