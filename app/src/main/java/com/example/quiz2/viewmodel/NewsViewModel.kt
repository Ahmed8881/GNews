package com.example.quiz2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz2.models.NewsArticle
import com.example.quiz2.repository.NewsRepository
import com.example.quiz2.utils.UiState
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<NewsArticle>>>()
    val uiState: LiveData<UiState<List<NewsArticle>>> = _uiState

    private var allArticles: List<NewsArticle> = emptyList()

    private val _currentCountry = MutableLiveData("us")
    val currentCountry: LiveData<String> = _currentCountry

    init {
        fetchHeadlines()
    }

    fun fetchHeadlines(country: String = _currentCountry.value ?: "us") {
        _currentCountry.value = country
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            repository.getTopHeadlines(country).fold(
                onSuccess = { articles ->
                    allArticles = articles
                    _uiState.value = UiState.Success(articles)
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "An unexpected error occurred.")
                }
            )
        }
    }

    fun filterArticles(query: String) {
        if (query.isBlank()) {
            _uiState.value = UiState.Success(allArticles)
            return
        }
        val filtered = allArticles.filter { article ->
            article.title.contains(query, ignoreCase = true) ||
            article.description?.contains(query, ignoreCase = true) == true ||
            article.source.name.contains(query, ignoreCase = true)
        }
        _uiState.value = UiState.Success(filtered)
    }

    fun getCurrentCountry(): String = _currentCountry.value ?: "us"
}
