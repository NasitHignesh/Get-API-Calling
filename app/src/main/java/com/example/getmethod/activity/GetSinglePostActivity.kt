package com.example.getmethod.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.getmethod.R
import com.example.getmethod.interfaces.ApiInterface
import com.example.getmethod.model.MyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetSinglePostActivity : AppCompatActivity() {

    private lateinit var edittext: EditText
    private lateinit var searchButton: ImageButton
    private lateinit var innerLinearlayout: LinearLayout
    private lateinit var singleId: TextView
    private lateinit var singleTitle: TextView
    private lateinit var singleBody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_single_post)

        edittext = findViewById(R.id.edittext)
        searchButton = findViewById(R.id.imageButton)
        innerLinearlayout = findViewById(R.id.innerLinearlayout)
        singleId = findViewById(R.id.singleId)
        singleTitle = findViewById(R.id.singleTitle)
        singleBody = findViewById(R.id.singleBody)

        searchButton.setOnClickListener {
            val id: String = edittext.text.toString()
            val baseUrl = "https://jsonplaceholder.typicode.com/"

            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)

            val retrofitSingleData = retrofitBuilder.getSinglePostData(id.toInt())

            retrofitSingleData.enqueue(object : Callback<MyData?> {
                override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                    // API call is successful and data is received
                    val responseBody = response.body()

                    if (responseBody == null) {
                        Toast.makeText(this@GetSinglePostActivity, "Data not available", Toast.LENGTH_SHORT).show()
                    } else {
                        innerLinearlayout.visibility = View.VISIBLE

                        singleId.text = "Id : " + responseBody[0].id.toString()
                        singleTitle.text = "Title : " + responseBody[0].title.toString()
                        singleBody.text = "Body : " + responseBody[0].body.toString()
                    }
                }

                override fun onFailure(call: Call<MyData?>, t: Throwable) {
                    // API call fails to get data
                    Toast.makeText(this@GetSinglePostActivity, "Failed to get data", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
}
