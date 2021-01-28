package com.tengshi.basemodule.databases;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * 作者: Adam
 * 日期: 2019-11-18 - 16:43
 * 邮箱: itgaojian@163.com
 * 描述:  BaseDao
 */
public interface BaseDao<T> {


    /**
     * 新增
     *
     * @param t
     */
    @Insert
    void insert(T... t);

    /**
     * 更新
     *
     * @param t
     * @return
     */
    @Update
    int update(T... t);

    /**
     * 删除
     *
     * @param goods
     * @return
     */
    @Delete
    int delete(T... goods);

}
