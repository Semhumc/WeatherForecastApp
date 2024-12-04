package com.example.wheatherapp.extension

import java.text.SimpleDateFormat
import java.util.*


fun String.convertDateToWeekNameYear(format: String): String? {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    val parsedDate = dateFormat.parse(this)
    val dayMonthFormat =
        SimpleDateFormat(DateFormats.DAY_OF_WEEK_MONTH_FORMAT.format, Locale.getDefault())
    return parsedDate?.let { dayMonthFormat.format(parsedDate) }
}

