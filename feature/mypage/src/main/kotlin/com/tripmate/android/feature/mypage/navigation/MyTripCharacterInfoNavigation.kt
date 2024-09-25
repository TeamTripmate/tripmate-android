package com.tripmate.android.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tripmate.android.feature.mypage.MyTripCharacterInfoRoute

const val CHARACTER_ID = "character_id"
const val TRIP_STYLE = "trip_style"
const val MY_TRIP_CHARACTER_INFO_ROUTE = "my_trip_character_info_route/{$CHARACTER_ID}/{$TRIP_STYLE}"

fun NavController.navigateToMyTripCharacterInfo(
    characterId: String,
    tripStyle: String,
) {
    navigate("my_trip_character_info_route/$characterId/$tripStyle")
}

fun NavGraphBuilder.myTripCharacterInfoNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToPersonalization: () -> Unit,
) {
    composable(
        route = MY_TRIP_CHARACTER_INFO_ROUTE,
        arguments = listOf(
            navArgument(CHARACTER_ID) {
                type = NavType.StringType
            },
            navArgument(TRIP_STYLE) {
                type = NavType.StringType
            },
        ),
    ) {
        MyTripCharacterInfoRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
            navigateToPersonalization = navigateToPersonalization,
        )
    }
}
