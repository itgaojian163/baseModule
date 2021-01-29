package com.tengshi.basemodule.retrofitNet;

import com.tengshi.basemodule.retrofitNet.conver.BaseUrlInterceptor;
import com.tengshi.basemodule.retrofitNet.conver.CommInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者 : Adam on 2018/12/5.
 * 邮箱 : itgaojian@163.com
 * 描述 : Retrofit工具类
 */
public class RetrofitManager {
    //项目Base路径
    /**
     * 超时时间
     */
    public static final int TIMEOUT = 60;
    private static volatile RetrofitManager mInstance;
    private Retrofit mRetrofit;

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }

    private RetrofitManager() {
        initRetrofit();
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置超时
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        //封装公共参数
        builder.addInterceptor(new CommInterceptor());
        //多BaseUrl连接器
        builder.addInterceptor(new BaseUrlInterceptor());
        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                // 设置请求的域名
                .baseUrl(BaseUrlApi.BASE_SYSTEM_IP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    /**
     * 创建API
     */
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}
