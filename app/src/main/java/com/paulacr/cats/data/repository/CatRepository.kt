package com.paulacr.cats.data.repository

import com.paulacr.cats.data.model.CatImage
import io.reactivex.rxjava3.core.Single

interface CatRepository {

    fun getRandomCat(): Single<CatImage>

    fun getLocalRandomCat(): Single<CatImage>

    fun getRemoteRandomCat(): Single<CatImage>
}