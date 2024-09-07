package com.example.getmethod.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getmethod.R
import com.example.getmethod.adapter.MyAdapter
import com.example.getmethod.interfaces.ApiInterface
import com.example.getmethod.model.MyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var getSingleProductButton:Button
    private lateinit var commentButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        getSingleProductButton = findViewById(R.id.getSingleProductButton)
        commentButton = findViewById(R.id.commentButton)

        val baseUrl ="https://jsonplaceholder.typicode.com/"

        val retroFitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retroFitData = retroFitBuilder.getPostData()

        getSingleProductButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GetSinglePostActivity::class.java)
            startActivity(intent)
        }

        commentButton.setOnClickListener {
            val intent = Intent(this@MainActivity, CommentActivity::class.java)
            startActivity(intent)
        }

        retroFitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //  api is run successful run and get data

                val responseBody = response.body()

                if (responseBody == null) {
                    Toast.makeText(this@MainActivity, "Data not available", Toast.LENGTH_SHORT).show()
                }else{
                    myAdapter = responseBody?.let { MyAdapter(this@MainActivity, it) }!!
                    recyclerView.adapter = myAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // api not get data
                Toast.makeText(this@MainActivity,"Not get data", Toast.LENGTH_SHORT).show()
            }
        })

    }
}