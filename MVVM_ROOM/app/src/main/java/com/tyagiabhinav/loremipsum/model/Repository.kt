package com.tyagiabhinav.loremipsum.model

import android.content.Context
import com.tyagiabhinav.loremipsum.model.dao.Post
import com.tyagiabhinav.loremipsum.model.service.PostsService

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

    suspend fun getPosts(): List<Post> {
        return PostsService.postsAPi.getPosts()
    }
}