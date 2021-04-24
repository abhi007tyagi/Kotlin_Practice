package com.tyagiabhinav.loremipsum.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.tyagiabhinav.loremipsum.model.Repository
import com.tyagiabhinav.loremipsum.model.dao.Post
import com.tyagiabhinav.loremipsum.model.dao.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel
@Inject constructor(
        private val app: Application,
        private val repo: Repository
) : AndroidViewModel(app) {

    val dataLoading = ObservableField(false)

    val result: LiveData<ResultState<List<Post>>> = liveData(IO, 5000) {
        dataLoading.set(true)
        try {
            val res = repo.getPosts()
            if (res.isNotEmpty()) {
                emit(ResultState.Success(res))
            } else {
                emit(ResultState.Error(Exception()))
            }
        } catch (ioException: Exception) {
            emit(ResultState.Error(Exception()))
        } finally {
            dataLoading.set(false)
        }
    }
}