package com.pavel.newsapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert
    suspend fun addItem(item: NewsEntity)

    @Query("SELECT * FROM NewsEntity")
    suspend fun getAllItem(): List<NewsEntity>

    @Query("SELECT * FROM NewsEntity WHERE title = :title")
    suspend fun findNews(title: String): List<NewsEntity>

    @Query("DELETE FROM NewsEntity WHERE title = :title")
    suspend fun deleteNews(title:String)

}