package com.business.swey

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate()
    }
}