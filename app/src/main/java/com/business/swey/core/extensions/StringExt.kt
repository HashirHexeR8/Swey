package com.business.swey.core.extensions

import androidx.core.text.isDigitsOnly
import java.util.Locale

fun String.isPhoneNumber(): Boolean {
    return this.removePrefix("+").run { isDigitsOnly() && length <= 13 }
}

fun String.capitalize(): String {
    return lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}