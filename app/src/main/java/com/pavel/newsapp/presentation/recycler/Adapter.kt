package com.pavel.newsapp.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pavel.newsapp.databinding.ItemNewsBinding
import com.pavel.newsapp.model.News

class Adapter(
    private val onNewsClick: (
        news: News
    ) -> Unit
) :
    ListAdapter<News, Holder>(object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), onNewsClick)
        holder.itemView.setOnClickListener {
            onNewsClick(
                getItem(position)
            )
        }

    }
}