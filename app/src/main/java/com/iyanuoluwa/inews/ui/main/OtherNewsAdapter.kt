package com.iyanuoluwa.inews.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.iyanuoluwa.inews.R
import com.iyanuoluwa.inews.data.model.Article
import com.iyanuoluwa.inews.util.loadImageFromGlide

class OtherNewsAdapter(
    val context: Context,
    var newsList: MutableList<Article> = mutableListOf()
) : RecyclerView.Adapter<OtherNewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.all_news_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tittle.text = newsList[position].title
        holder.timePosted.text = newsList[position].publishedAt
        holder.description.text = newsList[position].description
        holder.source.text = newsList[position].source.name
        holder.images.loadImageFromGlide(newsList[position].urlToImage)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var images: ShapeableImageView = itemView.findViewById(R.id.breaking_news_image)
        var tittle: TextView = itemView.findViewById(R.id.news_title)
        var description: TextView = itemView.findViewById(R.id.news_description)
        var source: TextView = itemView.findViewById(R.id.news_source)
        var timePosted: TextView = itemView.findViewById(R.id.news_time)
    }
}