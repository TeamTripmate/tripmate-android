package com.tripmate.android.core.common

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/*
https://www.youtube.com/watch?v=mB1Lej0aDus
viewModel 에서 하드코딩된 문자열의 사용을 피하기위해 String Resource 를 호출하고자 할때,
context 가 필요해서 이를 우회하기 위해 따로 UiText 클래스를 정의하였습니다.
*/
sealed class UiText {
    data class DirectString(val value: String) : UiText()

    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any,
    ) : UiText()

    @Composable
    fun asString() = when (this) {
        is DirectString -> value
        is StringResource -> stringResource(resId, *args)
    }

    fun asString(context: Context) = when (this) {
        is DirectString -> value
        is StringResource -> context.getString(resId, *args)
    }
}
