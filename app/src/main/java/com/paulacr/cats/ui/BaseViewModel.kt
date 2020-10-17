package com.paulacr.cats.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel :  ViewModel() {

    private var disposables = CompositeDisposable()

    fun Disposable.addToDisposables() = apply { disposables.add(this) }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}