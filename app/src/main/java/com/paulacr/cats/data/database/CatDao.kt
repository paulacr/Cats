package com.paulacr.cats.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.paulacr.cats.data.model.CatImage

@Dao
interface CatDao {

    @Query("SELECT * FROM catimage")
    fun getAll(): List<CatImage>

    @Insert
    fun insertAll(vararg users: CatImage)

    @Delete
    fun delete(user: CatImage)
}