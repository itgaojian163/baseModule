package com.tengshi.basemodule.retrofitNet.conver;

import io.reactivex.Observer;

/**
 * 作者 : Adam on 2018/12/5.
 * 邮箱 : itgaojian@163.com
 * 描述 : 统一错误处理
 */
public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        ApiException apiException = new ExceptionHandler(e).parserException();
        error(apiException);
    }

    public abstract void error(ApiException e);


    @Override
    public void onComplete() {

    }
}
