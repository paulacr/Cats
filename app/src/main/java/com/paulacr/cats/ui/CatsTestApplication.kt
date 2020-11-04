package com.paulacr.cats.ui

class CatsTestApplication : CatsApplication() {

    var url = "http://127.0.0.1:8080"

    override fun getBaseUrl(): String {
        return url
    }
}
