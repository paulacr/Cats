package com.paulacr.cats.ui.list

import androidx.lifecycle.MutableLiveData
import com.paulacr.cats.data.model.Cat
import com.paulacr.cats.data.repository.CatRepository
import com.paulacr.cats.ui.BaseViewModel
import com.paulacr.cats.ui.ViewState
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatsListViewModel @Inject constructor(private val repository: CatRepository) :
    BaseViewModel() {

    fun getCatsList(): Single<ViewState> {

        return repository.getCatsList(100, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                ViewState.Failure(Exception())
            }
            .map {
                ViewState.Success(it)
            }
//            .onErrorReturnItem())
    }
}
