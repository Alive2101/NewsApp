package com.pavel.newsapp.presentation.viewNews

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavel.newsapp.model.News
import com.pavel.newsapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ViewNewsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var job: Job? = null
    fun saveNews(
        news: News
    ) {
        job?.cancelChildren()
        job = viewModelScope.launch(Dispatchers.IO) {
            if (repository.findNews(news.title).isEmpty()) {
                repository.addNews(news)
            } else {
                repository.deleteNews(news.title)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseData(publishedAt: String?): String {
        val zonedDateTime = ZonedDateTime.parse(publishedAt)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formattedDate = zonedDateTime.format(formatter)
        return formattedDate
    }
}