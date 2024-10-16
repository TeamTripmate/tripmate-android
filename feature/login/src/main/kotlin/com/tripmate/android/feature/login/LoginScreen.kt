package com.tripmate.android.feature.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.tripmate.android.core.designsystem.R as designSystemR
import com.tripmate.android.core.designsystem.component.LoadingIndicator
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.feature.login.viewmodel.LoginUiAction
import com.tripmate.android.feature.login.viewmodel.LoginUiEvent
import com.tripmate.android.feature.login.viewmodel.LoginViewModel
import tech.thdev.compose.exteions.system.ui.controller.rememberExSystemUiController
import timber.log.Timber

@Composable
internal fun LoginRoute(
    innerPadding: PaddingValues,
    navigateToMain: () -> Unit,
    navigateToPersonalization: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val systemUiController = rememberExSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()

    DisposableEffect(systemUiController) {
        systemUiController.setSystemBarsColor(
            color = Primary01,
            darkIcons = true,
            isNavigationBarContrastEnforced = false,
        )

        onDispose {
            systemUiController.setSystemBarsColor(
                color = if (isDarkTheme) Gray001 else Color.White,
                darkIcons = !isDarkTheme,
                isNavigationBarContrastEnforced = false,
            )
        }
    }

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
        innerPadding = innerPadding,
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun LoginScreen(
    innerPadding: PaddingValues,
    uiState: LoginUiState,
    onAction: (LoginUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary01)
            .padding(innerPadding),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(81.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 48.dp),
            ) {
                AppIcon()
                Spacer(modifier = Modifier.height(44.dp))
                AppDescription()
            }
            Spacer(modifier = Modifier.weight(1f))
            TripmateButton(
                onClick = {
                    onAction(LoginUiAction.OnLoginButtonClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(start = 20.dp, end = 20.dp)
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
                        imageVector = ImageVector.vectorResource(id = designSystemR.drawable.ic_kakao),
                        contentDescription = "Kakao Icon",
                        tint = Color.Unspecified,
                    )
                },
            )
        }
        LoadingIndicator(
            isLoading = uiState.isLoading,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun AppIcon() {
    Image(
        painter = painterResource(id = R.drawable.img_tripmate_icon),
        contentDescription = "App Icon",
    )
}

@Composable
fun AppDescription() {
    Text(
        text = "I 에게도 액티비티가\n필요한 순간이 있다!\n\nE 에게도 힐링이\n필요한 순간이 있다!",
        color = Color.White,
        style = Large20_Bold,
    )
}

@DevicePreview
@Composable
private fun LoginScreenPreview() {
    TripmateTheme {
        LoginScreen(
            innerPadding = PaddingValues(),
            uiState = LoginUiState(),
            onAction = {},
        )
    }
}
