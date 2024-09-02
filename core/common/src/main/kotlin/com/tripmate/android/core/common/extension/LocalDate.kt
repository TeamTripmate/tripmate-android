package com.tripmate.android.core.common.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDate.formatToDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일", Locale.KOREAN)
    return this.format(formatter)
}

// fun LocalDate.formatToDate(): String {
//     return "${year}년 ${monthNumber.toString().padStart(2, '0')}월 ${dayOfMonth.toString().padStart(2, '0')}일"
// }
