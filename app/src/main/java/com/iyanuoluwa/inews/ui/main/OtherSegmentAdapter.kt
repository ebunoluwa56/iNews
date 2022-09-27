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

class OtherSegmentAdapter(
    val context : Context,
    var newsItems: MutableList<Article> = mutableListOf()
) : RecyclerView.Adapter<OtherSegmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.other_segment_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = newsItems[position].title
        holder.timePosted.text = newsItems[position].publishedAt
        holder.image.loadImageFromGlide(newsItems[position].urlToImage)
    }

    override fun getItemCount() = newsItems.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ShapeableImageView = itemView.findViewById(R.id.segment_image_header)
        var title: TextView = itemView.findViewById(R.id.segment_headline_title)
        var timePosted: TextView = itemView.findViewById(R.id.time_posted_headline_segment)

    }
}