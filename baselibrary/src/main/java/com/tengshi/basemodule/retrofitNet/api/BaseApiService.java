package com.tengshi.basemodule.retrofitNet.api;


import com.tengshi.basemodule.retrofitNet.bean.BaseResponseBean;
import com.tengshi.basemodule.retrofitNet.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者 : Adam on 2018/12/7.
 * 邮箱 : itgaojian@163.com
 * 描述 : 校验App版本
 */
public interface BaseApiService {

    @FormUrlEncoded
    @POST("appCmVersionManagement/getAppVersion")
    Observable<BaseResponseBean<VersionBean, Object>> checkUpdate(@Field("appKey") String key);
}
