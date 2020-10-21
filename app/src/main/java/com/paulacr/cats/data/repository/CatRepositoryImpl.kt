package com.paulacr.cats.data.repository

import com.paulacr.cats.data.api.ApiService
import com.paulacr.cats.data.database.CatDao
import com.paulacr.cats.data.mapper.CatMapper
import com.paulacr.cats.data.model.CatImageResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import kotlin.random.Random

class CatRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val dao: CatDao
) : CatRepository {

    private var shouldRefreshCatData = Random.nextBoolean()
    private val catMapper = CatMapper()

    override fun getRandomCat(): Single<CatImageResponse> =
        service.getRandomCat().map { it.first() }

    override fun getLocalRandomCat(): Single<CatImageResponse> {
        TODO("Not yet implemented")
    }

    override fun getRemoteRandomCat(): Single<CatImageResponse> {

        val remoteData = service.getRandomCat()
            .map {
                it.first()
            }.flatMap {
                val cat = catMapper.map(it)
                // save data
                dao.insertAll(cat)
                Single.just(it)
            }

        val localData = getLocalRandomCat()
            .onErrorResumeNext {
                remoteData
            }

        return Single.just(shouldRefreshCatData)
            .flatMap {
                if (it) {
                    remoteData
                } else {
                    localData
                }
            }
    }
}