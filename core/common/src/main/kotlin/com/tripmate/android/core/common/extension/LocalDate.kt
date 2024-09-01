package com.tripmate.android.core.common.extension

import kotlinx.datetime.LocalDate

fun LocalDate.formatToDate(): String {
    return "${year}년 ${monthNumber.toString().padStart(2, '0')}월 ${dayOfMonth.toString().padStart(2, '0')}일"
}
