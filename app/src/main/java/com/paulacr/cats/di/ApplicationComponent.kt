package com.paulacr.cats.di

import com.paulacr.cats.MainActivity
import com.paulacr.cats.ui.list.CatsListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CatsListModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun inject(castsListFragment: CatsListFragment)
}