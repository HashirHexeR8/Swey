package com.business.swey.core.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.business.swey.R

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
