package com.tengshi.basemodule.retrofitNet.conver;


import com.tengshi.basemodule.retrofitNet.listener.DownloadListener;
import com.tengshi.basemodule.retrofitNet.download.DownloadResponseBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 作者 : Adam on 2018/12/5.
 * 邮箱 : itgaojian@163.com
 * 描述 : 下载拦截器
 */
public class DownloadInterceptor implements Interceptor {

    private DownloadListener listener;

    public DownloadInterceptor(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        return originalResponse.newBuilder()
                .body(new DownloadResponseBody(originalResponse.body(), listener))
                .build();
    }
}
