package com.tengshi.basemodule.retrofitNet;

import com.google.gson.Gson;
import com.tengshi.basemodule.retrofitNet.bean.BaseResponseBean;
import com.tengshi.basemodule.utils.LogUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 作者 : Adam on 2018/12/5.
 * 邮箱 : itgaojian@163.com
 * 描述 : <功能描述>
 */
public class RequestUtils {
    private static final Gson gson = new Gson();


    /**
     * 1. 转换
     * 统一处理一些动作
     */
    public static <D, L> void convert(Observable<BaseResponseBean<D, L>> observable, Observer<List<L>> observer) {
        observable.map(tdBaseResponseBean -> {
            if ("200".equals(tdBaseResponseBean.getState())) {
                return tdBaseResponseBean.getList();
            } else if ("202".equals(tdBaseResponseBean.getState())) {
                return null;
            } else {
                return null;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 2. 执行的方法
     */
    public static <D, T> void execute(Observable<BaseResponseBean<D, T>> observable, Observer<BaseResponseBean<D, T>> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 3.请求数据是Json，Json转成RequestBody
     */
    public static RequestBody createRequestBody(Object obj) {
        BaseResponseBean bean = new BaseResponseBean();
        String json = gson.toJson(bean);
        // 打印请求的Json
        LogUtils.json(json);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return body;
    }

}
