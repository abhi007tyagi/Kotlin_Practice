package com.tyagiabhinav.loremipsum.model

import android.content.Context
import com.tyagiabhinav.loremipsum.model.dao.Post
import com.tyagiabhinav.loremipsum.model.db.PostsDao
import com.tyagiabhinav.loremipsum.model.db.PostsDatabase
import com.tyagiabhinav.loremipsum.model.service.PostsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Repository {
    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        @Volatile
        private lateinit var dbDao: PostsDao

        fun getInstance(context: Context): Repository {
            return INSTANCE ?: synchronized(this) {
                val instance = Repository()
                INSTANCE = instance

                val db = PostsDatabase.getDatabase(context)
                dbDao = db.postsDao()

//                CoroutineScope(IO).launch {
//                    dbDao.deleteAll()
//                }
                // return instance
                instance
            }
        }
    }

    suspend fun getPosts(): List<Post> {
        val cachedPostList = dbDao?.getPosts()
        if (cachedPostList.isNotEmpty())
            return cachedPostList

        val freshPostList = PostsService.postsAPi.getPosts()
        dbDao.insert(freshPostList)
        return freshPostList
    }
}