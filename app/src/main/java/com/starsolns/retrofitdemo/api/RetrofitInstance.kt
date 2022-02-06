package com.starsolns.retrofitdemo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val baseUrl = "https://jsonplaceholder.typicode.com/"

object RetrofitInstance {

    private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val api: DemoApi by lazy{
        retrofit.create(DemoApi::class.java)
    }
}