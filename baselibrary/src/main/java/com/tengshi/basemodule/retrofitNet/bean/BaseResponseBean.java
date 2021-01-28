package com.tengshi.basemodule.retrofitNet.bean;

import java.util.List;

/**
 * 作者 : Adam on 2018/12/5.
 * 邮箱 : itgaojian@163.com
 * 描述 : 响应数据基类
 */
public class BaseResponseBean<D, L> {
    private String id;
    private String msg;
    private String state;
    private D data;
    private List<L> list;

    @Override
    public String toString() {
        return "BaseResponseBean{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                ", state='" + state + '\'' +
                ", data=" + data +
                ", list=" + list +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public List<L> getList() {
        return list;
    }

    public void setList(List<L> list) {
        this.list = list;
    }
}
