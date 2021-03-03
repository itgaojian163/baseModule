package com.tengshi.basemodule.base;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tengshi.basemodule.databinding.ActivityConditionBinding;
import com.tengshi.basemodule.globalconfig.PathConfig;


/**
 * 作者: Adam
 * 日期: 2019-4-22 18:30:41
 * 邮箱: itgaojian@163.com
 * 描述:
 */
@Route(path = PathConfig.PATH_MODULE_CMUTILS_CONDITION)
public class ConditionActivity extends BaseActivity<ActivityConditionBinding> {


    @Override
    public void initData() {
        super.initData();
        refreshView(STATE_LOAD_SUCCESS);
    }

    @Override
    protected void reLoadData() {

    }

    @Override
    protected ActivityConditionBinding setLayoutId() {
        return ActivityConditionBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
