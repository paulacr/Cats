package com.paulacr.cats

import com.paulacr.cats.data.model.Cat
import com.paulacr.cats.data.repository.CatRepository
import com.paulacr.cats.ui.list.CatsListViewModel
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when` as mockitoWhen
import org.mockito.MockitoAnnotations

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

        val catImage = Cat("1", "", emptyList(), emptyList())
        mockitoWhen(repository.getRandomCat()).thenReturn(Single.just(catImage))

        val observable: Single<Cat> = repository.getRandomCat()
        val observer: TestObserver<Cat> = TestObserver()
        observable.subscribe(observer)

        observer.assertComplete()
            .assertNoErrors()
            .assertValue(Cat("1", "", emptyList(), emptyList()))
    }

    @Test
    fun shouldHandleErrorWhenRepositoryThrowsException() {

        mockitoWhen(repository.getRandomCat()).thenReturn(
            Single.error(Exception()))

        val observable: Single<Cat> = repository.getRandomCat()
        val observer: TestObserver<Cat> = TestObserver()
        observable.subscribe(observer)

        observer
            .assertError(Exception::class.java)
    }
}
