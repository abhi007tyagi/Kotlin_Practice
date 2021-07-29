package com.tyagiabhinav.loremipsum.model;

import android.util.Log;

import com.tyagiabhinav.loremipsum.model.db.Posts;
import com.tyagiabhinav.loremipsum.model.db.PostsDao;
import com.tyagiabhinav.loremipsum.model.service.Parser;
import com.tyagiabhinav.loremipsum.model.service.PostsAPI;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository {

    public interface RepoResult {
        void onSuccess(List<Posts> postsList);
        void onError(String message, Exception exception);
    }

    private static final String TAG = PostsRepository.class.getName();

    private final PostsDao postsDao;
    private final PostsAPI postsAPI;
    private Posts testPost;

    @Inject
    public PostsRepository (PostsDao postsDao, PostsAPI postsAPI){
        this.postsDao = postsDao;
        this.postsAPI = postsAPI;
        //testPost = postsDao.getPost();
    }

    private void fetchPosts(RepoResult repoResult){
        Call<String> call = postsAPI.getPosts();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: "+response.code());
                List<Posts> allPosts = Parser.getPosts(response.body());
                repoResult.onSuccess(allPosts);
                update(allPosts);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());
                repoResult.onError("No posts available", new Exception("Database Empty!"));
//                update(Arrays.asList(new Posts("Error Occurred","Try again later!!!")));
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

    public void getAllPosts(RepoResult repoResult){
        new Thread(() -> {
            // do background stuff here
            List<Posts> allPosts = postsDao.getPosts();
            if(allPosts.isEmpty()){
                fetchPosts(repoResult);
            } else {
                repoResult.onSuccess(allPosts);
            }
        }).start();
    }

    public Posts getTestPost(){
        return testPost;
    }
}
