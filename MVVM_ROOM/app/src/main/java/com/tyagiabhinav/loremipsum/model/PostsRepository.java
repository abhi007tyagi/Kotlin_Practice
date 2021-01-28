package com.tyagiabhinav.loremipsum.model;

import android.content.Context;
import android.util.Log;

import com.tyagiabhinav.loremipsum.model.db.Posts;
import com.tyagiabhinav.loremipsum.model.db.PostsDao;
import com.tyagiabhinav.loremipsum.model.db.PostsDatabase;
import com.tyagiabhinav.loremipsum.model.service.Parser;
import com.tyagiabhinav.loremipsum.model.service.PostsService;

import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository {

    private static final String TAG = PostsRepository.class.getName();

    private PostsDao postsDao;
    private Posts testPost;
    private LiveData<List<Posts>> allPosts;
    private PostsService.PostsAPI postsAPI;

    public PostsRepository (Context context){
        PostsDatabase postsDatabase = PostsDatabase.getInstance(context);
        postsDao = postsDatabase.postsDao();
        postsAPI = PostsService.getInstance(context).create(PostsService.PostsAPI.class);
        fetchPosts();
        allPosts = postsDao.getPosts();
        //testPost = postsDao.getPost();
    }

    private void fetchPosts(){
        Call<String> call = postsAPI.getPosts();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: "+response.code());
                update(Parser.getPosts(response.body()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());
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
}
