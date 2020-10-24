package com.paulacr.cats.di

import com.paulacr.cats.data.api.ApiService
import com.paulacr.cats.data.database.CatDao
import com.paulacr.cats.data.mapper.CatMapper
import com.paulacr.cats.data.repository.CatRepository
import com.paulacr.cats.data.repository.CatRepositoryImpl
import com.paulacr.cats.data.settings.AppConfig
import com.paulacr.cats.data.settings.AppConfigImpl
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
    fun provideRepository(service: ApiService, dao: CatDao, catsMapper: CatMapper, appConfig: AppConfig): CatRepository {
        return CatRepositoryImpl(service, dao, catsMapper, appConfig)
    }

    @Provides
    fun provideMapper() = CatMapper()

    @Provides
    fun provideAppConfig(): AppConfig = AppConfigImpl()
}