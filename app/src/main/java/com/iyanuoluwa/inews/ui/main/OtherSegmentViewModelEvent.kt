package com.iyanuoluwa.inews.ui.main

import com.iyanuoluwa.inews.data.model.Article

sealed interface OtherSegmentViewModelEvent

data class DisplaySegmentNews(val articles: MutableList<Article>) : OtherSegmentViewModelEvent

data class ShowErrorMessage(val error: String) : OtherSegmentViewModelEvent