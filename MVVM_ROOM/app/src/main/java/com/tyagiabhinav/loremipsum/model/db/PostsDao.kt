package com.tyagiabhinav.loremipsum.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tyagiabhinav.loremipsum.model.dao.Post

@Dao
interface PostsDao {
    @Insert
    suspend fun insert(posts: List<Post>)

    @Query("DELETE from posts_table")
    suspend fun deleteAll()

    @Query("SELECT * from posts_table")
    fun getPosts(): List<Post>
}