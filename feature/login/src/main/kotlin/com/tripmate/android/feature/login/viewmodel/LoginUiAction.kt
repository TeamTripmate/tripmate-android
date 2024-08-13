package com.tripmate.android.feature.login.viewmodel

sealed interface LoginUiAction {
    data object OnLoginButtonClick : LoginUiAction
}
