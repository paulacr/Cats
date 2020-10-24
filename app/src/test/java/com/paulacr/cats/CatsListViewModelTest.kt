package com.paulacr.cats

import com.paulacr.cats.data.model.CatImage
import com.paulacr.cats.data.repository.CatRepository
import com.paulacr.cats.ui.list.CatsListViewModel
import io.reactivex.Single
import io.reactivex.observers.TestObserver
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
    fun showGetRandomCat() {

        val catImage = CatImage("1", "", emptyList(), emptyList())
        mockitoWhen(repository.getRandomCat()).thenReturn(Single.just(catImage))

        val observable: Single<CatImage> = repository.getRandomCat()
        val observer: TestObserver<CatImage> = TestObserver()
        observable.subscribe(observer)

        observer.assertComplete()
            .assertNoErrors()
            .assertValue(CatImage("1", "", emptyList(), emptyList()))
    }

    @Test
    fun shouldHandleErrorWhenRepositoryThrowsException() {

        mockitoWhen(repository.getRandomCat()).thenReturn(
            Single.error(Exception()))

        val observable: Single<CatImage> = repository.getRandomCat()
        val observer: TestObserver<CatImage> = TestObserver()
        observable.subscribe(observer)

        observer
            .assertError(Exception::class.java)
    }
}