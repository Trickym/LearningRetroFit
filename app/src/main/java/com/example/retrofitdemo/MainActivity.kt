    package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

    class MainActivity : AppCompatActivity() {
        private var recyclerView: RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView= findViewById(R.id.recycler_view)
        getDemoData()
    }

    private fun  getDemoData(){
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
                    recyclerView!!.adapter = AdapterData(responseBody)
                    recyclerView!!.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView!!.setHasFixedSize(true)
                }

                override fun onFailure(call: Call<List<DemoDataItem>?>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Something went wrong!",Toast.LENGTH_SHORT).show()
                }
            })
    }
}