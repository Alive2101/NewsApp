package com.pavel.newsapp.network


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "15a78314f4cd4e178dacaa98a1f1cf2a"

interface Api {
    @GET("v2/everything?language=ru&apiKey=$API_KEY")
    suspend fun getNewsAboutCars(@Query("q") name: String): Response<NewsResponse>
}