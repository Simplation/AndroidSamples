package com.simplation.localstorage

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns

/**
 * @作者: Simplation
 * @日期: 2020/02/17 16:17
 * @描述:
 * @更新:
 */

// provider 唯一标示信息
const val CONTENT_AUTHORITY = "com.simplation.localstorage.myprovider"

// 基础 Uri
val BASE_CONTENT_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

// 操作表的名称
const val PATH_TEST = "people"

object ContentData {
    // 完整 Uri
    val CONTENT_URI: Uri = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TEST).build()

    // 表中记录信息
    object TestEntry : BaseColumns {

        internal fun buildUri(id: Long): Uri {
            return ContentUris.withAppendedId(CONTENT_URI, id)
        }

        internal const val TABLE_NAME = "userInfo"
        const val COLUMN_NAME = "name"
        const val COLUMN_SEX = "sex"
        const val COLUMN_AGE = "age"
    }

}