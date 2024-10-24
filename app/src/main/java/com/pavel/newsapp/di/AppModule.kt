package com.pavel.newsapp.di

import android.content.Context
import androidx.room.Room
import com.pavel.newsapp.db.AppDataBase
import com.pavel.newsapp.db.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java,"dataBase").build()
    }

    @Singleton
    @Provides
    fun provideCityDao(appDataBase: AppDataBase): NewsDao {
        return appDataBase.getNewsDao()
    }
}