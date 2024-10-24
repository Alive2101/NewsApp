package com.pavel.newsapp.presentation.bookmarks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavel.newsapp.model.News
import com.pavel.newsapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val newsData = MutableLiveData<List<News>>()

    private var job: Job? = null

    fun getNewsList() {
        job?.cancelChildren()
        job = viewModelScope.launch(Dispatchers.IO) {
            newsData.postValue(repository.getNewsList())
        }
    }
}