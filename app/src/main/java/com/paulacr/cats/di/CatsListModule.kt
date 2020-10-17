package com.paulacr.cats.di

import com.paulacr.cats.ui.list.CatsListViewModel
import dagger.Module
import dagger.Provides

@Module
class CatsListModule {

    @Provides
    fun provideViewModel(): CatsListViewModel {
        return CatsListViewModel()
    }
}