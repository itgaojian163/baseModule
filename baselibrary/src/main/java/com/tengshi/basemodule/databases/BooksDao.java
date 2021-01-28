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
public interface BooksDao {
    /**
     * 新增
     *
     * @param t
     */
    @Insert
    void insert(BookEntity... t);

    /**
     * 更新
     *
     * @param t
     * @return
     */
    @Update
    int update(BookEntity... t);

    /**
     * 删除
     *
     * @param goods
     * @return
     */
    @Delete
    int delete(BookEntity... goods);

    @Query("select * from books")
    public abstract List<BookEntity> getAll();

}
