package com.paulacr.cats.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.paulacr.cats.data.model.CatImage
import com.paulacr.cats.data.repository.CatRepository
import com.paulacr.cats.ui.BaseViewModel
import com.paulacr.cats.utils.logError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatsListViewModel @Inject constructor(private val repository: CatRepository) :
    BaseViewModel() {

    fun getRandomCat() =
        repository.getRandomCat()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                Log.i("Cats", "working")
            }
            .doOnError {
                logError("Random cat", it)
            }
}
