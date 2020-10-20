package com.paulacr.cats.data.repository

import com.paulacr.cats.data.model.CatImage
import io.reactivex.Single
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(private val remoteRepository: RemoteRepository) : CatRepository {

    override fun getRandomCat(): Single<CatImage> = remoteRepository.getRandomCat()
}