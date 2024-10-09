package com.tripmate.android.mate_review.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tripmate.android.mate_review.MateReviewRoute

const val COMPANION_ID = "companion_id"
const val TITLE = "title"
const val DATE = "date"
const val MATE_REVIEW_ROUTE = "mate_review_route/{$COMPANION_ID}/{$TITLE}/{$DATE}"

fun NavController.navigateToMateReview(companionId: Long, title: String, date: String) {
    navigate("mate_review_route/$companionId/$title/$date")
}

fun NavGraphBuilder.mateReviewNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(
        route = MATE_REVIEW_ROUTE,
        arguments = listOf(
            navArgument(COMPANION_ID) {
                type = NavType.LongType
            },
            navArgument(TITLE) {
                type = NavType.StringType
            },
            navArgument(DATE) {
                type = NavType.StringType
            },
        ),
    ) {
        MateReviewRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
