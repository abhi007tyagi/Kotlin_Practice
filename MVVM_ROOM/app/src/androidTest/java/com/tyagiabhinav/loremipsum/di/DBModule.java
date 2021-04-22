package com.tyagiabhinav.loremipsum.di;

import android.content.Context;

import com.tyagiabhinav.loremipsum.model.db.PostsDatabase;

import javax.inject.Named;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DBModule {

    @Provides
    @Named("test_db")
    PostsDatabase providesDB(@ApplicationContext Context context) {
        return Room.inMemoryDatabaseBuilder(context, PostsDatabase.class)
                .build();
    }

}
