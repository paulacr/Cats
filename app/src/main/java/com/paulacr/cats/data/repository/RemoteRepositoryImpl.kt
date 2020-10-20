package com.paulacr.cats.data.repository

import com.paulacr.cats.data.api.ApiService
import com.paulacr.cats.data.model.CatImage
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val service: ApiService) : RemoteRepository {

    override fun getRandomCat(): Single<CatImage> = service.getRandomCat().map {
        it.first()
    }
}