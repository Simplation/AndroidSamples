package com.simplation.database

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.simplation.database.databinding.ActivityDbpBinding

class DBPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDbpBinding
    private lateinit var newId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDbpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            addData.setOnClickListener {
                val uri: Uri = Uri.parse("content://com.simplation.database.provider/book")
                val values = ContentValues()
                values.put("name", "The Da Vinci Code")
                values.put("author", "Dan Brown")
                values.put("pages", 500)
                values.put("price", 55.55)

                val newUri: Uri? = contentResolver.insert(uri, values)
                try {
                    newId = newUri?.pathSegments?.get(1).toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            queryData.setOnClickListener {
                val uri = Uri.parse("content://com.simplation.database.provider/book/")
                val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        val name: String = cursor.getString(cursor.getColumnIndex("name"))
                        val author: String = cursor.getString(cursor.getColumnIndex("author"))
                        val pages: Int = cursor.getInt(cursor.getColumnIndex("pages"))
                        val price: Double = cursor.getDouble(cursor.getColumnIndex("price"))
                        Log.d("TAG", "book name is: $name")
                        Log.d("TAG", "book author is: $author")
                        Log.d("TAG", "book pages is: $pages")
                        Log.d("TAG", "book price is: $price")
                    }
                    cursor.close()
                }
            }

            deleteData.setOnClickListener {
                val uri = Uri.parse("content://com.simplation.database.provider/book/$newId")
                contentResolver.delete(uri, null, null)
            }

            updateData.setOnClickListener {
                val uri = Uri.parse("content://com.simplation.database.provider/book/$newId")
                val values = ContentValues()
                values.put("name", "The Da Vinci Code new")
                values.put("author", "Dan Brown new")
                values.put("pages", 454)
                values.put("price", 16.96)

                contentResolver.update(uri, values, null, null)
            }
        }

    }
}