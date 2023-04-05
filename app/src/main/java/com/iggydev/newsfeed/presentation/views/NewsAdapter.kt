package com.iggydev.newsfeed.presentation.views

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iggydev.newsfeed.databinding.ListItemArticleCardBinding
import com.iggydev.newsfeed.domain.models.Article
import com.iggydev.newsfeed.domain.models.NewsFeed
import okhttp3.internal.assertThreadHoldsLock
import timber.log.Timber
import java.net.URI
import java.net.URL

class NewsAdapter(
    val onClickListener: ((Article) -> Unit)
) : ListAdapter<Article, NewsHolder>(NewsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemArticleCardBinding.inflate(
            /* inflater = */ layoutInflater,
            /* parent = */ parent,
            /* attachToParent = */ false
        )
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            cardTitle.text = item.title
            setImageFromUrl(root.context, Uri.parse(item.urlToImage), cardImage)
            root.setOnClickListener {
                onClickListener.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    private fun setImageFromUrl(context: Context, uri: Uri?, imageView: ImageView) {
        try {
            Glide.with(context)
                .load(uri)
                .into(imageView)
        } catch (e: Exception) {
            Timber.e("Could not set an image (from NewsAdapter) ;((")
        }
    }
}