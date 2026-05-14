package com.example.quiz2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz2.activities.DetailActivity
import com.example.quiz2.adapters.NewsAdapter
import com.example.quiz2.databinding.ActivityMainBinding
import com.example.quiz2.models.NewsArticle
import com.example.quiz2.utils.UiState
import com.example.quiz2.viewmodel.NewsViewModel
import com.example.quiz2.viewmodel.NewsViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    private val countries = listOf(
        Triple("🇵🇰 Pakistan", "pk", "🇵🇰 PK"),
        Triple("🇺🇸 United States", "us", "🇺🇸 US"),
        Triple("🇬🇧 United Kingdom", "gb", "🇬🇧 GB"),
        Triple("🇮🇳 India", "in", "🇮🇳 IN"),
        Triple("🇸🇦 Saudi Arabia", "sa", "🇸🇦 SA"),
        Triple("🇦🇪 UAE", "ae", "🇦🇪 AE")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, NewsViewModelFactory())
            .get(NewsViewModel::class.java)

        setupToolbar()
        setupRecyclerView()
        setupSearch()
        setupCountryButton()
        setupSwipeRefresh()
        setupRetryButton()
        observeViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name_display)
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter { article -> openDetailScreen(article) }
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun setupSearch() {
        binding.searchView.apply {
            setIconified(false)
            queryHint = getString(R.string.search_hint)

            // Style search text for dark background
            findViewById<EditText>(androidx.appcompat.R.id.search_src_text)?.apply {
                setTextColor(Color.WHITE)
                setHintTextColor(Color.parseColor("#99FFFFFF"))
            }
            findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
                ?.setColorFilter(Color.WHITE)
            findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
                ?.setColorFilter(Color.WHITE)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = true
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.filterArticles(newText.orEmpty())
                    return true
                }
            })
        }
    }

    private fun setupCountryButton() {
        binding.btnCountry.setOnClickListener { showCountryDialog() }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchHeadlines()
        }
    }

    private fun setupRetryButton() {
        binding.btnRetry.setOnClickListener {
            viewModel.fetchHeadlines()
        }
    }

    private fun showCountryDialog() {
        val names = countries.map { it.first }.toTypedArray()
        val currentIndex = countries.indexOfFirst { it.second == viewModel.getCurrentCountry() }

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.select_country))
            .setSingleChoiceItems(names, currentIndex) { dialog, which ->
                val (_, code, label) = countries[which]
                binding.btnCountry.text = label
                viewModel.fetchHeadlines(code)
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this) { state ->
            binding.swipeRefreshLayout.isRefreshing = false
            when (state) {
                is UiState.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.recyclerView.isVisible = false
                    binding.layoutError.isVisible = false
                }
                is UiState.Success -> {
                    binding.progressBar.isVisible = false
                    binding.layoutError.isVisible = false
                    binding.recyclerView.isVisible = true
                    newsAdapter.submitList(state.data)
                    if (state.data.isEmpty()) {
                        showSnackbar(getString(R.string.no_results))
                    }
                }
                is UiState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.recyclerView.isVisible = false
                    binding.layoutError.isVisible = true
                    binding.tvError.text = state.message
                    showSnackbar(state.message)
                }
            }
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.retry)) { viewModel.fetchHeadlines() }
            .show()
    }

    private fun openDetailScreen(article: NewsArticle) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ARTICLE, article)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.fetchHeadlines()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
