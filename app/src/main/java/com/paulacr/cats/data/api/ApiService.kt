package com.paulacr.cats.data.api

import com.paulacr.cats.data.model.CatImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    @GET("images/search")
//    fun getRandomCat(
//        @Query("limit") limit: Int? = null,
//        @Query("page") page: Int? = null
//    ): Single<List<CatImageResponse>>

    @GET("images/search")
    fun getCatsList(
        @Query("limit") limit: Int? = 1,
        @Query("page") page: Int? = 0
    ): Single<List<CatImageResponse>>
}
