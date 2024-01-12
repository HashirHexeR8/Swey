package com.business.swey.core.utils

import androidx.core.text.isDigitsOnly

object StringUtil {
    fun getPhoneString(phone: String): String{
        val code = phone.dropLast(n = 10)
        val number = phone.takeLast(n = 10)
        return "$code       $number";
    }
}