package com.tripmate.android.feature.trip_list.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tripmate.android.feature.trip_list.MateListRoute

const val COMPANION_ID = "companion_id"
const val PAGE = "page"
const val MATE_LIST_ROUTE = "mate_list_route/{$COMPANION_ID}/{$PAGE}"

fun NavController.navigateToMateList(
    companionId: Long,
    page: Int,
) {
    navigate("mate_list_route/$companionId/$page")
}

fun NavGraphBuilder.mateListNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToCharacterDescription: (String, String, String, String) -> Unit,
) {
    composable(
        route = MATE_LIST_ROUTE,
        arguments = listOf(
            navArgument(COMPANION_ID) {
                type = NavType.LongType
            },
            navArgument(PAGE) {
                type = NavType.IntType
            },
        ),
    ) {
        MateListRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
            navigateToCharacterDescription = navigateToCharacterDescription,
        )
    }
}
