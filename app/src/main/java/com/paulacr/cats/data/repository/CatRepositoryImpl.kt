package com.paulacr.cats.data.repository

import com.paulacr.cats.data.api.ApiService
import com.paulacr.cats.data.database.CatDao
import com.paulacr.cats.data.mapper.CatMapper
import com.paulacr.cats.data.model.Cat
import com.paulacr.cats.data.settings.AppConfig
import com.paulacr.cats.utils.logError
import io.reactivex.Single
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val dao: CatDao,
    private val catMapper: CatMapper,
    private val appConfig: AppConfig
) : CatRepository {

    override fun getRandomCat(): Single<Cat> {
        return appConfig.shouldRefreshRemoteData()
            .flatMap {
                if (it) getRemoteRandomCat()
                else Single.just(getLocalRandomCat())
            }.onErrorResumeNext {
                getRemoteRandomCat()
            }
    }

    override fun getLocalRandomCat(): Cat = dao.getAll().random()

    override fun getRemoteRandomCat(): Single<Cat> =
        service.getRandomCat()
            .doOnError {
                logError("Random cat remote error:", it)
            }.map {
                it.first()
            }.flatMap {
                val cat = catMapper.map(it)
                dao.insert(cat)
                Single.just(cat)
            }

    override fun getCatsList(limit: Int, page: Int): Single<List<Cat>> =
        service.getRandomCat(limit, page)
            .flatMap { catsListResponse ->
                val catsList: MutableList<Cat> = mutableListOf()
                catsListResponse.forEach {
                    catsList.add(catMapper.map(it))
                }

                if (catsList.isNotEmpty()) dao.insertAll(catsList)
                Single.just(catsList)
            }
}
