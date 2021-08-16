package com.simplation.database

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.simplation.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val dbHelper: MyDataBaseHelper = MyDataBaseHelper(this, "BookStore.db", null, 1)
    // 升级数据库
    // private val dbHelper: MyDataBaseHelper = MyDataBaseHelper(this, "BookStore.db", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }

        // 插入数据
        binding.addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()

            // 开始添加第一条数据
            values.put("name", "The Da Vinci Code")
            values.put("author", "Dan Brown")
            values.put("pages", 454)
            values.put("price", 16.97)
            db.insert("Book", null, values)

            values.clear()

            // 开始添加第二条数据
            values.put("name", "The Lost Symbol")
            values.put("author", "Dan Brown")
            values.put("pages", 520)
            values.put("price", 16.97)
            db.insert("Book", null, values)
        }

        // 更新数据
        binding.updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()

            values.put("price", 10.99)
            db.update("Book", values, "name = 1", arrayOf("The Da Vinci Code"))
        }

        // 删除数据
        binding.deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > 1", arrayOf("500"))
        }

        // 查询数据
        binding.queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 查询 Book 表中的所有数据
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍历 cursor
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))

                    Log.d("--- log -- ", "book name is $name")
                    Log.d("--- log -- ", "book author is $author")
                    Log.d("--- log -- ", "book pages is $pages")
                    Log.d("--- log -- ", "book price is $price")
                } while (cursor.moveToNext())
            }

            cursor.close()
        }
    }
}