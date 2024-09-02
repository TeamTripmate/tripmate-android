package com.tripmate.android.core.common.extension

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun String.parseToLocalDate(): LocalDate {
    if (this.isBlank()) {
        return Clock.System.now().toLocalDateTime(TimeZone.of("Asia/Seoul")).date
    }

    require(this.matches(Regex("^\\d{4}년\\s*\\d{1,2}월\\s*\\d{1,2}일$"))) { "잘못된 날짜 형식: $this" }

    val (year, month, day) = this.split("년", "월", "일")
        .filter { it.isNotBlank() }
        .map { it.trim().toInt() }
    return LocalDate(year, month, day)
}

fun String.parseToLocalTime(): LocalTime {
    if (this.isBlank()) {
        // 빈 문자열인 경우 현재 시간 반환
        return Clock.System.now().toLocalDateTime(TimeZone.of("Asia/Seoul")).time
    }

    require(this.matches(Regex("^(오전|오후)\\s*\\d{1,2}시\\s*\\d{1,2}분$"))) { "잘못된 시간 형식: $this" }

    val (period, time) = this.split(" ", limit = 2)
    val (hour, minute) = time.split("시 ", "분")
        .filter { it.isNotBlank() }
        .map { it.trim().toInt() }

    require(hour in 1..12 && minute in 0..59) { "유효하지 않은 시간 또는 분: $this" }

    val adjustedHour = when {
        period == "오후" && hour != 12 -> hour + 12
        period == "오전" && hour == 12 -> 0
        else -> hour
    }

    return LocalTime(adjustedHour, minute)
}
