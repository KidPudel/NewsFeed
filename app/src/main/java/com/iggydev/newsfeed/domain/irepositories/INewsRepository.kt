package com.iggydev.newsfeed.domain.irepositories

import com.iggydev.newsfeed.domain.models.Article

interface INewsRepository {
    suspend fun getAppleNews(): List<Article>?
}