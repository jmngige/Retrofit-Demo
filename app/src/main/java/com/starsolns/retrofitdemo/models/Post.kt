package com.starsolns.retrofitdemo.models

import java.io.Serializable

data class Post(
    val id: Int,
    val userId: Int,
    val body: String,
    val title: String
): Serializable