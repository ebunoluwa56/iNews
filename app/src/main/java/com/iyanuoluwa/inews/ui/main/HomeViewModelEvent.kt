package com.iyanuoluwa.inews.ui.main

import com.iyanuoluwa.inews.data.model.Article

sealed interface HomeViewModelEvent

data class DisplayTopHeadlines(val articles : MutableList<Article>) : HomeViewModelEvent

data class DisplayOtherNews(val articles: MutableList<Article>) : HomeViewModelEvent

data class ShowErrorToastMessage(val error: String) : HomeViewModelEvent