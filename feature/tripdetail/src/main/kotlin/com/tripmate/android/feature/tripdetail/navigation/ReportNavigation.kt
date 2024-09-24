package com.tripmate.android.feature.tripdetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tripmate.android.feature.tripdetail.ReportRoute

const val REPORT_ROUTE = "report_route"

fun NavController.navigateToReport() {
    navigate(REPORT_ROUTE)
}

fun NavGraphBuilder.reportNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(route = REPORT_ROUTE) {
        ReportRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
