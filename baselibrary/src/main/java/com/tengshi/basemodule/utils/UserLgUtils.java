package com.tengshi.basemodule.utils;

import com.tengshi.basemodule.globalconfig.PathConfig;

public class UserLgUtils {
    /**
     * 保存token
     *
     * @param token
     */
    public static void setToken(String token) {
        SPUtils.getInstance(PathConfig.SP_USER).put("token", token);
    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken() {
        return SPUtils.getInstance(PathConfig.SP_USER).getString("token");
    }

}
