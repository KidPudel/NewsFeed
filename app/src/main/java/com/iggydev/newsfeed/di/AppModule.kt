package com.iggydev.newsfeed.di

import com.iggydev.newsfeed.common.Constants.BASE_URL
import com.iggydev.newsfeed.data.remote.INewsApi
import com.iggydev.newsfeed.data.repositories.NewsRepository
import com.iggydev.newsfeed.domain.irepositories.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun getNewsRepository(newsRepository: NewsRepository): INewsRepository

    companion object {
        @Provides
        @Singleton
        fun getNewsApi(): INewsApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(INewsApi::class.java)
        }
    }
}