package com.tengshi.basemodule.base;

public interface BaseMVPView {
    /**
     * 加载成功
     */
    void loadingSuccess();

    /**
     * 加载中
     */
    void loading();

    /**
     * 空数据
     */
    void loadingEmpty();

    void initData();

    /**
     * 加载失败
     */
    void loadingFailed();
}
