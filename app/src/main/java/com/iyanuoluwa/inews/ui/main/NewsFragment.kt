package com.iyanuoluwa.inews.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iyanuoluwa.inews.R
import com.iyanuoluwa.inews.data.model.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news_fragmet) {

    private lateinit var recyclerView: RecyclerView
    private val viewModel: OtherFragmentViewModel by viewModels()
    private var category: Category = Category.SPORTS
    private lateinit var adapter: OtherSegmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

        category = Category.fromCategoryName(requireArguments().getString("category").orEmpty())

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewModelEvents.collect {
                    handleEvent(it)
                }
            }
        }

        viewModel.getOtherSegments(category)
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.other_segment_recycler_view)
        adapter = OtherSegmentAdapter(requireContext(), mutableListOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

    private fun handleEvent(event: OtherSegmentViewModelEvent) {
        when (event) {
            is DisplaySegmentNews -> {
                adapter.newsItems = event.articles
                recyclerView.adapter?.notifyDataSetChanged()
            }
            is ShowErrorMessage -> {
                Toast.makeText(requireActivity(), event.error, Toast.LENGTH_LONG).show()
            }
        }
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