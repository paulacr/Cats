package com.paulacr.cats.data

import com.paulacr.cats.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CatsInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request()
        val url = newRequest.url
            .newBuilder()
            .addQueryParameter(API_KEY_NAME, BuildConfig.API_KEY)
            .build()

        newRequest.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }

    companion object {
        const val API_KEY_NAME = "x-api-key"
    }
}