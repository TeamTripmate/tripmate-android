package com.tripmate.android.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tripmate.android.feature.mypage.MyPageRoute

const val MY_PAGE_ROUTE = "my_page_route"

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MY_PAGE_ROUTE, navOptions)
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues,
    navigateToMyTripCharacterInfo: (String, String) -> Unit,
    navigateToMyPick: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToWithdraw: () -> Unit,
    navigateToMain: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToTermOfUse: () -> Unit,
) {
    composable(route = MY_PAGE_ROUTE) {
        MyPageRoute(
            innerPadding = padding,
            navigateToMyTripCharacterInfo = navigateToMyTripCharacterInfo,
            navigateToMyPick = navigateToMyPick,
            navigateToLogin = navigateToLogin,
            navigateToWithdraw = navigateToWithdraw,
            navigateToMain = navigateToMain,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy,
            navigateToTermOfUse = navigateToTermOfUse,
        )
    }
}
