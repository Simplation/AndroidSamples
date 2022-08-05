package com.simplation.json

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.simplation.json.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnJsonObject.setOnClickListener {
                val jsonObject = "{\n" +
                        "    \"name\": \"Simplation\",\n" +
                        "    \"age\": 20,\n" +
                        "    \"male\": true\n" +
                        "}"

                val json = JSONObject(jsonObject)
                val name = json.getString("name")
                val age = json.getInt("age")
                val male = json.getString("male")
                Log.d("TAG", "Result: ==> name = $name - age = $age - male = $male")
            }

            btnJsonArray.setOnClickListener {
                val jsonArray = "[\n" +
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

                val array = JSONArray(jsonArray)
                for (i in 0 until array.length()) {
                    val jsonObject = array.getJSONObject(i)
                    val name = jsonObject.getString("name")
                    val age = jsonObject.getInt("age")
                    val male = jsonObject.getString("male")
                    Log.d("TAG", "Result: ==> name = $name - age = $age - male = $male")
                }
            }

            btnComplexJsonObject.setOnClickListener {
                val complexJsonObject = "{\n" +
                        "    \"name\": \"Simplation\",\n" +
                        "    \"age\": 20,\n" +
                        "    \"male\": true,\n" +
                        "    \"address\": {\n" +
                        "        \"street\": \"simplation street\",\n" +
                        "        \"city\": \"Shanxi\",\n" +
                        "        \"country\": \"China\"\n" +
                        "    }\n" +
                        "}"

                val jsonObject = JSONObject(complexJsonObject)
                val name = jsonObject.getString("name")
                val age = jsonObject.getInt("age")
                val male = jsonObject.getString("male")

                val address = jsonObject.getJSONObject("address")
                val street = address.getString("street")
                val city = address.getString("city")
                val country = address.getString("country")
                Log.d(
                    "TAG",
                    "Result: ==> name = $name - age = $age - male = $male - street = $street - city = $city - country = $country"
                )
            }

            btnComplexJsonArray.setOnClickListener {
                val complexJsonArray = "[\n" +
                        "    {\n" +
                        "        \"name\": \"Simplation\",\n" +
                        "        \"age\": 20,\n" +
                        "        \"male\": true,\n" +
                        "        \"address\": {\n" +
                        "            \"street\": \"Simplation Street\",\n" +
                        "            \"city\": \"Shanxi\",\n" +
                        "            \"country\": \"China\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"simplation\",\n" +
                        "        \"age\": 22,\n" +
                        "        \"male\": false,\n" +
                        "        \"address\": {\n" +
                        "            \"street\": \"simplation street\",\n" +
                        "            \"city\": \"Hunan\",\n" +
                        "            \"country\": \"China\"\n" +
                        "        }\n" +
                        "    }\n" +
                        "]"

                val jsonArray = JSONArray(complexJsonArray)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val name = jsonObject.getString("name")
                    val age = jsonObject.getInt("age")
                    val male = jsonObject.getString("male")

                    val address = jsonObject.getJSONObject("address")
                    val street = address.getString("street")
                    val city = address.getString("city")
                    val country = address.getString("country")
                    Log.d(
                        "TAG",
                        "Result: ==> name = $name - age = $age - male = $male - street = $street - city = $city - country = $country"
                    )
                }
            }

            btnParseComplexJson.setOnClickListener {
                val complexJson = "{\n" +
                        "    \"buses\": {\n" +
                        "        \"bus\": [\n" +
                        "            {\n" +
                        "                \"last_foot_dist\": \"0\",\n" +
                        "                \"time\": \"37\",\n" +
                        "                \"segments\": {\n" +
                        "                    \"segment\": [\n" +
                        "                        {\n" +
                        "                            \"line_name\": \"立珊专线(中南大学学生公寓-长沙火车站)\",\n" +
                        "                            \"foot_dist\": \"362\",\n" +
                        "                            \"stat_xys\": \"\",\n" +
                        "                            \"stats\": \"岳麓山南;湖南师大;二里半;岳麓山北;市四医院;华图教育(太平街口);牛耳教育(南阳街口);韭菜园;曙光路口;长岛路口;长沙火车站\",\n" +
                        "                            \"end_stat\": \"长沙火车站\",\n" +
                        "                            \"line_xys\": \"\",\n" +
                        "                            \"line_dist\": \"7535\",\n" +
                        "                            \"start_stat\": \"岳麓山南\"\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                \"foot_dist\": \"362\",\n" +
                        "                \"dist\": \"7897\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"last_foot_dist\": \"0\",\n" +
                        "                \"time\": \"41\",\n" +
                        "                \"segments\": {\n" +
                        "                    \"segment\": [\n" +
                        "                        {\n" +
                        "                            \"line_name\": \"旅1路(科教新村-长沙火车站)\",\n" +
                        "                            \"foot_dist\": \"337\",\n" +
                        "                            \"stat_xys\": \"\",\n" +
                        "                            \"stats\": \"岳麓山南;湖南师大;二里半;岳麓山北;市四医院;华图教育(太平街口);蔡锷中路口;韭菜园;乔庄;曙光路口;长岛路口;五一东村;车站路口;长沙火车站\",\n" +
                        "                            \"end_stat\": \"长沙火车站\",\n" +
                        "                            \"line_xys\": \"\",\n" +
                        "                            \"line_dist\": \"7822\",\n" +
                        "                            \"start_stat\": \"岳麓山南\"\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                \"foot_dist\": \"337\",\n" +
                        "                \"dist\": \"8159\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}"

                val jsonObject = JSONObject(complexJson)
                val buses = jsonObject.getJSONObject("buses")
                val bus = buses.getJSONArray("bus")

                for (i in 0 until bus.length()) {
                    val busObject = bus.getJSONObject(i)
                    val last_foot_dist = busObject.getString("last_foot_dist")
                    val time = busObject.getString("time")
                    val segments = busObject.getJSONObject("segments")

                    val segment = segments.getJSONArray("segment")
                    for (j in 0 until segment.length()) {
                        val seg = segment.getJSONObject(j)
                        val line_name = seg.getString("line_name")
                        val foot_dist = seg.getString("foot_dist")
                        val stat_xys = seg.getString("stat_xys")
                        val stats = seg.getString("stats")
                        val end_stat = seg.getString("end_stat")
                        val line_xys = seg.getString("line_xys")
                        val line_dist = seg.getString("line_dist")
                        val start_stat = seg.getString("start_stat")

                        Log.d("TAG", "Result: ==> line_name - $line_name - foot_dist - $foot_dist - stat_xys - $stat_xys - stats - $stats - end_stat - $end_stat" +
                                " - line_xys - $line_xys - line_dist - $line_dist - start_stat - $start_stat")
                    }

                    val dist = busObject.getString("dist")

                    Log.d("TAG", "Result: ==> last_foot_dist - $last_foot_dist - time - $time - dist - $dist")
                }
            }
        }

    }
}