package com.example.w3d3_okhttpdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient()
        // GET
        val get = Request.Builder()
            .url("https://reqres.in/api/users?page=2")
            .build()
        client.newCall(get).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val responseBody = response.body
                    if (!response.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    }
                    Log.i("data", responseBody!!.string())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })

        // POST
        val postData = JsonObject()
        postData.addProperty("name", "morpheus")
        postData.addProperty("job", "leader")

        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
        val postBody = RequestBody.create(JSON, postData.toString())
        val post = Request.Builder()
            .url("https://reqres.in/api/users")
            .post(postBody)
            .build()

        client.newCall(post).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val responseBody = response.body
                    if (!response.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    }
                    Log.i("data", responseBody!!.string())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })

    }

}