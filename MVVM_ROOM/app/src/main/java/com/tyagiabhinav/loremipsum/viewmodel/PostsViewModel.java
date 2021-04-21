package com.tyagiabhinav.loremipsum.viewmodel;

import android.app.Application;

import com.tyagiabhinav.loremipsum.model.PostsRepository;
import com.tyagiabhinav.loremipsum.model.db.Posts;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PostsViewModel extends AndroidViewModel {

    PostsRepository postsRepository;
    private final LiveData<List<Posts>> allPosts;

    @Inject
    public PostsViewModel(@NonNull Application application, PostsRepository repo) {
        super(application);
        this.postsRepository = repo;
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
