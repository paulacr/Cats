package com.paulacr.cats.di

import android.app.Application
import androidx.room.Room
import com.paulacr.cats.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase = Room
        .databaseBuilder(application.applicationContext, AppDatabase::class.java, "cats_db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideCatDao(db: AppDatabase) = db.catDao()
}
