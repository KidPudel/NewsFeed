package com.iggydev.newsfeed.domain.use_cases

import com.iggydev.newsfeed.data.repositories.NewsRepository
import com.iggydev.newsfeed.domain.irepositories.INewsRepository
import com.iggydev.newsfeed.domain.models.Article
import com.iggydev.newsfeed.domain.models.NewsFeed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: INewsRepository) {
    fun getAppleNews(): Flow<NewsFeed> = flow {
        val news = newsRepository.getAppleNews()
        emit(news)
    }
}