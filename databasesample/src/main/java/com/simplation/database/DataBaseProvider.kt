package com.simplation.database

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri


/**
 * @作者: Simplation
 * @日期: 2017/05/26 21:27
 * @描述:
 * @更新:
 */

const val BOOK_DIR = 1
const val BOOK_ITEM = 2
const val CATEGORY_DIR = 3
const val CATEGORY_ITEM = 4

const val AUTHORITY = "com.simplation.database.provider"

class DataBaseProvider : ContentProvider() {

    var uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    private lateinit var dbHelper: MyDataBaseHelper

    init {
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR)
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM)
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR)
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM)
    }

    override fun onCreate(): Boolean {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = MyDataBaseHelper(context!!, "Book.db", null, 1)
        return true
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String?>?): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        var deleteRows = 0
        when (uriMatcher.match(uri)) {
            BOOK_DIR -> deleteRows = db.delete("Book", selection, selectionArgs)
            BOOK_ITEM -> {
                val bookId: String = uri.pathSegments[1]
                deleteRows = db.delete("Book", "id = ?", arrayOf(bookId))
            }
            CATEGORY_DIR -> deleteRows = db.delete("Category", selection, selectionArgs)
            CATEGORY_ITEM -> {
                val categoryId: String = uri.pathSegments[1]
                deleteRows = db.delete("Category", "id = ?", arrayOf(categoryId))
            }
        }
        return deleteRows
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher.match(uri)) {
            BOOK_DIR -> return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.book"
            BOOK_ITEM -> return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.book"
            CATEGORY_DIR -> return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.category"
            CATEGORY_ITEM -> return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.category"
        }
        return null
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // 添加数据
        val db: SQLiteDatabase = dbHelper.writableDatabase
        var uriReturn: Uri? = null
        when (uriMatcher.match(uri)) {
            BOOK_DIR -> {}
            BOOK_ITEM -> {
                val newBookId = db.insert("Book", null, values)
                uriReturn = Uri.parse("content://$AUTHORITY/book/$newBookId")
            }
            CATEGORY_DIR -> {}
            CATEGORY_ITEM -> {
                val newCategory = db.insert("category", null, values)
                uriReturn = Uri.parse("content://$AUTHORITY/book/$newCategory")
            }
        }
        return uriReturn
    }

    override fun query(
        uri: Uri, projection: Array<String?>?, selection: String?,
        selectionArgs: Array<String?>?, sortOrder: String?
    ): Cursor? {
        // 查询数据
        val db: SQLiteDatabase = dbHelper.writableDatabase
        var cursor: Cursor? = null
        when (uriMatcher.match(uri)) {
            BOOK_DIR ->                 // 查询表 1 中所有数据
                cursor = db.query(
                    "Book", projection, selection, selectionArgs, null, null,
                    sortOrder
                )
            BOOK_ITEM -> {
                // 查询 table1 表中的单条数据
                val bookId: String = uri.pathSegments[1]
                cursor = db.query(
                    "Book", projection, "id = ?", arrayOf(bookId),
                    null, null, null, sortOrder
                )
            }
            CATEGORY_DIR ->                 // 查询表 2 中的所有数据
                cursor = db.query(
                    "Category", projection, selection, selectionArgs, null, null,
                    sortOrder
                )
            CATEGORY_ITEM -> {
                // 查询 table2 表中的单条数据
                val categoryId: String = uri.pathSegments[1]
                cursor = db.query(
                    "Category", projection, "id = ?", arrayOf(categoryId),
                    null, null, null, sortOrder
                )
            }
        }
        return cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String?>?
    ): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        var updatedRows = 0
        when (uriMatcher.match(uri)) {
            BOOK_DIR -> updatedRows = db.update("Book", values, selection, selectionArgs)
            BOOK_ITEM -> {
                val bookId: String = uri.pathSegments[1]
                updatedRows = db.update("Book", values, "id = ?", arrayOf(bookId))
            }
            CATEGORY_DIR -> updatedRows = db.update("Category", values, selection, selectionArgs)
            CATEGORY_ITEM -> {
                val categoryId: String = uri.pathSegments[1]
                updatedRows = db.update("Category", values, "id = ?", arrayOf(categoryId))
            }
        }
        return updatedRows
    }
}