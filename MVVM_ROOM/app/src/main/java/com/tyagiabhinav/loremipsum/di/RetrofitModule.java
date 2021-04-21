package com.tyagiabhinav.loremipsum.di;

import com.tyagiabhinav.loremipsum.model.service.PostsAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    @Singleton
    @Provides
    Retrofit providesRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    PostsAPI providesPostsAPI(Retrofit retrofit) {
        return retrofit.create(PostsAPI.class);
    }
}
