    package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

    class MainActivity : AppCompatActivity() {
        private lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.txt_view)
        getDemoData()
    }

    private fun getDemoData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(ApiInterface::class.java)
        val string = StringBuilder()
        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<DemoDataItem>?> {
                override fun onResponse(
                    call: Call<List<DemoDataItem>?>,
                    response: Response<List<DemoDataItem>?>
                ) {
                    val responseBody = response.body()!!
                    for(each in responseBody){
                        string.append("Title : ${each.title}")
                        string.append("\n")
                        string.append("---------------------")
                        string.append("\n")

                    }
                    tv.text = string
                }

                override fun onFailure(call: Call<List<DemoDataItem>?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}