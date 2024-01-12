package com.business.swey.core.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object ThemeUtil {

    fun isDarkTheme(context: Context): Boolean {
        return when (context.resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK) {
            android.content.res.Configuration.UI_MODE_NIGHT_NO -> {
                false
            }

            android.content.res.Configuration.UI_MODE_NIGHT_YES -> {
                true
            }

            else -> {
                false
            }
        }
    }

    fun toggleTheme(context: Context) {

        when (context.resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK) {
            android.content.res.Configuration.UI_MODE_NIGHT_NO -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            android.content.res.Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}