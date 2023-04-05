package com.iggydev.newsfeed.data.remote.dto

import com.iggydev.newsfeed.domain.models.Article

data class ArticleDto(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDto?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

fun ArticleDto.toArticle(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        sourceName = source?.name,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}