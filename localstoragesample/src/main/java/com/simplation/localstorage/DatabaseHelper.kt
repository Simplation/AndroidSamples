package com.simplation.localstorage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Author: Simplation
 * Date: 2020/02/13 18:56
 * Description:
 */
class DatabaseHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table if not exists userInfo(_id integer primary key autoincrement, name text not null, sex text not null, age integer not null)")
        db.execSQL("insert into userInfo(name, sex, age) values('张三', '男', 18)")
        db.execSQL("insert into userInfo(name, sex, age) values('李四', '女', 19)")
        db.execSQL("insert into userInfo(name, sex, age) values('王五', '男', 20)")
    }

    /**
     * 当数据库需要修改的时候（两个数据库版本不同），Android 系统会主动的调用这个方法。
     * 一般我们在这个方法里边删除数据库表，并建立新的数据库表.
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // 三个参数，一个  SQLiteDatabase 对象，一个旧的版本号和一个新的版本号
        // 需要对比新版本和旧版本的版本号
        /*if (oldVersion == 1 && newVersion == 2) {
            val sql = "alter table userInfo add [desc] nvarchar(300)"
            db.execSQL(sql);
        }*/
    }

    override fun onOpen(db: SQLiteDatabase) {
        // 每次成功打开数据库后首先被执行
        super.onOpen(db)
    }

}