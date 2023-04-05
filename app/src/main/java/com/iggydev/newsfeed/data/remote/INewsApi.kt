package com.iggydev.newsfeed.data.remote


import com.iggydev.newsfeed.data.remote.dto.NewsFeedDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface INewsApi {
    @GET("everything")
    suspend fun getAppleNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsFeedDto>
}