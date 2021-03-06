package com.paulacr.cats.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paulacr.cats.data.Converters
import com.paulacr.cats.data.model.CatImage
@TypeConverters(Converters::class)
@Database(entities = [CatImage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}
