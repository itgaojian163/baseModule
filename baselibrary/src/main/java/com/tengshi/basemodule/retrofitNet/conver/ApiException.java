package com.tengshi.basemodule.retrofitNet.conver;

/**
 * Author by Gao
 * Time : 2018/4/25
 * Description : 描述信息
 */

public class ApiException extends Exception {
    private String code;
    private String errorMsg;

    public ApiException() {

    }

    public ApiException(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
