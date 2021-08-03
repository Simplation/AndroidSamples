package com.simplation.activity

import android.app.Activity

/**
 * @作者: Simplation
 * @日期: 2021/8/3 15:57
 * @描述:
 * @更新:
 */
object ActivityCollector {
    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }
}
