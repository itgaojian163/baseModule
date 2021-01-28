package com.tengshi.basemodule.retrofitNet.bean;

/**
 * 作者 : Adam on 2018/12/5.
 * 邮箱 : itgaojian@163.com
 * 描述 : 错误码
 */
public interface ApiErrorCode {
    String HTTP_ERROR = "401";//网络错误
    String SOCKET_ERROR = "402";//网络错误
    String SOCKET_TIME_OUT = "406";//连接超时
    String JSON_ERROR = "1002";//json错误
    String JSON_PARSER_ERROR = "1003";//json解析错误
    String UNKNOWN_ERROR = "1000";//未知
    String TOKEN_LOSE = "202";//Token失效
}
