package com.starsolns.retrofitdemo.api

import com.starsolns.retrofitdemo.models.Post
import com.starsolns.retrofitdemo.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DemoApi {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int ): Post

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int ): User

}