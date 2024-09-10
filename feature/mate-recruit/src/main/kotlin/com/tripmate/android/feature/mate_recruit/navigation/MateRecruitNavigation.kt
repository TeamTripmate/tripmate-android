package com.tripmate.android.feature.mate_recruit.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tripmate.android.feature.mate_recruit.MateRecruitRoute

const val MATE_RECRUIT_ROUTE = "mate_recruit_route"

fun NavController.navigateToMateRecruit() {
    navigate(MATE_RECRUIT_ROUTE)
}

fun NavGraphBuilder.mateRecruitNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(route = MATE_RECRUIT_ROUTE) { entry ->
        MateRecruitRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
