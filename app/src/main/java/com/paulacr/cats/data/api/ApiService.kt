package com.paulacr.cats.data.api

import com.paulacr.cats.data.model.CatImageResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("images/search")
    fun getRandomCat(): Single<List<CatImageResponse>>
}