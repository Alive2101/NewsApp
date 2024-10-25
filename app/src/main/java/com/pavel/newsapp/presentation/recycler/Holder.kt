package com.pavel.newsapp.presentation.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.pavel.newsapp.databinding.ItemNewsBinding
import com.pavel.newsapp.databinding.ItemSafeNewsBinding
import com.pavel.newsapp.model.News

private const val resource: String = "Источник"

class Holder(private val binding: ItemNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(
        news: News,
        onHeadClick: (
            news: News
        ) -> Unit
    ) = binding.run {
        head.text = news.title
        description.text = news.content
        source.text = "$resource: ${news.source}"
        head.setOnClickListener {
            onHeadClick(
                news
            )
        }
    }
}