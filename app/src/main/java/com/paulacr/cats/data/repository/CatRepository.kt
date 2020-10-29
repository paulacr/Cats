package com.paulacr.cats.data.repository

import com.paulacr.cats.data.model.CatImage
import io.reactivex.Single

interface CatRepository {

    fun getRandomCat(): Single<CatImage>

    fun getLocalRandomCat(): CatImage

    fun getRemoteRandomCat(): Single<CatImage>
}
