package com.iggydev.newsfeed.data.remote.dto

import com.iggydev.newsfeed.domain.models.Article
import com.iggydev.newsfeed.domain.models.NewsFeed

data class NewsFeedDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)

fun NewsFeedDto.toDomainArticles(): NewsFeed {
    return NewsFeed(articles = articles.map { articleDto -> articleDto.toArticle() })
}