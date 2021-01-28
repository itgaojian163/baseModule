package com.tengshi.basemodule.retrofitNet.api;

import com.tengshi.basemodule.retrofitNet.bean.BaseSuccessBean;
import com.tengshi.basemodule.retrofitNet.bean.BaseUserBean;
import com.tengshi.basemodule.retrofitNet.bean.DictBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * 作者: adam
 * 日期: 1/28/21 - 2:33 PM
 * 邮箱: itgaojian@163.com
 * 描述:
 */
public interface UserCenterApi {
    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Headers({"base_url_name:userCenter"})
    @POST("app/sign/login")
    Observable<BaseUserBean> doLogin(@Body RequestBody user);

    /**
     * 登录-手机号
     *
     * @param user
     * @return
     */
    @Headers({"base_url_name:userCenter"})
    @POST("app/sign/loginphone")
    Observable<BaseUserBean> doLoginWithPhone(@Body RequestBody user);

    /**
     * 忘记密码
     */
    @Headers({"base_url_name:userCenter"})
    @PUT("app/user/forgetpassword")
    Observable<BaseSuccessBean> doForgetPwd(@Body RequestBody body);


    /**
     * Image
     * 上传图片文件
     * 用户中心
     *
     * @param file
     * @param token
     */
    @Headers({"base_url_name:userCenter"})
    @Multipart
    @POST("app/file/uploadimage")
    Observable<BaseUserBean> doUploadImage(@Header("token") String token,
                                           @Part MultipartBody.Part file);

    /**
     * Image
     * 上传图片文件
     * 其他项目
     *
     * @param file
     * @param token
     */
    @Multipart
    @POST("app/file/uploadimage")
    Observable<BaseUserBean> doUploadImage(@Header("base_url_name") String basePro,
                                           @Header("token") String token,
                                           @Part MultipartBody.Part file);

    /**
     * 根据父节点ID获取字典
     */
    @GET("app/datadictionary/listdictionarybyparentid/{dictionaryParentId}")
    Observable<List<DictBean>> getDictionaryListBypId(@Header("token") String token,
                                                      @Header("base_url_name") String moduleType,
                                                      @Path("dictionaryParentId") String pId);
}
