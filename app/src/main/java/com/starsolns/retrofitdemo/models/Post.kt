package com.starsolns.retrofitdemo.models

import java.io.Serializable

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)