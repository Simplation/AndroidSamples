package com.simplation.json

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.simplation.jsonsample", appContext.packageName)
    }


    @Test
    fun parseJsonObject() {
        val json = "{\n" +
                "    \"name\": \"Simplation\",\n" +
                "    \"age\": 20,\n" +
                "    \"male\": true\n" +
                "}"

        val gson = Gson()
        val bean: Bean = gson.fromJson(json, Bean::class.java)
        println(bean.name)
        Log.d("TAG", "parseJsonObject: ${bean.name} -  ${bean.age} -  ${bean.male}")
    }

    @Test
    fun parseJsonArray() {
        val json = "[\n" +
                "    {\n" +
                "        \"name\": \"Simplation\",\n" +
                "        \"age\": 20,\n" +
                "        \"male\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"simplation\",\n" +
                "        \"age\": 22,\n" +
                "        \"male\": false\n" +
                "    }\n" +
                "]"

        val gson = Gson()
        val type = object : TypeToken<List<Bean>>() {}.type
        val list: List<Bean> = gson.fromJson(json, type)
        Log.d("TAG", "parseJsonArray: $list")
        for (i in list.indices) {
            Log.d("TAG", "parseJsonArray: ${list[i].name} - ${list[i].age} - ${list[i].male}")
        }

        // new Gson().fromJson(jsonArray, new TypeToken<List<Object>>(){}.getType());

        // List
        // Type type = new TypeToken<List<Object>>() {}.getType();
        // List<Object> list = gson.fromJson(record, type);
        // Map
        // Type type = new TypeToken<Map<String, NetVO>>() {}.getType();
        // Map<String, NetVO> map = gson.fromJson(record, type);
    }

    data class Bean(val name: String, val age: Int, val male: String)
}