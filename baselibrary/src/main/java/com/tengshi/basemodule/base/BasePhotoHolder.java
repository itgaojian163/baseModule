package com.tengshi.basemodule.base;

import android.view.View;
import android.widget.ImageView;

import com.tengshi.basemodule.R;

import androidx.recyclerview.widget.RecyclerView;


/**
 * 作者 : Adam on 2018/11/14.
 * 邮箱 : itgaojian@163.com
 * 描述 : <功能描述>
 */
public class BasePhotoHolder extends RecyclerView.ViewHolder {
    public ImageView mIvPhoto;

    public BasePhotoHolder(View itemView) {
        super(itemView);
        mIvPhoto = itemView.findViewById(R.id.iv_photo);
    }
}
