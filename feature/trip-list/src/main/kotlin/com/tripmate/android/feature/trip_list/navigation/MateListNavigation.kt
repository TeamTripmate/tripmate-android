package com.tripmate.android.feature.trip_list.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tripmate.android.feature.trip_list.MateListRoute

const val MATE_LIST_ROUTE = "mate_list_route"

fun NavController.navigateToMateList() {
    navigate(MATE_LIST_ROUTE)
}

fun NavGraphBuilder.mateListNavGraph(
    padding: PaddingValues,
//    popBackStack: () -> Unit
) {
    composable(route = MATE_LIST_ROUTE) {
        MateListRoute(
            innerPadding = padding,
        )
    }
}
