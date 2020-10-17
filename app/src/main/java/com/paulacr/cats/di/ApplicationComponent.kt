package com.paulacr.cats.di

import com.paulacr.cats.MainActivity
import com.paulacr.cats.ui.list.CatsListFragment
import dagger.Component

@Component(modules = [CatsListModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun inject(castsListFragment: CatsListFragment)
}