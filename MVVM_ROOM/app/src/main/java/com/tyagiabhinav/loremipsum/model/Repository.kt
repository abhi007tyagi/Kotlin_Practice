package com.tyagiabhinav.loremipsum.model

import android.content.Context
import androidx.lifecycle.LiveData
import com.tyagiabhinav.loremipsum.model.dao.Post
import com.tyagiabhinav.loremipsum.model.service.PostsService
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class Repository {
    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(context: Context): Repository {
            return INSTANCE ?: synchronized(this) {
                val instance = Repository()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private var job: CompletableJob? = null
    fun getPosts(): LiveData<List<Post>> {
        job = Job()
        return object : LiveData<List<Post>>() {
            override fun onActive() {
                super.onActive()
                job?.let { fetchPostsJob ->
                    CoroutineScope(IO + fetchPostsJob).launch {
                        val listOfPosts = PostsService.postsAPi.getPosts()
                        withContext(Main) {
                            value = listOfPosts
                            fetchPostsJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJob() {
        job?.cancel()
    }
}