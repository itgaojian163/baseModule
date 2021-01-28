package com.tengshi.basemodule.databases;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * 作者: Adam
 * 日期: 2019-11-19 - 10:01
 * 邮箱: itgaojian@163.com
 * 描述:
 */
@Dao
public interface GoodsDao {
    /**
     * 新增
     *
     * @param t
     */
    @Insert
    void insert(GoodsEntity... t);

    /**
     * 更新
     *
     * @param t
     * @return
     */
    @Update
    int update(GoodsEntity... t);

    /**
     * 删除
     *
     * @param goods
     * @return
     */
    @Delete
    int delete(GoodsEntity... goods);

    @Query("select * from goods")
    public abstract List<GoodsEntity> getAll();

}
