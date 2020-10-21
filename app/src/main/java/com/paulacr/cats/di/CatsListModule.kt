package com.paulacr.cats.di

import com.paulacr.cats.data.api.ApiService
import com.paulacr.cats.data.repository.CatRepository
import com.paulacr.cats.data.repository.CatRepositoryImpl
import com.paulacr.cats.ui.list.CatsListViewModel
import dagger.Module
import dagger.Provides

@Module
class CatsListModule {

    @Provides
    fun provideViewModel(repository: CatRepository): CatsListViewModel {
        return CatsListViewModel(repository)
    }

    @Provides
    fun provideRepository(service: ApiService): CatRepository {
        return CatRepositoryImpl(service)
    }
}