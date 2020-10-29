package com.paulacr.cats.data.settings

import io.reactivex.Single
import kotlin.random.Random

interface AppConfig {

    fun shouldRefreshRemoteData(): Single<Boolean>
}
class AppConfigImpl : AppConfig {

    override fun shouldRefreshRemoteData(): Single<Boolean> {
        return Single.just(Random.nextBoolean())
    }
}
