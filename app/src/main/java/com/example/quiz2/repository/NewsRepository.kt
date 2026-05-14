package com.example.quiz2.repository

import com.example.quiz2.BuildConfig
import com.example.quiz2.models.NewsArticle
import com.example.quiz2.network.ApiClient
import com.example.quiz2.network.NewsApiService
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NewsRepository(
    private val apiService: NewsApiService = ApiClient.newsApiService
) {

    suspend fun getTopHeadlines(country: String): Result<List<NewsArticle>> {
        return try {
            val response = apiService.getTopHeadlines(
                category = "general",
                lang = "en",
                country = country,
                max = 10,
                apiKey = BuildConfig.NEWS_API_KEY
            )
            Result.success(response.articles)
        } catch (e: UnknownHostException) {
            Result.failure(Exception("No internet connection. Please check your network."))
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("Connection timed out. Please try again."))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "An unexpected error occurred."))
        }
    }
}
