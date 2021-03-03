package com.tengshi.basemodule.base;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tengshi.basemodule.R;
import com.tengshi.basemodule.databinding.ActivityPhotoBinding;
import com.tengshi.basemodule.globalconfig.PathConfig;
import com.tengshi.basemodule.utils.ToastUtils;

import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;

/**
 * 相片浏览
 */
@Route(path = PathConfig.PATH_MODULE_CMUTILS_PHOTO_SHOW)
public class PhotoActivity extends BaseActivity<ActivityPhotoBinding> {
    public static final String TAG_IMGURL = "imgUrls";


    @Override
    public void initData() {
        mBaseBinding.tvAppTitleTxt.setText("图片浏览");
        mBaseBinding.ivAppBack.setVisibility(View.VISIBLE);
        refreshView(STATE_LOAD_SUCCESS);
        ArrayList<String> mImgUrls = getIntent().getStringArrayListExtra(TAG_IMGURL);
        if (mImgUrls == null) {
            ToastUtils.showShort(R.string.check_imgurl);
            finish();
        } else {
            mContentBinding.vpImages.setAdapter(new ImageAdapter(this, mImgUrls));
            mContentBinding.tvCount.setText(String.format(getResources().getString(R.string.img_position), 1, mImgUrls.size()));
            mContentBinding.vpImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    mContentBinding.tvCount.setText(String.format(getResources().getString(R.string.img_position), ++position, mImgUrls.size()));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    protected void reLoadData() {

    }

    @Override
    protected ActivityPhotoBinding setLayoutId() {
        return ActivityPhotoBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
