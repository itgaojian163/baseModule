package com.tengshi.basemodule.retrofitNet.bean;

/**
 * 作者 : Adam on 2018/12/5.
 * 邮箱 : itgaojian@163.com
 * 描述 : <功能描述>
 */
public class TestPhotoBean {
    private String id;
    private String photoPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "TestPhotoBean{" +
                "id='" + id + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
