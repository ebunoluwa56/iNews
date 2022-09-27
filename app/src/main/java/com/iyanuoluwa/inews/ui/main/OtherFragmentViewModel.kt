package com.iyanuoluwa.inews.ui.main

import android.util.Log
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
class OtherFragmentViewModel @Inject constructor(
    private val useCases: UseCases
) : BaseViewModel<OtherSegmentViewModelEvent>() {

    private val _viewModelEvents = MutableSharedFlow<OtherSegmentViewModelEvent>()

    override val viewModelEvents: SharedFlow<OtherSegmentViewModelEvent>
        get() = _viewModelEvents.asSharedFlow()

    fun getOtherSegments(category: Category) {
        viewModelScope.launch {
            useCases.otherFragmentsUseCase.getOtherSegments(category).collect {
                Log.i("ViewModel", it.toString())
                when (it) {
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        _viewModelEvents.emit(DisplaySegmentNews(it.data.articles as MutableList<Article>))
                    }
                    is Resource.Failure -> {
                        _viewModelEvents.emit(ShowErrorMessage(it.throwable.message!!))
                    }
                }
            }
        }
    }
}