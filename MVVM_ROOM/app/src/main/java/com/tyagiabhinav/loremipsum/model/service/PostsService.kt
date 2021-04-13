package com.tyagiabhinav.loremipsum.model.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsService {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
    }

    val postsAPi: PostsApi by lazy {
        retrofitBuilder
                .build()
                .create(PostsApi::class.java)
    }

}