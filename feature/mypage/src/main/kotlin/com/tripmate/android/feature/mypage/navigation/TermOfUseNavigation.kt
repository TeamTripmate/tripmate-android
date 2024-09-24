package com.tripmate.android.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tripmate.android.feature.mypage.MyPageRoute
import com.tripmate.android.feature.mypage.TermOfUseRoute

const val TERM_OF_USE_ROUTE = "term_of_use_route"

fun NavController.navigateToMyPage() {
    navigate(TERM_OF_USE_ROUTE)
}

fun NavGraphBuilder.termOfUseNavGraph(
    popBackStack: () -> Unit,
) {
    composable(route = MY_PAGE_ROUTE) {
        TermOfUseRoute(
            popBackStack = popBackStack,
        )
    }
}
