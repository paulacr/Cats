package com.paulacr.cats.utils

import android.util.Log
import android.view.View

fun Any.logError(tag: String = "", throwable: Throwable) {
    val message = if (tag.isEmpty()) "Exception -> "
    else tag

    Log.e(message, throwable.localizedMessage ?: throwable.message ?: "")
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}
