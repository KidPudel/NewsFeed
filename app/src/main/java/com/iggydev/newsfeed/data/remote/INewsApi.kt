package com.iggydev.newsfeed.data.remote

import com.iggydev.newsfeed.BuildConfig
import com.iggydev.newsfeed.data.remote.dto.NewsFeedDto
import retrofit2.Response
import retrofit2.http.GET

interface INewsApi {
    @GET("everything?q=apple@apiKey=${BuildConfig.API_KEY}")
    suspend fun getAppleNews(): Response<NewsFeedDto>
}