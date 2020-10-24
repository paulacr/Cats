package com.paulacr.cats.ui

import android.app.Application
import com.paulacr.cats.di.ApplicationComponent
import com.paulacr.cats.di.DaggerApplicationComponent

class CatsApplication : Application() {
    val appComponent: ApplicationComponent =
        DaggerApplicationComponent.builder().application(this).build()
}