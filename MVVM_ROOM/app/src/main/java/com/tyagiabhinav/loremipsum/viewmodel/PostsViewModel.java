package com.tyagiabhinav.loremipsum.viewmodel;

import android.app.Application;

import com.tyagiabhinav.loremipsum.model.PostsRepository;
import com.tyagiabhinav.loremipsum.model.db.Posts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PostsViewModel extends AndroidViewModel {

    PostsRepository postsRepository;
    public final ObservableField<Boolean> isLoading = new ObservableField<>(false);
    private final MutableLiveData<List<Posts>> allPosts;

    @Inject
    public PostsViewModel(@NonNull Application application, PostsRepository repo) {
        super(application);
        this.allPosts = new MutableLiveData<>();
        this.postsRepository = repo;
    }

    public LiveData<List<Posts>> getAllPosts() {
        isLoading.set(true);
        postsRepository.getAllPosts(new PostsRepository.RepoResult() {
            @Override
            public void onSuccess(List<Posts> postsList) {
                isLoading.set(false);
                allPosts.postValue(postsList);
            }

            @Override
            public void onError(String message, Exception exception) {
                isLoading.set(false);
                allPosts.postValue(new ArrayList<>());
            }
        });
        return allPosts;
    }


    public void setVisibilityState(int pos, boolean isVisible){
        allPosts.getValue().get(pos).setDescVisible(isVisible);
    }
//
//    public boolean isVisible(int pos){
//        return allPosts.getValue().get(pos).isDescVisible();
//    }

    /*
    insert, update, delete also goes here if data is being changed by the user
     */

}
