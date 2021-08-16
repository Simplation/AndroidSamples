package com.simplation.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/**
 * @作者: Simplation
 * @日期: 2021/08/16 20:49
 * @描述:
 * @更新:
 */
class MyDataBaseHelper(
    private val context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) :
    SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        val CREATE_BOOK: String =
            "create table Book(id integer primary key autoincrement, author text, price real, pages integer, name text)"
        val CREATE_CATEGORY: String =
            "create table Category(id integer primary key autoincrement, category_name text, category_code integer)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_BOOK)
        db.execSQL(CREATE_CATEGORY)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists Book")
        db.execSQL("drop table if exists Category")
        onCreate(db)
    }

}
