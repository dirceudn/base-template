package com.org.core.common

import android.text.format.DateUtils
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String? {
    val dateToFormat = this
    return try {
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(dateToFormat)
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        sdf.format(date)
    } catch (e: Exception) {
        "--"
    }
}

fun Long.formatDuration(): String {
   return  DateUtils.formatElapsedTime(this)
}