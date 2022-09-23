package com.iyanuoluwa.inews.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseViewModel<T> : ViewModel(){

    abstract val viewModelEvents : SharedFlow<T>
}