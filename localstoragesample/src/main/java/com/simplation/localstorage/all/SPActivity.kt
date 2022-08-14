package com.simplation.localstorage.all

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.simplation.localstorage.R
import com.simplation.localstorage.databinding.ActivitySpBinding

/**
 * 用法
 *  1、获取 SharedPreferences 对象
 *  2、获取 SharedPreferences 内部接口 Editor 用来编辑 userInfo。
 *  3、取出 userInfo 中的数据。
 *  4、将取到的用户名赋给用户名编辑框。
 *
 * 优点
 *  SharedPreferences 对象与 SQLite 数据库相比显得格外轻量级，免去了创建数据库，创建表，写 SQL 语句等诸多操作，相对而言更加方便，简洁。
 * 缺点
 *  1、其职能存储 boolean，int，float，long 和 String 五种简单的数据类型。
 *  2、无法进行条件查询等。
 */
class SPActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpBinding
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = intent.getStringExtra("title")

        preferences = getSharedPreferences("userInfo", MODE_PRIVATE)

        initSP()

        val getName = preferences.getString("userName", null)
        if (getName == null) {
            binding.cbSave.isChecked = false
        } else {
            binding.cbSave.isChecked = true
            binding.etUser.setText(getName)
        }

    }

    private fun initSP() {
        binding.apply {
            btnLogin.setOnClickListener(onClickListener)
            btnClear.setOnClickListener(onClickListener)
        }

        preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        editor = preferences.edit()
    }

    private val onClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.btn_login -> {
                if ("admin" == binding.etUser.text.toString()
                        .trim() && "123456" == binding.etPwd.text.toString().trim()
                ) {
                    if (binding.cbSave.isChecked) {
                        editor.putString("userName", binding.etUser.text.toString().trim())
                        editor.commit()
                    } else {
                        editor.remove("userName")
                        editor.commit()
                    }
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btn_clear -> {
                binding.apply {
                    etUser.text = null
                    etPwd.text = null
                }
            }
        }
    }
}