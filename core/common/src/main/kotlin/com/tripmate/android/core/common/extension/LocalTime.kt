package com.tripmate.android.core.common.extension

import kotlinx.datetime.LocalTime

fun LocalTime.formatToTime(): String {
    val period = if (hour < 12) "오전" else "오후"
    val adjustedHour = when {
        hour == 0 -> 12
        hour > 12 -> hour - 12
        else -> hour
    }
    return "$period ${adjustedHour}시 ${minute.toString().padStart(2, '0')}분"
}
