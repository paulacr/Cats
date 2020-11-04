package com.paulacr.cats.ui

import android.app.Application
import com.paulacr.cats.BuildConfig
import com.paulacr.cats.di.ApplicationComponent
import com.paulacr.cats.di.DaggerApplicationComponent

open class CatsApplication : Application() {
    val appComponent: ApplicationComponent =
        DaggerApplicationComponent.builder().application(this).build()

    open fun getBaseUrl() = BuildConfig.BASE_URL
}
