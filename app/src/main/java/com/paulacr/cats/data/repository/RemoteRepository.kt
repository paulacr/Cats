package com.paulacr.cats.data.repository

import com.paulacr.cats.data.model.CatImage
import io.reactivex.Single

interface RemoteRepository {

    fun getRandomCat(): Single<CatImage>
}