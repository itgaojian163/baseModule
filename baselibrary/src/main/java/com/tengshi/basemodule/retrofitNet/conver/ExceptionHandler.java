package com.tengshi.basemodule.retrofitNet.conver;

import com.google.gson.JsonParseException;
import com.tengshi.basemodule.retrofitNet.bean.ApiErrorCode;
import com.tengshi.basemodule.utils.LogUtils;
import com.tengshi.basemodule.utils.ToastUtils;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Author by Gao
 * Time : 2018/4/26
 * Description : 错误解析
 */

public class ExceptionHandler {
    private Throwable mThrowable;

    public ExceptionHandler(Throwable e) {
        this.mThrowable = e;
        LogUtils.e(e.getMessage());
    }

    public ApiException parserException() {
        ApiException exception = new ApiException();
        if (mThrowable instanceof HttpException) {
            exception.setCode(ApiErrorCode.HTTP_ERROR);
            exception.setErrorMsg("网络错误,错误码:" + ApiErrorCode.HTTP_ERROR);
        } else if (mThrowable instanceof SocketException) {
            exception.setCode(ApiErrorCode.SOCKET_ERROR);
            exception.setErrorMsg("网络错误,错误码:" + ApiErrorCode.SOCKET_ERROR);
        } else if (mThrowable instanceof SocketTimeoutException) {
            exception.setCode(ApiErrorCode.SOCKET_TIME_OUT);
            exception.setErrorMsg("网络连接超时,错误码:" + ApiErrorCode.SOCKET_TIME_OUT);
        } else if (mThrowable instanceof JSONException) {
            exception.setCode(ApiErrorCode.JSON_ERROR);
            exception.setErrorMsg("网络错误,错误码:" + ApiErrorCode.JSON_ERROR);
        } else if (mThrowable instanceof JsonParseException) {
            exception.setCode(ApiErrorCode.JSON_PARSER_ERROR);
            exception.setErrorMsg("网络错误,错误码:" + ApiErrorCode.JSON_PARSER_ERROR);
        } else if (mThrowable instanceof ApiException) {
            if ("202".equals(((ApiException) mThrowable).getCode())) {
                exception.setCode(ApiErrorCode.TOKEN_LOSE);
                exception.setErrorMsg("Token失效,错误码:" + ApiErrorCode.TOKEN_LOSE);
            } else {
                exception.setCode(((ApiException) mThrowable).getCode());
                exception.setErrorMsg(((ApiException) mThrowable).getErrorMsg() + ",错误码:" + ((ApiException) mThrowable).getCode());
            }
        } else {
            LogUtils.e(mThrowable.toString());
            exception.setCode(ApiErrorCode.UNKNOWN_ERROR);
            exception.setErrorMsg(mThrowable.getMessage() + ",错误码:" + ApiErrorCode.UNKNOWN_ERROR);
        }
        ShowException(exception.getErrorMsg());
        return exception;
    }

    public void ShowException(String displayMsg) {
        ToastUtils.showShort(displayMsg);
    }
}
