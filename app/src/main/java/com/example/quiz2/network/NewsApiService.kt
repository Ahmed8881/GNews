package com.example.quiz2.network

import com.example.quiz2.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String,
        @Query("lang") lang: String,
        @Query("country") country: String,
        @Query("max") max: Int,
        @Query("apikey") apiKey: String
    ): NewsResponse

    @GET("search")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("lang") lang: String,
        @Query("max") max: Int,
        @Query("apikey") apiKey: String
    ): NewsResponse
}
