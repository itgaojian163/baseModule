package com.tengshi.basemodule.utils;

import android.content.Context;
import android.content.Intent;


/**
 * 作者 : Adam on 2018/10/26.
 * 邮箱 : itgaojian@163.com
 * 描述 : <定位工具类>
 */
public class LocationUtils {
    /**
     * 开启定位服务
     *
     * @param ctx
     */
    public static void startLocationService(Context ctx) {
        Intent intent = new Intent();
        try {
            Class<?> aClass = Class.forName("com.sucstepsoft.modulelocation.service.LocationService");
            intent.setClass(ctx, aClass);
            ctx.startService(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
