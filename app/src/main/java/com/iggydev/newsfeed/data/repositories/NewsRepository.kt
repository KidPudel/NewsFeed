package com.iggydev.newsfeed.data.repositories

import com.iggydev.newsfeed.data.remote.INewsApi
import com.iggydev.newsfeed.data.remote.dto.toDomainArticles
import com.iggydev.newsfeed.domain.irepositories.INewsRepository
import com.iggydev.newsfeed.domain.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: INewsApi) : INewsRepository {
    override suspend fun getAppleNews(): List<Article>? {
        val response = newsApi.getAppleNews()
        if (!response.isSuccessful) {
            return emptyList()
        }
        val body = response.body()
        return body?.toDomainArticles()
    }
}