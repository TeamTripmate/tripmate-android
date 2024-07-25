package com.tripmate.android.core.designsystem.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tripmate.android.core.designsystem.R

val pretendardFamily = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
)

val Title0 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
)
