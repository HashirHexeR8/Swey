package com.business.swey.core.models

import androidx.annotation.DrawableRes

data class SweyUserDto(
    val id: Int,
    @DrawableRes
    val imageRes: Int
)
