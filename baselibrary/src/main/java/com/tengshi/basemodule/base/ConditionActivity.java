package com.tengshi.basemodule.base;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tengshi.basemodule.R;
import com.tengshi.basemodule.globalconfig.PathConfig;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者: Adam
 * 日期: 2019-4-22 18:30:41
 * 邮箱: itgaojian@163.com
 * 描述:
 */
@Route(path = PathConfig.PATH_MODULE_CMUTILS_CONDITION)
public class ConditionActivity extends BaseActivity {

    private Unbinder mBind;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_condition;
    }

    @Override
    public void initData() {
        super.initData();
        mBind = ButterKnife.bind(this);
        refreshView(STATE_LOAD_SUCCESS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
