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

// fun LocalTime.formatToTime(): String {
//     val period = if (hour < 12) "오전" else "오후"
//     val adjustedHour = when {
//         hour == 0 -> 12
//         hour > 12 -> hour - 12
//         else -> hour
//     }
//     return "$period ${adjustedHour}시 ${minute.toString().padStart(2, '0')}분"
// }
