package com.iyanuoluwa.inews.ui.main

import androidx.lifecycle.viewModelScope
import com.iyanuoluwa.inews.data.model.Article
import com.iyanuoluwa.inews.data.model.Category
import com.iyanuoluwa.inews.domain.usecase.UseCases
import com.iyanuoluwa.inews.util.Resource
import com.iyanuoluwa.inews.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val useCases: UseCases
) : BaseViewModel<HomeViewModelEvent>() {

    private val _viewModelEvents = MutableSharedFlow<HomeViewModelEvent>()

    override val viewModelEvents: SharedFlow<HomeViewModelEvent>
        get() = _viewModelEvents.asSharedFlow()

    fun getTopHeadlines(category: Category) {
        viewModelScope.launch {
            useCases.homeFragmentUseCase.getTopHeadlines(category).collect {
                when (it) {
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        _viewModelEvents.emit(DisplayTopHeadlines(it.data.articles as MutableList<Article>))
                    }
                    is Resource.Failure -> {
                        _viewModelEvents.emit(ShowErrorToastMessage(it.throwable.message!!))
                    }
                }
            }
        }
    }

    fun getOtherNews() {
        viewModelScope.launch {
            useCases.homeFragmentUseCase.getOtherNews().collect {
                when (it) {
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        _viewModelEvents.emit(DisplayOtherNews(it.data.articles as MutableList<Article>))
                    }
                    is Resource.Failure -> {
                        _viewModelEvents.emit(ShowErrorToastMessage(it.throwable.message!!))
                    }
                }
            }
        }
    }
}