package com.paulacr.cats.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatImage(@PrimaryKey val id: String, val url: String, val breedList: List<Breed>?, val categoryList: List<Category>?)

@Entity
data class Breed(val id: String, val name: String, val temperament: String)

@Entity
data class Category(val id: String, val name: String)