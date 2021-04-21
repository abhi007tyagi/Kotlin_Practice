package com.tyagiabhinav.loremipsum.model.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsAPI {
    @GET("/posts")
    Call<String> getPosts();
}
