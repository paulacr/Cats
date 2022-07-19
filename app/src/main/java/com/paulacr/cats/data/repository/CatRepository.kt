package com.paulacr.cats.data.repository

import com.paulacr.cats.data.model.Cat
import io.reactivex.Single

interface CatRepository {

//    fun getRandomCat(): Single<Cat>

//    fun getLocalRandomCat(): Cat

//    fun getRemoteRandomCat(): Single<Cat>

    fun getCatsList(limit: Int, page: Int): Single<List<Cat>>
}
