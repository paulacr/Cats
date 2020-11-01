package com.paulacr.cats.ui.list

import com.paulacr.cats.data.model.CatImage
import com.paulacr.cats.data.repository.CatRepository
import com.paulacr.cats.ui.BaseViewModel
import com.paulacr.cats.ui.StateManager
import com.paulacr.cats.ui.ViewState
import com.paulacr.cats.utils.logError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatsListViewModel @Inject constructor(private val repository: CatRepository) :
    BaseViewModel() {

    val catsListLiveData = StateManager<List<CatImage>>()

    fun getRandomCat() =
        repository.getRandomCat()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                catsListLiveData.postValue(ViewState.Success(listOf(it)))
            }
            .doOnError {
                logError("Random cat", it)
                catsListLiveData.postValue(ViewState.Failure(Exception()))
            }
            .subscribe()
            .addToDisposables()

    fun getCatsList() =
        repository.getCatsList(3, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                catsListLiveData.postValue(ViewState.Success(it))
            }
            .doOnError {
                logError("Random cat", it)
                catsListLiveData.postValue(ViewState.Failure(Exception()))
            }
            .subscribe()
            .addToDisposables()
}
