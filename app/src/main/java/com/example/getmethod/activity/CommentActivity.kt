package com.example.getmethod.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getmethod.R
import com.example.getmethod.adapter.CommentAdapter
import com.example.getmethod.interfaces.ApiInterface
import com.example.getmethod.model.CommentsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        recyclerView = findViewById(R.id.commentsRecycleView)

        val baseUrl = "https://jsonplaceholder.typicode.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiInterface::class.java)
        val retrofitData = apiService.getCommentData(2)
        val getCommentsForPostQuery = apiService.getCommentsForPostQuery(2)

        retrofitData.enqueue(object : Callback<CommentsData?> {
            override fun onResponse(call: Call<CommentsData?>, response: Response<CommentsData?>) {
                val responseBody = response.body()

                if (responseBody == null) {
                    Toast.makeText(this@CommentActivity, "Data not available", Toast.LENGTH_SHORT).show()
                } else {
                    commentAdapter = CommentAdapter(this@CommentActivity, responseBody)
                    recyclerView.adapter = commentAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this@CommentActivity)
                }
            }

            override fun onFailure(call: Call<CommentsData?>, t: Throwable) {
                Toast.makeText(this@CommentActivity, "Not get data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
