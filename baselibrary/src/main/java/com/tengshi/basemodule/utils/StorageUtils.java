package com.tengshi.basemodule.utils;

import android.content.Context;

import com.tengshi.basemodule.globalconfig.PathConfig;

import java.io.File;
import java.nio.file.Path;

/**
 * 作者: adam
 * 日期: 1/25/21 - 3:38 PM
 * 邮箱: itgaojian@163.com
 * 描述: 项目缓存文件目录
 */
public class StorageUtils {

    public static String getProjectExternalCacheDir(Context ctx) {
        File cacheFile = ctx.getExternalCacheDir();
        return cacheFile.getAbsolutePath() + File.separator + PathConfig.PROJECT_NAME + File.separator;
    }

    public static String getProjectExternalFileDir(Context ctx) {
        File cacheFile = ctx.getExternalFilesDir(PathConfig.CATCH_PATH);
        return cacheFile.getAbsolutePath() + File.separator + PathConfig.PROJECT_NAME + File.separator;
    }

    public static String getProjectCacheDir(Context ctx) {
        File cacheFile = ctx.getCacheDir();
        return cacheFile.getAbsolutePath() + File.separator + PathConfig.PROJECT_NAME + File.separator;
    }

    public static String getProjectFileDir(Context ctx) {
        File cacheFile = ctx.getFilesDir();
        return cacheFile.getAbsolutePath() + File.separator + PathConfig.PROJECT_NAME + File.separator;
    }

}
