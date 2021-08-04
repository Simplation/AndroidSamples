package com.simplation.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * @作者: Simplation
 * @日期: 2021/08/04 22:10
 * @描述:
 * @更新:
 */
class BootCompleteReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_SHORT).show()
    }
}