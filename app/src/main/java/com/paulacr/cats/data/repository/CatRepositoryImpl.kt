package com.paulacr.cats.data.repository

import com.paulacr.cats.data.api.ApiService
import com.paulacr.cats.data.model.CatImage
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import kotlin.random.Random

class CatRepositoryImpl @Inject constructor(private val service: ApiService) : CatRepository {

    private var shouldRefreshCatData = Random.nextBoolean()

    override fun getRandomCat(): Single<CatImage> = service.getRandomCat().map { it.first() }

    override fun getLocalRandomCat(): Single<CatImage> {
        TODO("Not yet implemented")
    }


    override fun getRemoteRandomCat(): Single<CatImage> =
        if (shouldRefreshCatData) {
            service.getRandomCat().map { it.first() }
        } else {
            getLocalRandomCat()
        }
}