package com.tripmate.android.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tripmate.android.feature.mypage.MypageRoute

const val MYPAGE_ROUTE = "mypage_route"

fun NavController.navigateToMypage(navOptions: NavOptions) {
    navigate(MYPAGE_ROUTE, navOptions)
}

fun NavGraphBuilder.mypageNavGraph(
    padding: PaddingValues,
//    popBackStack: () -> Unit
) {
    composable(route = MYPAGE_ROUTE) {
        MypageRoute(
            padding = padding,
        )
    }
}
