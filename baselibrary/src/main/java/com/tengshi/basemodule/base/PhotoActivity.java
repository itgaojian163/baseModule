package com.tengshi.basemodule.base;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tengshi.basemodule.R;
import com.tengshi.basemodule.R2;
import com.tengshi.basemodule.globalconfig.PathConfig;
import com.tengshi.basemodule.utils.ToastUtils;

import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 相片浏览
 */
@Route(path = PathConfig.PATH_MODULE_CMUTILS_PHOTO_SHOW)
public class PhotoActivity extends BaseActivity {
    @BindView(R2.id.vp_images)
    ViewPager mVpImages;
    @BindView(R2.id.tv_count)
    TextView mTvCount;
    public static final String TAG_IMGURL = "imgUrls";
    private Unbinder mUnbinder;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_photo;
    }


    @Override
    public void initData() {
        mUnbinder = ButterKnife.bind(this);
        mTvBaseTitle.setText("图片浏览");
        mIbBack.setVisibility(View.VISIBLE);
        refreshView(STATE_LOAD_SUCCESS);
        ArrayList<String> mImgUrls = getIntent().getStringArrayListExtra(TAG_IMGURL);
        if (mImgUrls == null) {
            ToastUtils.showShort(R.string.check_imgurl);
            finish();
        } else {
            mVpImages.setAdapter(new ImageAdapter(this, mImgUrls));
            mTvCount.setText(String.format(getResources().getString(R.string.img_position), 1, mImgUrls.size()));
            mVpImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    mTvCount.setText(String.format(getResources().getString(R.string.img_position), ++position, mImgUrls.size()));
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
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
