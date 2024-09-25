package com.tripmate.android.feature.mate_recruit_post.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tripmate.android.feature.mate_recruit_post.MateRecruitPostRoute

const val COMPANION_ID = "companion_dd"
const val MATE_RECRUIT_POST_ROUTE = "mate_recruit_post_route/{$COMPANION_ID}"

fun NavController.navigateToMateRecruitPost(companionId: Int) {
    navigate("mate_recruit_post_route/$companionId")
}

fun NavGraphBuilder.mateRecruitPostNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(
        route = MATE_RECRUIT_POST_ROUTE,
        arguments = listOf(
            navArgument(COMPANION_ID) {
                type = NavType.IntType
            },
        ),
    ) {
        MateRecruitPostRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
