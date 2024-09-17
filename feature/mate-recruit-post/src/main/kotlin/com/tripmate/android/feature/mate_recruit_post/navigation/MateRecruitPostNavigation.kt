package com.tripmate.android.feature.mate_recruit_post.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tripmate.android.feature.mate_recruit_post.MateRecruitPostRoute

const val MATE_RECRUIT_POST_ROUTE = "mate_recruit_post_route"

fun NavController.navigateToMateRecruitPost() {
    navigate(MATE_RECRUIT_POST_ROUTE)
}

fun NavGraphBuilder.mateRecruitPostNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(route = MATE_RECRUIT_POST_ROUTE) {
        MateRecruitPostRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
