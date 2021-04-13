package com.tyagiabhinav.loremipsum.model.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PostsDao {

    @Insert
    void insert(List<Posts> posts);

    @Update
    void update(List<Posts> posts);

    @Query("DELETE from posts_table")
    void deleteAll();

    @Query("SELECT * from posts_table")
    LiveData<List<Posts>> getPosts();

    @Query("SELECT * from posts_table LIMIT 1")
    Posts getPost();
}
