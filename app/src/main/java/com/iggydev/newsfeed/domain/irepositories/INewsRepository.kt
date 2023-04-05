package com.iggydev.newsfeed.domain.irepositories

import com.iggydev.newsfeed.domain.models.NewsFeed

interface INewsRepository {
    suspend fun getAppleNews(): NewsFeed
}