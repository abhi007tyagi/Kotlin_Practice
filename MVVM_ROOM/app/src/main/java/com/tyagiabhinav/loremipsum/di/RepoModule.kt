package com.tyagiabhinav.loremipsum.di

import com.tyagiabhinav.loremipsum.model.Repository
import com.tyagiabhinav.loremipsum.model.db.PostsDao
import com.tyagiabhinav.loremipsum.model.service.PostsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepoModule {

    @ViewModelScoped
    @Provides
    fun providesRepo(dbDao: PostsDao, postsAPi: PostsApi): Repository = Repository(dbDao, postsAPi)
}