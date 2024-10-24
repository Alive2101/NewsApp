package com.pavel.newsapp.presentation.news.recyclerView


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pavel.newsapp.databinding.ItemNewsBinding
import com.pavel.newsapp.model.News

class NewsAdapter(
    private val onNewsClick: (
        news: News
    ) -> Unit
) :
    ListAdapter<News, NewsHolder>(object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(getItem(position), onNewsClick)
        holder.itemView.setOnClickListener {
            onNewsClick(
                getItem(position)
            )
        }

    }
}