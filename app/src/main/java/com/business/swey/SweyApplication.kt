package com.business.swey

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.business.swey.core.utils.SharedPreferences
import com.business.swey.core.utils.SharedPreferences.IS_DARK_MODE_ENABLED
import com.business.swey.core.utils.ThemeUtil

class SweyApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: SweyApplication? = null

        fun context() : SweyApplication {
            return instance as SweyApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        val selectedIsDark = SharedPreferences.getPreference(this, IS_DARK_MODE_ENABLED, defaultValue = false) as Boolean
        val currentIsDark = ThemeUtil.isDarkTheme(this)
        if (selectedIsDark != currentIsDark)
            ThemeUtil.toggleTheme(this)
    }
}