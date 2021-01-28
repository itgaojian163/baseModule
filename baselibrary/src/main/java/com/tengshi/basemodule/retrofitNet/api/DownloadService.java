package com.tengshi.basemodule.retrofitNet.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 作者 : Adam on 2018/12/5.
 * 邮箱 : itgaojian@163.com
 * 描述 : 下载Api
 */
public interface DownloadService {
    @Streaming
    @GET
    Call<ResponseBody> downloadWithDynamicUrl(@Url String fileUrl);

}
