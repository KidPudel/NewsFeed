package com.iggydev.newsfeed.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.iggydev.newsfeed.databinding.ActivityMainBinding
import com.iggydev.newsfeed.domain.models.Article
import com.iggydev.newsfeed.presentation.view_models.MainViewModel
import com.iggydev.newsfeed.presentation.views.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val newsViewModel: MainViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        setNewsAdapter()

        addObservables()
    }

    private fun addObservables() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // update ui when our data is fetched successfully
                newsViewModel.newsState.collectLatest {
                    newsAdapter.submitList(it.articles)
                }

            }

        }
    }

    private fun setNewsAdapter() {
        val onArticleClickListener: ((Article) -> Unit) = { article: Article ->
            Snackbar.make(
                this,
                binding.root,
                article.author.toString(),
                Snackbar.LENGTH_SHORT
            ).show()
        }

        newsAdapter = NewsAdapter(onArticleClickListener)
        binding.newsListView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            visibility = View.VISIBLE
        }
    }
}