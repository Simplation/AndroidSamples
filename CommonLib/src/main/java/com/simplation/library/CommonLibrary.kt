package com.simplation.library

import android.app.Application

/**
 * @作者: Simplation
 * @日期: 2021/8/3 10:17
 * @描述:
 * @更新:
 */
class CommonLibrary private constructor() {
    lateinit var application: Application
    lateinit var baseUrl: String
    lateinit var apiClass: Class<*>

    fun initLibrary(mApplication: Application, mBaseUrl: String, mApiClass: Class<*>) {
        this.application = mApplication
        this.baseUrl = mBaseUrl
        this.apiClass = mApiClass
    }

    companion object {
        const val TAG = "Common_Library"

        val getInstance: CommonLibrary
            get() = InnerClass.instance
    }

    private object InnerClass {
        var instance = CommonLibrary()
    }
}
