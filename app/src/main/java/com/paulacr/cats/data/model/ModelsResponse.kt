package com.paulacr.cats.data.model

data class CatImageResponse(val id: String, val url: String, val breedListResponse: List<BreedResponse>, val categoryListResponse: List<CategoryResponse>)

data class BreedResponse(val id: String, val name: String, val temperament: String)

data class CategoryResponse(val id: String, val name: String)