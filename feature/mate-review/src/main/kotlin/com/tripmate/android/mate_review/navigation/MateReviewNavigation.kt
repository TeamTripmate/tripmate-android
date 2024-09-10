package com.tripmate.android.mate_review.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tripmate.android.mate_review.MateReviewRoute

const val MATE_REVIEW_ROUTE = "mate_review_route"

fun NavController.navigateToMateReview() {
    navigate(MATE_REVIEW_ROUTE)
}

fun NavGraphBuilder.mateReviewNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(route = MATE_REVIEW_ROUTE) {
        MateReviewRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
