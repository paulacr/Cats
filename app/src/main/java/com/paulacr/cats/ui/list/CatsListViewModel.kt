package com.paulacr.cats.ui.list

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.paulacr.cats.data.model.Cat
import com.paulacr.cats.data.repository.CatRepository
import com.paulacr.cats.ui.BaseViewModel
import com.paulacr.cats.ui.ViewState
import com.paulacr.cats.utils.logError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatsListViewModel @Inject constructor(private val repository: CatRepository) :
    BaseViewModel() {

    val catsListLiveData = MutableLiveData<List<Cat>>()
    val viewState = ObservableField<ViewState>()

//    fun getRandomCat() =
//        repository.getRandomCat()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSuccess {
//                catsListLiveData.postValue(ViewState.Success(listOf(it)))
//            }
//            .doOnError {
//                logError("Random cat", it)
//                catsListLiveData.postValue(ViewState.Failure(Exception()))
//            }
//            .subscribe()
//            .addToDisposables()

    fun getCatsList(): Disposable {

        return repository.getCatsList(3, 0)
            .doOnSubscribe {
                viewState.set(ViewState.Loading)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ cats ->
                viewState.set(ViewState.Success(cats))
                catsListLiveData.postValue(cats)
            }, { exception ->
                logError("Cats list", exception)
                viewState.set((ViewState.Failure(Exception())))
            })
            .addToDisposables()
    }
}
