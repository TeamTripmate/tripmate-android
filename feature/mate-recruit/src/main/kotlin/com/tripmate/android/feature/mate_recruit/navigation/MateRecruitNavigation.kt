package com.tripmate.android.feature.mate_recruit.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tripmate.android.feature.mate_recruit.MateRecruitRoute

const val SPOT_ID = "spot_Id"
const val SPOT_TITLE = "spot_title"
const val SPOT_ADDRESS = "spot_address"
const val MATE_RECRUIT_ROUTE = "mate_recruit_route/{$SPOT_ID}/{$SPOT_TITLE}/{$SPOT_ADDRESS}"

fun NavController.navigateToMateRecruit(
    spotId: String,
    spotTitle: String,
    spotAddress: String,
) {
    val title = spotTitle.ifBlank { "No Title" }
    val address = spotAddress.ifBlank { "No Address" }
    navigate("mate_recruit_route/$spotId/$title/$address")
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
            navArgument(SPOT_TITLE) {
                type = NavType.StringType
            },
            navArgument(SPOT_ADDRESS) {
                type = NavType.StringType
            }
        ),
    ) {
        MateRecruitRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
