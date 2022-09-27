package com.iyanuoluwa.inews.ui.main

import com.iyanuoluwa.inews.data.model.Article

sealed interface ViewModelEvent

data class DisplayTopHeadlines(val articles : MutableList<Article>) : ViewModelEvent

data class DisplayOtherNews(val articles: MutableList<Article>) : ViewModelEvent

data class ShowErrorToastMessage(val error: String) : ViewModelEvent