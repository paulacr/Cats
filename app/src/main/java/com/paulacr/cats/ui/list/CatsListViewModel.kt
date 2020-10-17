package com.paulacr.cats.ui.list

import android.util.Log
import com.paulacr.cats.data.api.ApiService
import com.paulacr.cats.ui.BaseViewModel
import com.paulacr.cats.utils.logError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatsListViewModel @Inject constructor(val service: ApiService) : BaseViewModel() {

    fun getRandomCat() =
        service.getRandomCat()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                Log.i("Cats", "working")
            }
            .doOnError {
                logError("Random cat", it)
            }
            .subscribe()
            .addToDisposables()
}