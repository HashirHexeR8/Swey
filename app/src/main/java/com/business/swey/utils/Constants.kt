package com.business.swey.utils

import android.util.TypedValue
import com.business.swey.SweyApplication

class Constants {
    companion object {
        val horizontalScrollableProductHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 223f, SweyApplication.context().resources.displayMetrics).toInt()
        val horizontalProductHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 223f, SweyApplication.context().resources.displayMetrics).toInt()
        val verticalProductHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 422f, SweyApplication.context().resources.displayMetrics).toInt()
    }
}