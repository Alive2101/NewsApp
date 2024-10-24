package com.pavel.newsapp.presentation.bookmarks.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pavel.newsapp.databinding.ItemSafeNewsBinding
import com.pavel.newsapp.model.News

class BookmarksAdapter(
    private val onNewsClick: (
        news: News
    ) -> Unit
) :
    ListAdapter<News, BookmarksHolder>(object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksHolder {
        return BookmarksHolder(
            ItemSafeNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarksHolder, position: Int) {
        holder.bind(getItem(position), onNewsClick)
        holder.itemView.setOnClickListener {
            onNewsClick(
                getItem(position)
            )
        }

    }
}