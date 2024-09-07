package com.example.getmethod.interfaces

import com.example.getmethod.model.CommentsData
import com.example.getmethod.model.CommentsDataItem
import com.example.getmethod.model.MyData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("posts")
    fun getPostData(): Call<MyData>

    @GET("posts")
    fun getSinglePostData(@Query("id") id: Int): Call<MyData>

    @GET("posts")
    fun getCommentData(): Call<CommentsData>

    @GET("posts/{postId}/comments")
    fun getCommentData(@Path("postId") postId: Int = 1): Call<CommentsData>

    @GET("comments")
    fun getCommentsForPostQuery(@Query("postId") postId: Int = 1): Call<CommentsData>
}