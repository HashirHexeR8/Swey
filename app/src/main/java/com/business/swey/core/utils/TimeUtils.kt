package com.business.swey.core.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

public class TimeUtils {
    fun formatDate(date: Date): String {
        // Get the current date and time
        val currentCalendar = Calendar.getInstance()
        val currentDate = currentCalendar.time

        // Check if the date is today
        return if (isSameDay(date, currentDate)) {
            // Format as time only (e.g., 12:00pm)
            val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
            timeFormat.format(date)
        } else {
            // Format as full date (e.g., 12 June, 2000)
            val dateFormat = SimpleDateFormat("d MMMM, yyyy", Locale.getDefault())
            dateFormat.format(date)
        }
    }

    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val cal1 = Calendar.getInstance()
        cal1.time = date1
        val cal2 = Calendar.getInstance()
        cal2.time = date2

        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
    }
}