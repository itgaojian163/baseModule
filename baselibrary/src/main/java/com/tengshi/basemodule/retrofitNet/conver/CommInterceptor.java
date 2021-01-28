package com.tengshi.basemodule.retrofitNet.conver;


import com.tengshi.basemodule.utils.LogUtils;
import com.tengshi.basemodule.utils.UserInfoSPUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 * 作者 : Adam on 2018/12/6.
 * 邮箱 : itgaojian@163.com
 * 描述 : 公共参数拦截器-添加公共参数
 */
public class CommInterceptor implements Interceptor {

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder newRequestBuild = null;
        String method = oldRequest.method();
        String postBodyString = "";
        if ("POST".equals(method)) {
            RequestBody oldBody = oldRequest.body();
            if (oldBody instanceof FormBody) {
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                formBodyBuilder.add("token", UserInfoSPUtils.getToken());
                newRequestBuild = oldRequest.newBuilder();
                RequestBody formBody = formBodyBuilder.build();
                postBodyString = bodyToString(oldRequest.body());
                postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
                newRequestBuild.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
            } else if (oldBody instanceof MultipartBody) {
                MultipartBody oldBodyMultipart = (MultipartBody) oldBody;
                List<MultipartBody.Part> oldPartList = oldBodyMultipart.parts();
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                RequestBody requestBody1 = RequestBody.create(MediaType.parse("text/plain"), "token");
                MultipartBody.Part partBody = MultipartBody.Part.createFormData("token", UserInfoSPUtils.getToken(), requestBody1);
                for (MultipartBody.Part part : oldPartList) {
                    builder.addPart(part);
                    postBodyString += (bodyToString(part.body()) + "\n");
                }
                postBodyString += (bodyToString(requestBody1) + "\n");
                builder.addPart(partBody);
                newRequestBuild = oldRequest.newBuilder();
                newRequestBuild.post(builder.build());
            } else {
                newRequestBuild = oldRequest.newBuilder();
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                FormBody token = formBodyBuilder.add("token", UserInfoSPUtils.getToken()).build();
                newRequestBuild.post(token);
            }
        } else {
            // 添加新的参数
            LogUtils.e(UserInfoSPUtils.getToken());
            HttpUrl.Builder commonParamsUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
                    .addQueryParameter("token", UserInfoSPUtils.getToken());
            newRequestBuild = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
                    .url(commonParamsUrlBuilder.build());
        }
        Request newRequest = newRequestBuild
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "zh")
                .build();

        okhttp3.Response response = chain.proceed(newRequest);
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    private HashMap<String, String> buildPublicParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", UserInfoSPUtils.getToken());
//        try {
//            params.put("strName", BluetoothAdapter.getDefaultAdapter().getName() == null ? "" : BluetoothAdapter.getDefaultAdapter().getName());//手机系统版名称
//            params.put("strSysName", "Android");//手机系统版名称
//            params.put("strSysVersion", android.os.Build.VERSION.RELEASE == null ? "" : android.os.Build.VERSION.RELEASE);//手机系统版本号
//            params.put("phoneModel", android.os.Build.MODEL == null ? "" : android.os.Build.MODEL);//手机型号
//            params.put("strAppName", AppUtils.getAppName());//应用名称
//            params.put("strAppVersion", AppUtils.getAppVersionName());//应用版本名称
//            params.put("strAppBuild", String.valueOf(AppUtils.getAppVersionCode()));//应用版本号
//        } catch (Exception e) {
//            LogUtils.e("Public parameter acquisition failed");
//        }
        return params;
    }

}
