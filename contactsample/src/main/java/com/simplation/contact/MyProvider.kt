package com.simplation.contact

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

/**
 * @作者: Simplation
 * @日期: 2021/08/10 23:14
 * @描述:
 * @更新:
 */
class MyProvider : ContentProvider() {

    companion object {
        const val TABLE1_DIR = 0
        const val TABLE1_ITEM = 1
        const val TABLE2_DIR = 2
        const val TABLE2_ITEM = 3
        val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    // 初始化内容提供器
    override fun onCreate(): Boolean {
        uriMatcher.addURI("com.example.contactstest.provider", "table1", TABLE1_DIR)
        uriMatcher.addURI("com.example.contactstest.provider", "table1/#", TABLE1_ITEM)
        uriMatcher.addURI("com.example.contactstest.provider", "table2", TABLE2_DIR)
        uriMatcher.addURI("com.example.contactstest.provider", "table2/#", TABLE2_ITEM)

        return false
    }

    // 从内容提供器中查询数据
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when {
            uriMatcher.match(uri) == TABLE1_DIR -> {
                // 查询 Table1 中的所有数据
            }
            uriMatcher.match(uri) == TABLE1_ITEM -> {
                // 查询 Table1 中单条数据
            }
            uriMatcher.match(uri) == TABLE2_DIR -> {
                // 查询 Table2 中的所有数据
            }
            uriMatcher.match(uri) == TABLE2_ITEM -> {
                // 查询 Table2 中的单条数据
            }
        }

        return null
    }

    // 根据传入的内容 URI 来返回相应的 MIME 类型
    override fun getType(uri: Uri): String? {
        return null
    }

    // 向内容提供器中插入一条数据
    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        return null
    }

    // 从内容提供器中删除数据
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    // 更新内容提供器中已有的数据
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}