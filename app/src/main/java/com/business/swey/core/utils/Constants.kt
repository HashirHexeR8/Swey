package com.business.swey.core.utils

import android.util.TypedValue
import com.business.swey.SweyApplication

object Constants {
    val horizontalScrollableProductHeight = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        223f,
        SweyApplication.context().resources.displayMetrics
    ).toInt()
    val horizontalProductHeight = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        223f,
        SweyApplication.context().resources.displayMetrics
    ).toInt()
    val verticalProductHeight = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        422f,
        SweyApplication.context().resources.displayMetrics
    ).toInt()
}

object ChatItemTypes {
    const val SEND_TEXT = 100
    const val SEND_IMAGE = 101
    const val SEND_VIDEO = 102
    const val SEND_AUDIO = 103
    const val SEND_LOCATION = 104
    const val SEND_QUICK_PAY = 105
    const val SEND_FILE = 106
    const val SEND_POLL = 107
    const val RECEIVE_TEXT = 200
    const val RECEIVE_IMAGE = 201
    const val RECEIVE_VIDEO = 202
    const val RECEIVE_AUDIO = 203
    const val RECEIVE_LOCATION = 204
    const val RECEIVE_QUICK_PAY = 205
    const val RECEIVE_FILE = 206
    const val RECEIVE_POLL = 207
    const val TIMESTAMP = 208
}
