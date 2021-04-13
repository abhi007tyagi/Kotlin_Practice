package com.tyagiabhinav.loremipsum.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tyagiabhinav.loremipsum.model.Repository
import com.tyagiabhinav.loremipsum.model.dao.Post

class PostsViewModel(val app: Application) : AndroidViewModel(app) {

    fun getPosts(): LiveData<List<Post>> {
        return Repository.getInstance(app.applicationContext).getPosts()
    }

    fun cancelJobs() {
        Repository.getInstance(app.applicationContext).cancelJob()
    }
}