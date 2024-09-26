package com.tripmate.android.feature.mate_recruit.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tripmate.android.feature.mate_recruit.MateRecruitRoute

const val SPOT_ID = "spot_Id"
const val MATE_RECRUIT_ROUTE = "mate_recruit_route/{${SPOT_ID}}"

fun NavController.navigateToMateRecruit(spotId: String) {
    navigate("mate_recruit_route/$spotId")
}

fun NavGraphBuilder.mateRecruitNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(
        route = MATE_RECRUIT_ROUTE,
        arguments = listOf(
            navArgument(SPOT_ID) {
                type = NavType.StringType
            },
        ),
    ) {
        MateRecruitRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
