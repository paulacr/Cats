package com.paulacr.cats.data.mapper

import com.paulacr.cats.data.model.Breed
import com.paulacr.cats.data.model.CatImage
import com.paulacr.cats.data.model.CatImageResponse
import com.paulacr.cats.data.model.Category

class CatMapper {

    fun map(catImageResponse: CatImageResponse): CatImage {

        val breeds = mutableListOf<Breed>()
        val categories = mutableListOf<Category>()

        catImageResponse.breedListResponse.forEach {
            breeds.add(Breed(it.id, it.name, it.temperament))
        }

        catImageResponse.categoryListResponse.forEach {
            categories.add(Category(it.id, it.name))
        }

        return CatImage(
            catImageResponse.id,
            catImageResponse.url,
            breeds.toList(),
            categories.toList()
        )
    }
}