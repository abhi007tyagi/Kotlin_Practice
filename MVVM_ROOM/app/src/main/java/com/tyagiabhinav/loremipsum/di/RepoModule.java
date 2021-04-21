package com.tyagiabhinav.loremipsum.di;

import com.tyagiabhinav.loremipsum.model.PostsRepository;
import com.tyagiabhinav.loremipsum.model.db.PostsDao;
import com.tyagiabhinav.loremipsum.model.service.PostsAPI;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ViewModelComponent.class)
public class RepoModule {

    @ViewModelScoped
    @Provides
    PostsRepository providesRepository(PostsDao dao, PostsAPI api) {
        return new PostsRepository(dao, api);
    }
}
