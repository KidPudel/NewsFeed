package com.iggydev.newsfeed.data.remote.dto

import com.iggydev.newsfeed.domain.models.Article

data class NewsFeedDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)

fun NewsFeedDto.toDomainArticles(): List<Article> {
    return articles.map { articleDto -> articleDto.toArticle() }
}