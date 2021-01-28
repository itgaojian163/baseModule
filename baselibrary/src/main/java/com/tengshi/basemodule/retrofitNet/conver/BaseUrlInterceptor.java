package com.tengshi.basemodule.retrofitNet.conver;


import com.tengshi.basemodule.globalconfig.PathConfig;
import com.tengshi.basemodule.retrofitNet.BaseUrlApi;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 多个BaseUrl的拦截器
 */
public class BaseUrlInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        HttpUrl oldHttpUrl = request.url();
        List<String> baseUrlName = request.headers("base_url_name");
        if (null != baseUrlName && baseUrlName.size() > 0) {
            builder.removeHeader("base_url_name");
            String headerValue = baseUrlName.get(0);
            HttpUrl newBaseUrl = null;
            if (PathConfig.MODULE_USER_CENTER.equals(headerValue)) {
                newBaseUrl = HttpUrl.parse(BaseUrlApi.USERCENTER_IP);
            } else {
                newBaseUrl = oldHttpUrl;
            }
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme(newBaseUrl.scheme())
                    .host(newBaseUrl.host())
                    .port(newBaseUrl.port())
                    .setPathSegment(0, newBaseUrl.pathSegments().get(0))
                    .build();
            return chain.proceed(builder.url(newFullUrl).build());
        } else {
            return chain.proceed(request);
        }
    }
}
