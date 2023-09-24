package com.business.swey.core.utils

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

object VibrateUtil{
    fun vibrateWithEffect(context: Context, durationMillis: Long) {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
        if (vibrator.hasVibrator()) {
            val vibrationEffect = VibrationEffect.createOneShot(
                durationMillis,
                VibrationEffect.DEFAULT_AMPLITUDE
            )

            vibrator.vibrate(vibrationEffect)
        }
    }
}
