package com.iyanuoluwa.inews.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iyanuoluwa.inews.R
import com.iyanuoluwa.inews.data.model.Category

class NewsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_fragmet, container, false)
    }

    companion object {
        fun newInstance(category: Category): NewsFragment {
            val args = Bundle()
            args.putString("category", category.categoryName)
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}