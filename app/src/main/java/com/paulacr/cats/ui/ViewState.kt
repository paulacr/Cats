package com.paulacr.cats.ui

import java.lang.Exception

sealed class ViewState {

    class Success<T>(val data: T) : ViewState()

    class Failure(exception: Exception) : ViewState()

    object Loading : ViewState()
}
