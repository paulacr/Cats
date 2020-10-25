package com.paulacr.cats.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulacr.cats.data.model.CatImage

@Dao
interface CatDao {

    @Query("SELECT * FROM catimage")
    fun getAll(): List<CatImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cat: CatImage): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg cat: CatImage): List<Long>

    @Delete
    fun delete(user: CatImage)
}
