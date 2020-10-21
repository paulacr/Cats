package com.paulacr.cats.data.api

import com.paulacr.cats.data.model.CatImage
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("images/search")
    fun getRandomCat(): Single<List<CatImage>>
}