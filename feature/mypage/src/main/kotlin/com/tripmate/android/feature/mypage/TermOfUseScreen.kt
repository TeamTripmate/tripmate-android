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
internal fun TermOfUseRoute(
    popBackStack: () -> Unit,
) {
    TermOfUseScreen(
        onCloseClick = popBackStack,
        termOfUseUrl = "https://bubbly-stoplight-4e6.notion.site/Trip-Mate-1155df692efb45feaa6016e5458457a5?pvs=4",
    )
}

@Composable
internal fun TermOfUseScreen(
    onCloseClick: () -> Unit,
    termOfUseUrl: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        TermOfUseTopAppBar(onCloseClick = onCloseClick)
        TermOfUseContent(termOfUseUrl = termOfUseUrl)
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
internal fun TermOfUseContent(
    termOfUseUrl: String,
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
                    loadUrl(termOfUseUrl)
                }
            },
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
internal fun TermOfUseTopAppBar(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TripmateTopAppBar(
        navigationType = TopAppBarNavigationType.Back,
        title = stringResource(R.string.term_of_use),
        onNavigationClick = onCloseClick,
        navigationIconContentDescription = null,
        modifier = modifier
            .statusBarsPadding()
            .height(56.dp),
    )
}
