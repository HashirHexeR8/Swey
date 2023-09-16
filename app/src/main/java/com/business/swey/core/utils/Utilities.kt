package com.business.swey.core.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

class Utilities {
    companion object {
        val instance = Utilities()
    }

    fun convertPxToDP(context: Context, valuePx: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valuePx, context.resources.displayMetrics).toInt()
    }

    fun getWidthOfScreen(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getHeightOfScreen(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }
}