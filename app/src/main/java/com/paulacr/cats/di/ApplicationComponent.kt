package com.paulacr.cats.di

import android.app.Application
import com.paulacr.cats.MainActivity
import com.paulacr.cats.ui.list.CatsListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CatsListModule::class, NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun inject(castsListFragment: CatsListFragment)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}
