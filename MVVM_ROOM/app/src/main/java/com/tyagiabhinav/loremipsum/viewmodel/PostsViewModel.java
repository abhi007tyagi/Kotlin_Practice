package com.tyagiabhinav.loremipsum.viewmodel;

import android.app.Application;

import com.tyagiabhinav.loremipsum.model.PostsRepository;
import com.tyagiabhinav.loremipsum.model.db.Posts;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PostsViewModel extends AndroidViewModel {

    private PostsRepository postsRepository;
    private LiveData<List<Posts>> allPosts;
    public PostsViewModel(@NonNull Application application) {
        super(application);
        postsRepository = new PostsRepository(application.getApplicationContext());
        allPosts = postsRepository.getPosts();
    }

    public LiveData<List<Posts>> getAllPosts() {
        return allPosts;
    }

    public void setVisibilityState(int pos, boolean isVisible){
        allPosts.getValue().get(pos).setDescVisible(isVisible);
    }

    public boolean isVisible(int pos){
        return allPosts.getValue().get(pos).isDescVisible();
    }

    /*
    insert, update, delete also goes here if data is being changed by the user
     */

}
