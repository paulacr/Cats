package com.paulacr.cats.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulacr.cats.data.model.Cat

@Dao
interface CatDao {

    @Query("SELECT * FROM cat")
    fun getAll(): List<Cat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cat: Cat): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cat: List<Cat>): List<Long>

    @Delete
    fun delete(user: Cat)
}
