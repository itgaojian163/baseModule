package com.tengshi.basemodule.retrofitNet.conver;



import com.tengshi.basemodule.retrofitNet.bean.BaseResponseBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author by Gao
 * Time : 2018/4/25
 * Description : 描述信息
 */

public class RxTransformer<D, L> {

    /**
     * 获取list转换器
     *
     * @param <D> Data的数据类型
     * @param <L> List的数据类型
     * @return
     */
    public static <D, L> ObservableTransformer<BaseResponseBean<D, L>, List<L>> getListTransformer() {
        return upstream -> upstream.flatMap((Function<BaseResponseBean<D, L>, ObservableSource<List<L>>>) dlBaseResponseBean -> {
            if ("200".equals(dlBaseResponseBean.getState())) {
                return new Observable<List<L>>() {
                    @Override
                    protected void subscribeActual(Observer<? super List<L>> observer) {
                        try {
                            observer.onNext(dlBaseResponseBean.getList());
                            observer.onComplete();
                        } catch (Exception e) {
                            observer.onError(e);
                        }
                    }
                };
            } else {
                return Observable.error(new ApiException(dlBaseResponseBean.getState(), dlBaseResponseBean.getMsg()));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取Data转换器
     *
     * @param <D> Data的数据类型
     * @param <L> List的数据类型
     * @return
     */
    public static <D, L> ObservableTransformer<BaseResponseBean<D, L>, D> getDataTransformer() {
        return upstream -> upstream.flatMap((Function<BaseResponseBean<D, L>, ObservableSource<D>>) dlBaseResponseBean -> {
            if ("200".equals(dlBaseResponseBean.getState())) {
                return new Observable<D>() {
                    @Override
                    protected void subscribeActual(Observer<? super D> observer) {
                        try {
                            observer.onNext(dlBaseResponseBean.getData());
                            observer.onComplete();
                        } catch (Exception e) {
                            observer.onError(e);
                        }
                    }
                };
            } else {
                return Observable.error(new ApiException(dlBaseResponseBean.getState(), dlBaseResponseBean.getMsg()));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取返回的全部数据
     *
     * @param <D> Data的数据类型
     * @param <L> List的数据类型
     * @return
     */
    public static <D, L> ObservableTransformer<BaseResponseBean<D, L>, BaseResponseBean<D, L>> getBaseBeanTransformer() {
        return upstream -> upstream.flatMap((Function<BaseResponseBean<D, L>, ObservableSource<BaseResponseBean<D, L>>>) dlBaseResponseBean -> {
            if ("200".equals(dlBaseResponseBean.getState())) {
                return new Observable<BaseResponseBean<D, L>>() {
                    @Override
                    protected void subscribeActual(Observer<? super BaseResponseBean<D, L>> observer) {
                        try {
                            observer.onNext(dlBaseResponseBean);
                            observer.onComplete();
                        } catch (Exception e) {
                            observer.onError(e);
                        }
                    }
                };
            } else {
                return Observable.error(new ApiException(dlBaseResponseBean.getState(), dlBaseResponseBean.getMsg()));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
