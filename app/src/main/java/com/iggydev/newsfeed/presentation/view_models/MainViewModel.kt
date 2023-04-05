package com.iggydev.newsfeed.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iggydev.newsfeed.domain.models.Article
import com.iggydev.newsfeed.domain.models.NewsFeed
import com.iggydev.newsfeed.domain.use_cases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    // state
    private val _newsState = MutableStateFlow<NewsFeed>(NewsFeed(emptyList()))
    val newsState: StateFlow<NewsFeed>
        get() = _newsState.asStateFlow()

    init {
        getAppleNews()
    }

    private fun getAppleNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.getAppleNews().collectLatest { news ->
                _newsState.value = news
            }
        }
    }
}