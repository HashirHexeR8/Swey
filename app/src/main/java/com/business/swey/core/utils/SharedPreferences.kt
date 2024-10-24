package com.business.swey.core.utils

import android.content.Context

object SharedPreferences{
    fun storePreference(context: Context, key: String, value: Any){
        val sharedPref = context.getSharedPreferences(SWEY_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        when(value){
            is Boolean -> editor.putBoolean(key, value)
            is Int -> editor.putInt(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            is String -> editor.putString(key, value)
        }
        editor.apply()
    }

    fun getPreference(context: Context, key: String, defaultValue: Any): Any {
        val sharedPref = context.getSharedPreferences(SWEY_PREFS, Context.MODE_PRIVATE)
        when (defaultValue) {
            is Boolean -> sharedPref.getBoolean(key, defaultValue)
            is Int -> sharedPref.getInt(key, defaultValue)
            is Float -> sharedPref.getFloat(key, defaultValue)
            is Long -> sharedPref.getLong(key, defaultValue)
            is String -> sharedPref.getString(key, defaultValue)
            else -> defaultValue
        }?.let {
            return it
        }
        return defaultValue;
    }

    private const val SWEY_PREFS = "sweyPrefs"
    const val IS_DARK_MODE_ENABLED = "isDarkModeEnabled"
}