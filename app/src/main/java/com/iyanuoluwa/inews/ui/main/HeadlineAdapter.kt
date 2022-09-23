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

class HeadlineAdapter(
    val context : Context,
    var headLines: MutableList<Article> = mutableListOf(),
) : RecyclerView.Adapter<HeadlineAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.headline_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tittle.text = headLines[position].title
        holder.timePosted.text = headLines[position].publishedAt
        holder.images.loadImageFromGlide(headLines[position].urlToImage)
    }

    override fun getItemCount(): Int {
        return headLines.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var images: ShapeableImageView = itemView.findViewById(R.id.headline_image)
        var tittle: TextView = itemView.findViewById(R.id.title_headline)
        var timePosted: TextView = itemView.findViewById(R.id.time_posted_headline)
    }
}