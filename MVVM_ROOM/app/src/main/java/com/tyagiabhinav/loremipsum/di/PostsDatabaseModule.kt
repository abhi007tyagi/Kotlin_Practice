package com.tyagiabhinav.loremipsum.di

import android.content.Context
import androidx.room.Room
import com.tyagiabhinav.loremipsum.model.db.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PostsDatabaseModule {

    @Singleton
    @Provides
    fun providesDB(@ApplicationContext context: Context): PostsDatabase =
            Room.databaseBuilder(context.applicationContext,
                    PostsDatabase::class.java, "posts_database")
                    .fallbackToDestructiveMigration()
                    .build()

    @Singleton
    @Provides
    fun providesDBDao(db: PostsDatabase) = db.postsDao()
}