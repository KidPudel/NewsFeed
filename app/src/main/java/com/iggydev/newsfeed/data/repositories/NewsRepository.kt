package com.iggydev.newsfeed.data.repositories

import com.iggydev.newsfeed.BuildConfig
import com.iggydev.newsfeed.data.remote.INewsApi
import com.iggydev.newsfeed.data.remote.dto.toDomainArticles
import com.iggydev.newsfeed.domain.irepositories.INewsRepository
import com.iggydev.newsfeed.domain.models.Article
import com.iggydev.newsfeed.domain.models.NewsFeed
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: INewsApi) : INewsRepository {
    override suspend fun getAppleNews(): NewsFeed {
        val response = newsApi.getAppleNews(q = "apple", apiKey = BuildConfig.API_KEY)
        if (!response.isSuccessful) {
            return NewsFeed(emptyList())
        }
        val body = response.body()
        return body?.toDomainArticles() ?: NewsFeed(emptyList())
    }
}