package com.paulacr.cats.data.repository

import com.paulacr.cats.data.api.ApiService
import com.paulacr.cats.data.database.CatDao
import com.paulacr.cats.data.mapper.CatMapper
import com.paulacr.cats.data.model.CatImage
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

    override fun getRandomCat(): Single<CatImage> {
        return appConfig.shouldRefreshRemoteData()
            .flatMap {
                if (it) getRemoteRandomCat()
                else Single.just(getLocalRandomCat())
            }.onErrorResumeNext {
                getRemoteRandomCat()
            }
    }

    override fun getLocalRandomCat(): CatImage = dao.getAll().random()

    override fun getRemoteRandomCat(): Single<CatImage> =
        service.getRandomCat()
            .doOnError {
                logError("Random cat remote:", it)
            }
            .map {
                it.first()
            }.flatMap {
                val cat = catMapper.map(it)
                dao.insert(cat)
                Single.just(cat)
            }
}