package com.pavel.newsapp.repository

import com.pavel.newsapp.db.NewsDao
import com.pavel.newsapp.db.NewsEntity
import com.pavel.newsapp.model.News
import com.pavel.newsapp.network.Api
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api, private val newsDao: NewsDao) {
    suspend fun getNews(name: String) = api.getNewsAboutCars(name)

    suspend fun getNewsList(): ArrayList<News> {
        return (newsDao.getAllItem().map {
            News(it.title, it.content, it.source, it.url, it.author, it.publishedAt)
        } as? ArrayList<News>) ?: arrayListOf()
    }

    suspend fun addNews(
        news: News
    ) =
        newsDao.addItem(
            NewsEntity(
                0,
                news.title,
                news.content,
                news.source,
                news.url,
                news.author,
                news.publishedAt
            )
        )

    suspend fun findNews(title: String) = newsDao.findNews(title)

    suspend fun deleteNews(title: String) = newsDao.deleteNews(title)
}