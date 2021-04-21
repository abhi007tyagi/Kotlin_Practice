package com.tyagiabhinav.loremipsum.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Posts.class}, version = 1)
public abstract class PostsDatabase extends RoomDatabase {
    public abstract PostsDao postsDao();
}
