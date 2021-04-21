package com.tyagiabhinav.loremipsum.di;

import android.content.Context;

import com.tyagiabhinav.loremipsum.model.db.PostsDao;
import com.tyagiabhinav.loremipsum.model.db.PostsDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class PostsDatabaseModule {

    @Singleton
    @Provides
    PostsDatabase providesDB(@ApplicationContext Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                PostsDatabase.class, "posts_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    PostsDao providesDBDao(PostsDatabase db) {
        return db.postsDao();
    }
}
