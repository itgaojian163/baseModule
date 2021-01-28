package com.tengshi.basemodule.databases;

import android.content.Context;

import com.tengshi.basemodule.globalconfig.PathConfig;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * 作者: Adam
 * 日期: 2019-11-18 - 16:29
 * 邮箱: itgaojian@163.com
 * 描述: Room数据库
 */
@Database(entities = {GoodsEntity.class, BookEntity.class}, version = PathConfig.DB_VERSION)
public abstract class RoomDataBases extends RoomDatabase {

    public abstract GoodsDao getDao();

    public abstract BooksDao getBookDao();

    private static volatile RoomDataBases instance;
    static Migration MIGRATION_1_2 = new Migration(1, PathConfig.DB_VERSION) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `books` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`name` TEXT, " +
                    "`price` REAL NOT NULL, " +
                    "`detail` TEXT)");
        }
    };

    public static RoomDataBases getInstance(Context context) {
        if (instance == null) {
            synchronized (RoomDatabase.class) {
                if (instance == null) {
                    instance = create(context);
                }
            }
        }
        return instance;
    }

    private static RoomDataBases create(final Context context) {
        return Room.databaseBuilder(context,
                RoomDataBases.class,
                PathConfig.DATABASE_NAME)
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build();
    }


}
