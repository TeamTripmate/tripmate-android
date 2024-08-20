package com.tripmate.android.writing.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tripmate.android.writing.WritingRoute

const val WRITING_ROUTE = "writing_route"

fun NavController.navigateToWriting(navOptions: NavOptions) {
    navigate(WRITING_ROUTE, navOptions)
}

fun NavGraphBuilder.writingNavGraph(
    padding: PaddingValues,
//    popBackStack: () -> Unit
) {
    composable(route = WRITING_ROUTE) {
        WritingRoute(
            innerPadding = padding,
        )
    }
}
