package com.tripmate.android.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tripmate.android.feature.mypage.MyPageRoute

const val MYPAGE_ROUTE = "mypage_route"

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MYPAGE_ROUTE, navOptions)
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues,
) {
    composable(route = MYPAGE_ROUTE) {
        MyPageRoute(
            innerPadding = padding,
        )
    }
}
