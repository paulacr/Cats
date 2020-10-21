package com.paulacr.cats

import com.paulacr.cats.data.model.CatImageResponse
import com.paulacr.cats.data.repository.CatRepository
import com.paulacr.cats.ui.list.CatsListViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as mockitoWhen

class CatsListViewModelTest {

    @Rule @JvmField
    var testSchedule = RxRule()

    @Mock
    lateinit var repository: CatRepository

    lateinit var viewModel: CatsListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = CatsListViewModel(repository)
    }

    @Test
    fun loadSingleCat() {

        mockitoWhen(repository.getRandomCat()).thenReturn(
            Single.just(CatImageResponse("1", "", listOf(), listOf()))
        )

        val observable: Single<CatImageResponse> = repository.getRandomCat()
        val observer: TestObserver<CatImageResponse> = TestObserver()
        observable.subscribe(observer)

        observer.assertComplete()
            .assertNoErrors()
            .assertValue(CatImageResponse("1", "", listOf(), listOf()))
    }
}