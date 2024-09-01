package com.tripmate.android.core.common.extension

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun String.parseToLocalDate(): LocalDate {
    if (this.isBlank()) {
        // 빈 문자열인 경우 현재 날짜 반환
        return Clock.System.now().toLocalDateTime(TimeZone.of("Asia/Seoul")).date
    }

    val (year, month, day) = this.split("년 ", "월 ", "일")
        .map { it.trim().toInt() }
    return LocalDate(year, month, day)
}

fun String.parseToLocalTime(): LocalTime {
    if (this.isBlank()) {
        // 빈 문자열인 경우 현재 시간 반환
        return Clock.System.now().toLocalDateTime(TimeZone.of("Asia/Seoul")).time
    }

    val (period, time) = this.split(" ", limit = 2)
    val (hour, minute) = time.split("시 ", "분")
        .map { it.trim().toInt() }

    val adjustedHour = when {
        period == "오후" && hour != 12 -> hour + 12
        period == "오전" && hour == 12 -> 0
        else -> hour
    }

    return LocalTime(adjustedHour, minute)
}
