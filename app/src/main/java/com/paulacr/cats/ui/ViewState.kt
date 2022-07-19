package com.paulacr.cats.ui

import com.paulacr.cats.data.model.Cat
import java.lang.Exception

sealed class ViewState {

    data class Success(val data: List<Cat>) : ViewState()

    data class Failure(val exception: Exception) : ViewState()

    object Loading : ViewState()
}
