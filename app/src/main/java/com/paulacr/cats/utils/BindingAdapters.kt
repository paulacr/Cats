package com.paulacr.cats.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.paulacr.cats.R
import com.paulacr.cats.ui.ViewState

@BindingAdapter("imageUrl")
fun loadImageFromUrl(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(R.mipmap.ic_launcher)
        .into(view)
}

@BindingAdapter("hideOnLoading")
fun ViewGroup.hideOnLoading(viewState: ViewState) {
    visibility = if (viewState is ViewState.Loading || viewState is ViewState.Failure)
        View.GONE
    else
        View.VISIBLE
}

@BindingAdapter("showOnLoading")
fun ViewGroup.showOnLoading(viewState: ViewState) {
    visibility = if (viewState is ViewState.Loading)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("showOnError")
fun ViewGroup.showOnError(viewState: ViewState) {
    visibility = if (viewState is ViewState.Failure)
        View.VISIBLE
    else
        View.GONE
}
