package com.tripmate.android.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthError
import com.kakao.sdk.user.UserApiClient
import com.tripmate.android.feature.login.viewmodel.LoginUiState
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.common.UiText
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Kakao
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.LoadingWheel
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.feature.login.viewmodel.LoginUiAction
import com.tripmate.android.feature.login.viewmodel.LoginUiEvent
import com.tripmate.android.feature.login.viewmodel.LoginViewModel
import timber.log.Timber

@Composable
internal fun LoginRoute(
    navigateToMain: () -> Unit,
    navigateToPersonalization: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        when {
            error != null -> when {
                (error is AuthError && error.response.error == "ProtocolError") -> {
                    Timber.e("로그인 실패: ${error.response.error}, ${error.response.errorDescription}")
                    viewModel.setErrorMessage(UiText.StringResource(R.string.network_error_message))
                }

                else -> {
                    Timber.e("로그인 실패: ${error.message}")
                    viewModel.setErrorMessage(UiText.StringResource(R.string.unknown_error_message))
                }
            }

            token != null -> UserApiClient.instance.me { user, _ ->
                user?.let {
                    Timber.d(
                        "로그인 성공: ${token.accessToken}," +
                            " ${token.refreshToken}," +
                            " ${it.id}" +
                            " ${it.kakaoAccount?.profile?.nickname}" +
                            " ${it.kakaoAccount?.profile?.thumbnailImageUrl}",
                    )
                    viewModel.serverLogin(
                        it.id!!,
                        it.kakaoAccount?.profile?.nickname!!,
                        it.kakaoAccount?.profile?.thumbnailImageUrl!!,
                        it.kakaoAccount?.profile?.profileImageUrl!!,
                        token.accessToken,
                        token.refreshToken,
                    )
                } ?: viewModel.setErrorMessage(UiText.StringResource(R.string.unknown_error_message))
            }

            else -> viewModel.setErrorMessage(UiText.StringResource(R.string.unknown_error_message))
        }
    }

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is LoginUiEvent.KakaoLogin -> {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    UserApiClient.instance.loginWithKakaoTalk(context, callback = kakaoCallback)
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
                }
            }

            is LoginUiEvent.ShowToast -> Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
            is LoginUiEvent.NavigateToMain -> navigateToMain()
            is LoginUiEvent.NavigateToPersonalization -> navigateToPersonalization()
        }
    }

    LoginScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun LoginScreen(
    uiState: LoginUiState,
    onAction: (LoginUiAction) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            TripmateButton(
                onClick = {
                    onAction(LoginUiAction.OnLoginButtonClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(start = 20.dp, end = 20.dp, bottom = 34.dp)
                    .height(60.dp),
                containerColor = Kakao,
                contentColor = Gray001,
                text = {
                    Text(
                        text = stringResource(id = R.string.kakao_login),
                        fontSize = 18.sp,
                        style = Medium16_SemiBold,
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_kakao),
                        contentDescription = "Kakao Icon",
                        tint = Color.Unspecified,
                    )
                },
            )
        }
        if (uiState.isLoading) {
            LoadingWheel(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@DevicePreview
@Composable
private fun LoginScreenPreview() {
    TripmateTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onAction = {},
        )
    }
}
