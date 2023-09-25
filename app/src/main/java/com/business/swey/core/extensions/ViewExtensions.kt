package com.business.swey.core.extensions

import android.app.Activity
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.business.swey.R
import com.google.android.material.elevation.SurfaceColors

@BindingAdapter("imageUrl", "showProgressDrawable", requireAll = false)
fun ImageView.loadUrl(url: String, showProcessDrawable: Boolean = true) {
    val requestOptions = if (showProcessDrawable) {
        RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(CenterCrop())
            .placeholder(R.drawable.ic_login_asset)
    } else {
        RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
    }
    Glide.with(context).load(url).apply(requestOptions).into(this)
}

fun Activity.adjustTheme(){
    setTheme(R.style.Activity_NoTitle)
    window?.statusBarColor = SurfaceColors.SURFACE_0.getColor(this)
    window?.navigationBarColor = SurfaceColors.SURFACE_0.getColor(this)
}
