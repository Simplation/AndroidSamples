package com.simplation.localstorage

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import java.sql.SQLException

/**
 * @作者: Simplation
 * @日期: 2020/02/17 16:07
 * @描述:
 * @更新:
 */
/*
class MyProvider : ContentProvider() {
    companion object {
        lateinit var dbOpenHelper: DatabaseHelper
        private const val TEST = 100 //匹配码

    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        //获得可写数据库
        val db = dbOpenHelper.writableDatabase
        val returnUri: Uri
        val _id: Long
        when (buildUriMatcher().match(uri)) {
            TEST -> {
                //插入数据
                _id = db.insert(ContentData.TestEntry.TABLE_NAME, null, values)
                returnUri =
                    if (_id > 0) ContentData.TestEntry.buildUri(_id) else throw SQLException("Failed to insert row into $uri")
            }
            else -> throw SQLException("Unknown uri: $uri")
        }
        return returnUri
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        //获得可读数据库
        val db = dbOpenHelper.readableDatabase
        //查到的结果是游标类型。
        var cursor: Cursor? = null
        when (buildUriMatcher().match(uri)) {
            TEST -> cursor = db.query(
                ContentData.TestEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                sortOrder,
                null,
                null
            )
        }

        return cursor
    }

    override fun onCreate(): Boolean {
        dbOpenHelper = DatabaseHelper(context, "userInfo", null, 1)
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}*/
