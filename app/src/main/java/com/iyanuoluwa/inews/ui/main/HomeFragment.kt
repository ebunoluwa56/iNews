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
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private val category: Category = Category.TOP_HEADLINES

    private lateinit var headlineAdapter: HeadlineAdapter
    private var headlineRecyclerView: RecyclerView? = null
    private var otherNewsRecyclerView: RecyclerView? = null
    private lateinit var otherNewsAdapter: OtherNewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewModelEvents.collect {
                    handleEvent(it)
                }
            }
        }

        viewModel.getTopHeadlines(category)
        viewModel.getOtherNews()

    }

    private fun initViews(view: View) {
        headlineRecyclerView = view.findViewById(R.id.headline_view)
        otherNewsRecyclerView = view.findViewById(R.id.all_news_view)
        headlineAdapter = HeadlineAdapter(requireContext(), mutableListOf())
        otherNewsAdapter = OtherNewsAdapter(requireContext(), mutableListOf())
        headlineRecyclerView?.adapter = headlineAdapter
        otherNewsRecyclerView?.adapter = otherNewsAdapter
        headlineRecyclerView?.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        otherNewsRecyclerView?.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

    }

    private fun handleEvent(event: ViewModelEvent) {
        when (event) {
            is ShowErrorToastMessage -> {
                Toast.makeText(requireActivity(), event.error, Toast.LENGTH_LONG).show()
            }
            is DisplayTopHeadlines -> {
                headlineAdapter.headLines = event.articles
                headlineRecyclerView?.adapter?.notifyDataSetChanged()
            }
            is DisplayOtherNews -> {
                otherNewsAdapter.newsList = event.articles
                otherNewsRecyclerView?.adapter?.notifyDataSetChanged()
            }
        }
    }


}