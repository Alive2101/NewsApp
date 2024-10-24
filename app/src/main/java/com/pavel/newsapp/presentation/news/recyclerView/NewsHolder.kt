package com.pavel.newsapp.presentation.news.recyclerView

import androidx.recyclerview.widget.RecyclerView
import com.pavel.newsapp.databinding.ItemNewsBinding
import com.pavel.newsapp.model.News

class NewsHolder(private val binding: ItemNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        news: News,
        onHeadClick: (
            news: News
        ) -> Unit
    ) = binding.run {
        head.text = news.title
        description.text = news.content
        source.text = "Источник: ${news.source}"
        head.setOnClickListener {
            onHeadClick(
                news
            )
        }

    }
}