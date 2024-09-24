package com.tripmate.android.feature.mypage

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar

@Composable
internal fun PrivacyPolicyRoute(
    popBackStack: () -> Unit,
) {
    PrivacyPolicyScreen(
        onCloseClick = popBackStack,
        privacyPolicyUrl = "http://bubbly-stoplight-4e6.notion.site",
    )
}

@Composable
internal fun PrivacyPolicyScreen(
    onCloseClick: () -> Unit,
    privacyPolicyUrl: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        PrivacyPolicyTopAppBar(onCloseClick = onCloseClick)
        PrivacyPolicyContent(privacyPolicyUrl = privacyPolicyUrl)
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
internal fun PrivacyPolicyContent(
    privacyPolicyUrl: String,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    webViewClient = WebViewClient()
                    webChromeClient = WebChromeClient()
                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        loadWithOverviewMode = true
                        useWideViewPort = true
                    }
                    loadUrl(privacyPolicyUrl)
                }
            },
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
internal fun PrivacyPolicyTopAppBar(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TripmateTopAppBar(
        navigationType = TopAppBarNavigationType.Back,
        title = stringResource(R.string.privacy_policy) ,
        onNavigationClick = onCloseClick,
        navigationIconContentDescription = null,
        modifier = modifier
            .statusBarsPadding()
            .height(56.dp),
    )
}
