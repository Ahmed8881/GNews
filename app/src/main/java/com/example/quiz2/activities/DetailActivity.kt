package com.example.quiz2.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.quiz2.R
import com.example.quiz2.databinding.ActivityDetailBinding
import com.example.quiz2.models.NewsArticle
import com.example.quiz2.utils.DateUtils

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        @Suppress("DEPRECATION")
        val article = intent.getSerializableExtra(EXTRA_ARTICLE) as? NewsArticle
        if (article == null) {
            finish()
            return
        }
        displayArticle(article)
    }

    private fun displayArticle(article: NewsArticle) {
        binding.apply {
            collapsingToolbar.title = article.source.name

            Glide.with(this@DetailActivity)
                .load(article.image)
                .placeholder(R.drawable.ic_news_placeholder)
                .error(R.drawable.ic_news_placeholder)
                .centerCrop()
                .into(ivHeaderImage)

            tvTitle.text = article.title
            tvSource.text = article.source.name
            tvDate.text = DateUtils.formatDate(article.publishedAt)
            tvDescription.text = article.description ?: "No description available."
            tvContent.text = DateUtils.cleanContent(article.content)

            btnReadFullArticle.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
