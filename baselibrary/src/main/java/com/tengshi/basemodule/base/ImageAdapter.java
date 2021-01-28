package com.tengshi.basemodule.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tengshi.basemodule.R;
import com.tengshi.basemodule.photoview.widget.PhotoView;
import com.tengshi.basemodule.retrofitNet.BaseUrlApi;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * 作者 : Adam on 2018/10/17.
 * 邮箱 : itgaojian@163.com
 * 描述 : 图片显示
 */
public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private List mImgs;

    public ImageAdapter(Context ctx, List imgs) {
        this.mContext = ctx;
        this.mImgs = imgs;
    }

    @Override
    public int getCount() {
        return mImgs.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_pic, container, false);
        PhotoView photoView = view.findViewById(R.id.pv_pic);
        RequestOptions options = new RequestOptions().error(R.drawable.ic_img_default).placeholder(R.drawable.ic_loading);
        Glide.with(mContext)
                .load(BaseUrlApi.BASE_SYSTEM_IP + mImgs.get(position))
                .apply(options)
                .into(photoView);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
