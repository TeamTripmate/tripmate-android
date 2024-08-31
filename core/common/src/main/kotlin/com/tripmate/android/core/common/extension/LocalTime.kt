package com.tripmate.android.core.common.extension

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalTime.formatToTime(): String {
    val formatter = DateTimeFormatter.ofPattern("a h시 mm분", Locale.KOREAN)
    return this.format(formatter)
        .replace("AM", "오전")
        .replace("PM", "오후")
}
