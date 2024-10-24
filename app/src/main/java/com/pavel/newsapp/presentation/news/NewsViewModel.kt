package com.pavel.newsapp.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavel.newsapp.controller.NetworkController
import com.pavel.newsapp.model.News
import com.pavel.newsapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: Repository,
    networkController: NetworkController
) : ViewModel() {

    val isNetworkConnected = MutableLiveData<Boolean>()
    val categoryLiveData = MutableLiveData<String>()
    val categoryListLiveData = MutableLiveData<List<String>>()

    val newsCategories = listOf(
        "Политика", "Экономика", "Технологии", "Наука", "Здоровье"
    )

    val newsData = MutableLiveData<List<News>>()

    init {
        viewModelScope.launch {
            networkController.isNetworkConnected.collectLatest {
                isNetworkConnected.value = it
            }
        }
        categoryListLiveData.postValue(newsCategories)

    }

    fun getNews(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getNews(name)
            if (response.isSuccessful) {
                response.body()?.articles?.map {
                    News(
                        title = it.title ?: "",
                        content = it.description ?: "",
                        source = it.source.name ?: "",
                        url = it.url ?: "",
                        author = it.author ?: "",
                        publishedAt = it.publishedAt ?: ""
                    )
                }?.run {
                    newsData.postValue(this)
                }
            }
        }
    }

    fun setCategory(category: String) {
        if (isNetworkConnected.value == true) {
            categoryLiveData.postValue(category)
        }
    }
}