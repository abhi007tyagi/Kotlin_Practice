package com.tyagiabhinav.loremipsum.model;

import android.util.Log;

import com.tyagiabhinav.loremipsum.model.db.Posts;
import com.tyagiabhinav.loremipsum.model.db.PostsDao;
import com.tyagiabhinav.loremipsum.model.service.Parser;
import com.tyagiabhinav.loremipsum.model.service.PostsAPI;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository {

    private static final String TAG = PostsRepository.class.getName();

    private final PostsDao postsDao;
    private final PostsAPI postsAPI;
    private Posts testPost;
    private final MutableLiveData<Boolean> isLoading;
    private LiveData<List<Posts>> allPosts;

    @Inject
    public PostsRepository (PostsDao postsDao, PostsAPI postsAPI){
        this.postsDao = postsDao;
        this.postsAPI = postsAPI;
        this.isLoading = new MutableLiveData<>();
        fetchPosts();
        allPosts = postsDao.getPosts();
        //testPost = postsDao.getPost();
    }

    private void fetchPosts(){
        isLoading.postValue(true);
        Call<String> call = postsAPI.getPosts();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: "+response.code());
                isLoading.postValue(false);
                update(Parser.getPosts(response.body()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());
                isLoading.postValue(false);
                update(Arrays.asList(new Posts("Error Occurred","Try again later!!!")));
            }
        });
    }

//    public void insert(List<Posts> posts){
//        new Thread(() -> {
//            // do background stuff here
//            postsDao.insert(posts);
//        }).start();
//    }

    public void update(List<Posts> posts){
        new Thread(() -> {
            // do background stuff here
            postsDao.deleteAll();
            postsDao.insert(posts);
        }).start();
    }

//    public void delete(){
//        new Thread(() -> {
//            // do background stuff here
//            postsDao.deleteAll();
//        }).start();
//    }

    public LiveData<List<Posts>> getPosts(){
        if(allPosts == null){
            allPosts = new MutableLiveData<>();
            loadPosts();
        }
        return allPosts;
    }

    private void loadPosts(){
        new Thread(() -> {
            // do background stuff here
            allPosts = postsDao.getPosts();
        }).start();
    }

    public Posts getTestPost(){
        return testPost;
    }

    public LiveData<Boolean> isLoading() {
            return isLoading;
    }
}
