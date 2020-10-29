package com.paulacr.cats.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.paulacr.cats.data.model.Breed
import com.paulacr.cats.data.model.Category

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromBreedList(value: List<Breed>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Breed>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toBreedList(value: String): List<Breed> {
        val gson = Gson()
        val type = object : TypeToken<List<Breed>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromCategoryList(value: List<Category>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Category>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCategoryList(value: String): List<Category> {
        val gson = Gson()
        val type = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(value, type)
    }
}
