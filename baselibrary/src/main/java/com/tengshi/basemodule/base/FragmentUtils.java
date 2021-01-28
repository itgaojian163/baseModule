package com.tengshi.basemodule.base;


import com.alibaba.android.arouter.launcher.ARouter;
import com.tengshi.basemodule.globalconfig.PathConfig;

import androidx.fragment.app.Fragment;

/**
 * 生成fragment的工具类
 */
public class FragmentUtils {

    public static Fragment getHomeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(PathConfig.PATH_MODULEMAIN_FRAGMENT_MAIN).navigation();
        return fragment;
    }

    public static Fragment getChatFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(PathConfig.PATH_MODULEMAIN_FRAGMENT_CHAT).navigation();
        return fragment;
    }

    public static Fragment getFuncFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(PathConfig.PATH_MODULEMAIN_FRAGMENT_FUNC).navigation();
        return fragment;
    }

    public static Fragment getUserFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(PathConfig.PATH_MODULEMAIN_FRAGMENT_USER).navigation();
        return fragment;
    }
}
