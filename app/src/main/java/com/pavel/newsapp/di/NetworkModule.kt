package com.pavel.newsapp.di

import com.pavel.newsapp.network.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provide(): Api {
        return Retrofit.Builder().baseUrl("https://newsapi.org/")
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            }).build())
            .addConverterFactory(GsonConverterFactory.create()).build().create(Api::class.java)
    }
}