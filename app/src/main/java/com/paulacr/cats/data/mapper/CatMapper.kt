package com.paulacr.cats.data.mapper

import com.paulacr.cats.data.model.Breed
import com.paulacr.cats.data.model.CatImage
import com.paulacr.cats.data.model.CatImageResponse
import com.paulacr.cats.data.model.Category
import okhttp3.internal.toImmutableList

class CatMapper {

    fun map(catImageResponse: CatImageResponse): CatImage {

        return CatImage(
            catImageResponse.id,
            catImageResponse.url,
            getBreeds(catImageResponse),
            getCategories(catImageResponse)
        )
    }

    private fun getCategories(
        catImageResponse: CatImageResponse
    ): List<Category> {
        val categories = mutableListOf<Category>()

        catImageResponse.categoryListResponse?.apply {
            if (isNotEmpty()) {
                catImageResponse.categoryListResponse.forEach {
                    categories.add(Category(it.id, it.name))
                }
            }
        }
        return categories.toImmutableList()
    }

    private fun getBreeds(
        catImageResponse: CatImageResponse
    ): List<Breed> {
        val breeds = mutableListOf<Breed>()

        catImageResponse.breedListResponse?.apply {
            if (isNotEmpty()) {
                catImageResponse.breedListResponse.forEach {
                    breeds.add(Breed(it.id, it.name, it.temperament))
                }
            }
        }
        return breeds.toImmutableList()
    }
}