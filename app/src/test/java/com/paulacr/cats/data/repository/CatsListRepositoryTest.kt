package com.paulacr.cats.data.repository

import com.paulacr.cats.RxRule
import com.paulacr.cats.data.api.ApiService
import com.paulacr.cats.data.database.CatDao
import com.paulacr.cats.data.mapper.CatMapper
import com.paulacr.cats.data.model.CatImage
import com.paulacr.cats.data.model.CatImageResponse
import com.paulacr.cats.data.settings.AppConfig
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.NoSuchElementException
import kotlin.random.Random
import org.mockito.Mockito.`when` as mockitoWhen

@RunWith(MockitoJUnitRunner::class)
class CatsListRepositoryTest {

    @Rule @JvmField
    var testSchedule = RxRule()

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var catDao: CatDao

    @Mock
    lateinit var appConfig: AppConfig

    private var catMapper: CatMapper = CatMapper()
    private lateinit var repository: CatRepository
    private lateinit var randomNumberMock: Random

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = CatRepositoryImpl(apiService, catDao, catMapper, appConfig)
        randomNumberMock = Mockito.mock(Random::class.java)
    }

    @Test
    fun shouldGetRemoteCatIfThereIsNoItemOnDatabaseWhenShouldGetFromRemoteFlagIsFalse() {

        mockitoWhen(randomNumberMock.nextBoolean()).thenReturn(false)

        val catImage = CatImage("1", "http://someurl.com", emptyList(), emptyList())
        val catImageResponse = CatImageResponse("1", "http://someurl.com", emptyList(), emptyList())

        mockitoWhen(appConfig.shouldRefreshRemoteData()).thenReturn(
            Single.just(false))

        mockitoWhen(catDao.getAll()).thenThrow(
            NoSuchElementException(""))

        mockitoWhen(catDao.insert(catImage)).thenReturn(
            1)

        mockitoWhen(apiService.getRandomCat()).thenReturn(
            Single.just(listOf(catImageResponse)))

        val result = repository.getRandomCat()
        val testObserver: TestObserver<CatImage> = TestObserver()
        result.subscribe(testObserver)

        testObserver
            .assertValue(CatImage("1", "http://someurl.com", emptyList(), emptyList()))
            .assertComplete()

        verify(catDao).getAll()
        verify(apiService).getRandomCat()
        verify(catDao).insert(catImage)
    }

    @Test
    fun shouldGetRemoteCatIfWhenShouldGetFromRemoteFlagIsTrue() {

        mockitoWhen(randomNumberMock.nextBoolean()).thenReturn(true)

        val catImage = CatImage("1", "http://someurl.com", emptyList(), emptyList())
        val catImageResponse = CatImageResponse("1", "http://someurl.com", emptyList(), emptyList())

        mockitoWhen(appConfig.shouldRefreshRemoteData()).thenReturn(
            Single.just(true))

        mockitoWhen(catDao.insert(catImage)).thenReturn(
            1)

        mockitoWhen(apiService.getRandomCat()).thenReturn(
            Single.just(listOf(catImageResponse)))

        val result = repository.getRandomCat()
        val testObserver: TestObserver<CatImage> = TestObserver()
        result.subscribe(testObserver)

        testObserver
            .assertValue(CatImage("1", "http://someurl.com", emptyList(), emptyList()))
            .assertComplete()

        verify(catDao, never()).getAll()
        verify(apiService).getRandomCat()
    }

    @Test
    fun shouldSaveCatOnDBWhenShouldGetFromRemoteFlagIsTrue() {

        mockitoWhen(randomNumberMock.nextBoolean()).thenReturn(true)

        val catImage = CatImage("1", "http://someurl.com", emptyList(), emptyList())
        val catImageResponse = CatImageResponse("1", "http://someurl.com", emptyList(), emptyList())

        mockitoWhen(appConfig.shouldRefreshRemoteData()).thenReturn(
            Single.just(true))

        mockitoWhen(catDao.insert(catImage)).thenReturn(
            1)

        mockitoWhen(apiService.getRandomCat()).thenReturn(
            Single.just(listOf(catImageResponse)))

        val result = repository.getRandomCat()
        val testObserver: TestObserver<CatImage> = TestObserver()
        result.subscribe(testObserver)

        testObserver
            .assertValue(CatImage("1", "http://someurl.com", emptyList(), emptyList()))
            .assertComplete()

        verify(catDao, never()).getAll()
        verify(apiService).getRandomCat()
        verify(catDao).insert(catImage)
    }

    @Test
    fun shouldGetRandomLocalCatWhenShouldGetFromRemoteFlagIsFalse() {

        mockitoWhen(randomNumberMock.nextBoolean()).thenReturn(false)

        val catImage = CatImage("1", "http://someurl.com", emptyList(), emptyList())

        mockitoWhen(appConfig.shouldRefreshRemoteData()).thenReturn(
            Single.just(false))

        mockitoWhen(catDao.getAll()).thenReturn(
            listOf(catImage))

        val result = repository.getRandomCat()
        val testObserver: TestObserver<CatImage> = TestObserver()
        result.subscribe(testObserver)

        testObserver
            .assertValue(CatImage("1", "http://someurl.com", emptyList(), emptyList()))
            .assertComplete()

        verify(catDao).getAll()
        verify(apiService, never()).getRandomCat()
        verify(catDao, never()).insert(catImage)
    }
}