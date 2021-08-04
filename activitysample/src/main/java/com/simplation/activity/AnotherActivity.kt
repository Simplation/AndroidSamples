package com.simplation.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AnotherActivity : AppCompatActivity() {

    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
            Intent(context, AnotherActivity::class.java).run {
                putExtra("param1", data1)
                putExtra("param2", data2)
                context.startActivity(this)
            }
        }

        /*
        // 传递对象
        fun actionStart(context: Context, user: User) {
            Intent(context, AnotherActivity::class.java).run {
                val bundle = Bundle()
                bundle.putParcelable("user", user)
                putExtras(bundle)
                context.startActivity(this)
            }
        }*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)
    }
}