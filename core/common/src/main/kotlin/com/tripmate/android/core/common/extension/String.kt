package com.tripmate.android.core.common.extension

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.parseToLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일", Locale.KOREAN)
    return LocalDate.parse(this, formatter)
}

fun String.parseToLocalTime(): LocalTime {
    val formattedString = this.replace("오전", "AM").replace("오후", "PM")
    val formatter = DateTimeFormatter.ofPattern("a h시 mm분", Locale.ENGLISH)
    return LocalTime.parse(formattedString, formatter)
}
