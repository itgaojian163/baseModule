package com.tengshi.basemodule.retrofitNet.download;


import com.tengshi.basemodule.retrofitNet.api.DownloadService;
import com.tengshi.basemodule.retrofitNet.conver.DownloadInterceptor;
import com.tengshi.basemodule.retrofitNet.listener.DownloadListener;
import com.tengshi.basemodule.utils.FileIOUtils;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 作者: Adam
 * 日期: 2019/4/16 - 09:00
 * 邮箱: itgaojian@163.com
 * 描述: 下载工具类
 */
public class DownloadUtils {
    private static final int DEFAULT_TIMEOUT = 15;
    private final ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private final MainThreadExecutor uiExecutor = new MainThreadExecutor();
    private OkHttpClient.Builder mBuilder;

    private DownloadUtils() {
    }

    public static DownloadUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initConfig(OkHttpClient.Builder builder) {
        this.mBuilder = builder;
    }

    /**
     * 下载文件
     *
     * @param listener
     */
    public void downloadFile(DownloadParams inputParam, final DownloadListener listener) {

        DownloadInterceptor interceptor = new DownloadInterceptor(listener);
        if (mBuilder != null) {
            mBuilder.addInterceptor(interceptor);
        } else {
            mBuilder = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        }
        final DownloadService api = new Retrofit.Builder()
                .baseUrl(inputParam.getBaseUrl())
                .client(mBuilder.build())
                .build()
                .create(DownloadService.class);
        mExecutorService.execute(() -> {
            try {
                Response<ResponseBody> result = api.downloadWithDynamicUrl(inputParam.getRelativeUrl()).execute();
                File file = FileIOUtils.writeFile(inputParam.getLoadedFilePath(), result.body().byteStream());
                if (listener != null) {
                    if (inputParam.isCallbackOnUiThread()) {
                        uiExecutor.execute(() -> listener.onFinish(file));
                    } else {
                        listener.onFinish(file);
                    }
                }
            } catch (Exception e) {
                if (listener != null) {
                    if (inputParam.isCallbackOnUiThread()) {
                        uiExecutor.execute(() -> listener.onFailed(e.getMessage()));
                    } else {
                        listener.onFailed(e.getMessage());
                    }
                }
            }
        });
    }

    private static class SingletonHolder {
        private static final DownloadUtils INSTANCE = new DownloadUtils();
    }

}
