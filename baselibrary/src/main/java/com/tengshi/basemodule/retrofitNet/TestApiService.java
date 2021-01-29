package com.tengshi.basemodule.retrofitNet;


import com.tengshi.basemodule.retrofitNet.bean.BaseResponseBean;
import com.tengshi.basemodule.retrofitNet.bean.TestDataBean;
import com.tengshi.basemodule.retrofitNet.bean.TestPhotoBean;
import com.tengshi.basemodule.retrofitNet.bean.UserBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 作者 : Adam on 2018/12/5.
 * 邮箱 : itgaojian@163.com
 * 描述 : <功能描述>
 */
public interface TestApiService {

    @POST("appTest/login")
    Observable<BaseResponseBean<TestDataBean, TestPhotoBean>> login(@Body UserBean bean);

    @FormUrlEncoded
    @POST("appTest/getData")
    Observable<BaseResponseBean<TestDataBean, TestPhotoBean>> getData(@Field("userName") String first);

    @GET("appTest/getList")
    Observable<BaseResponseBean<TestDataBean, TestPhotoBean>> getList(@Query("userName") String userName, @Query("passWord") String passWord);

    @GET("appTest/getGetData")
    Observable<BaseResponseBean<TestDataBean, TestPhotoBean>> getGetData();

    //    public static final String UPLOAD_IMG = BASE_URL + "appfile/asyncAploadFileImg";
    @Multipart
    @POST("appfile/asyncAploadFileImg")
    Observable<BaseResponseBean<TestDataBean, TestPhotoBean>> uploadFile(@Part("file") MultipartBody.Part file);
}