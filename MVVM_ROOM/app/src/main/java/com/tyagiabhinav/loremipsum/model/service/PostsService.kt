package com.tyagiabhinav.loremipsum.model.service;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;


public class PostsService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static Retrofit instance;

    public static synchronized Retrofit getInstance(Context context) {
        if(instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public interface PostsAPI {

        @GET("/posts")
        Call<String> getPosts();
    }


}
