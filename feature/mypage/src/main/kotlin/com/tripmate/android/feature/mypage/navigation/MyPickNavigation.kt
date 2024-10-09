package com.tripmate.android.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tripmate.android.feature.mypage.MyPickRoute
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiEvent

const val MY_PICK_ROUTE = "my_pick_route"

fun NavController.navigateToMyPick() {
    navigate(MY_PICK_ROUTE)
}

fun NavGraphBuilder.myPickNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToTripDetail: (String) -> Unit,
) {
    composable(route = MY_PICK_ROUTE) {
        MyPickRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
            navigateToTripDetail = navigateToTripDetail,
        )
    }
}
