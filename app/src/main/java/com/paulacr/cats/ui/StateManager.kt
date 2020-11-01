package com.paulacr.cats.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.Exception

class StateManager<T> {

    private val itemViewState: MutableLiveData<ViewState<T>> = MutableLiveData()

    fun postValue(state: ViewState<T>) = itemViewState.postValue(state)

    fun observe(owner: LifecycleOwner, observer: Observer<ViewState<T>>) {
        itemViewState.observe(owner, observer)
    }
}

sealed class ViewState<T> {

    class Success<T>(val data: T) : ViewState<T>()

    class Failure<T>(exception: Exception) : ViewState<T>()

    class Loading<T>() : ViewState<T>()
}
