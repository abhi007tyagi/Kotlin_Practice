package com.tyagiabhinav.loremipsum.model.service

import com.tyagiabhinav.loremipsum.model.dao.Post
import retrofit2.http.GET

interface PostsApi {
    @GET("/posts")
    suspend fun getPosts(): List<Post>

}