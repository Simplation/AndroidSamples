package com.simplation.localstorage.all

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplation.localstorage.DatabaseHelper
import com.simplation.localstorage.databinding.ActivitySqliteBinding

class SQLiteActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySqliteBinding
    private lateinit var db: SQLiteDatabase

    companion object {
        const val VERSION = 1
        const val TABLE_NAME = "userInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqliteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = intent.getStringExtra("title")

        initDatabase()

        binding.apply {
            // 查询操作
            btnQuery.setOnClickListener {
                query()
            }

            // 添加操作
            btnAdd.setOnClickListener {
                add()
            }

            // 删除操作
            btnDelete.setOnClickListener {
                delete()
            }

            // 修改操作
            btnUpdate.setOnClickListener {
                update()
            }
        }
    }

    private fun initDatabase() {
        val databaseHelper = DatabaseHelper(this, TABLE_NAME, null, VERSION)

        /**
         * getWritableDatabase() 和 getReadableDatabase() 方法都可以获取一个用于操作数据库的 SQLiteDatabase 实例。
         *  其中 getReadableDatabase() 方法则是先以读写方式打开数据库，如果数据库的磁盘空间满了，就会打开失败，当打开失败后
         *  会继续尝试以只读方式打开数据库。如果该问题成功解决，则只读数据库对象就会关闭，然后返回一个可读写的数据库对象。
         *  getWritableDatabase() 方法以读写方式打开数据库，一旦数据库的磁盘空间满了，数据库就只能读而不能写，
         *  使用的是 getWritableDatabase() 方法就会出错。　
         */
        db = databaseHelper.writableDatabase
    }

    private fun update() {
        /*val contentVlaues = ContentValues()
        contentVlaues.put("name", "张三三")
        val whereClause = "name=?"
        val whereArgs = arrayOf("张三")
        db.update("userInfo", contentVlaues, whereClause, whereArgs)*/

        val updateSql = "update userInfo set name = '张三' where name = '张三22'"
        db.execSQL(updateSql)
    }

    private fun delete() {
        /*// 删除的条件
        val whereClause = "name=?"
        // 删除的条件参数
        val whereArgs = arrayOf("新来的")
        // 执行删除
        db.delete("userInfo", whereClause, whereArgs)*/

        val deleteSql = "delete from userInfo where name='新添加的内容'"
        db.execSQL(deleteSql)

        query()
    }

    private fun add() {
        /*val contentValues = ContentValues()
        contentValues.put("name", "新添加的内容")
        contentValues.put("sex", "女")
        contentValues.put("age", 21)
        db.insert(TABLE_NAME, null, contentValues)*/

        db.execSQL("insert into userInfo(name, sex, age) values('新添加的内容', '女', '24')")

        // 将新添加的数据查询出来
        query()
    }

    private fun query() {
        binding.apply {
            tvId.text = ""
            tvName.text = ""
            tvSex.text = ""
            tvAge.text = ""
        }

        val cursor = db.rawQuery("select * from userInfo", null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                binding.apply {
                    tvId.append("\n" + cursor.getString(cursor.getColumnIndex("_id")))
                    tvName.append("\n" + cursor.getString(cursor.getColumnIndex("name")))
                    tvSex.append("\n" + cursor.getString(cursor.getColumnIndex("sex")))
                    tvAge.append("\n" + cursor.getString(cursor.getColumnIndex("age")))
                }
            }
            cursor.close()
        }
    }
}