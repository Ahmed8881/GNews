package com.example.quiz2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quiz2.R
import com.example.quiz2.databinding.ItemNewsCardBinding
import com.example.quiz2.models.NewsArticle
import com.example.quiz2.utils.DateUtils

class NewsAdapter(
    private val onItemClick: (NewsArticle) -> Unit
) : ListAdapter<NewsArticle, NewsAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(
        private val binding: ItemNewsCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: NewsArticle) {
            binding.apply {
                tvTitle.text = article.title
                tvSource.text = article.source.name
                tvDate.text = DateUtils.formatDate(article.publishedAt)

                Glide.with(itemView.context)
                    .load(article.image)
                    .placeholder(R.drawable.ic_news_placeholder)
                    .error(R.drawable.ic_news_placeholder)
                    .centerCrop()
                    .into(ivArticleImage)

                root.setOnClickListener { onItemClick(article) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsArticle>() {
            override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle) =
                oldItem == newItem
        }
    }
}
