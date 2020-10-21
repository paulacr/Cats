package com.paulacr.cats.data.repository

import com.paulacr.cats.data.model.CatImageResponse
import io.reactivex.rxjava3.core.Single

interface CatRepository {

    fun getRandomCat(): Single<CatImageResponse>

    fun getLocalRandomCat(): Single<CatImageResponse>

    fun getRemoteRandomCat(): Single<CatImageResponse>
}