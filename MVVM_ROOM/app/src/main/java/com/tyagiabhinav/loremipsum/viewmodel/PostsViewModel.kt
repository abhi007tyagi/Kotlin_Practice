package com.tyagiabhinav.loremipsum.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.tyagiabhinav.loremipsum.model.Repository
import com.tyagiabhinav.loremipsum.model.dao.Post
import kotlinx.coroutines.Dispatchers.IO

class PostsViewModel(private val app: Application) : AndroidViewModel(app) {

    val dataLoading = ObservableField(false)

    val result: LiveData<List<Post>> = liveData(IO, 5000) {
        dataLoading.set(true)
        try {
            val res = Repository.getInstance(app.applicationContext).getPosts()
            if (res.isNotEmpty()) {
                emit(res)
            } else {
                emit(emptyList<Post>())
            }
        } catch (ioException: Exception) {
            emit(emptyList<Post>())
        } finally {
            dataLoading.set(false)
        }
    }
}