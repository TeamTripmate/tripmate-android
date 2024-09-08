package com.tripmate.android.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tripmate.android.feature.mypage.MyTripCharacterInfoRoute

const val CHARACTER_ID = "character_id"
const val MY_TRIP_CHARACTER_INFO_ROUTE = "my_trip_character_info_route/{$CHARACTER_ID}"

fun NavController.navigateToMyTripCharacterInfo(characterId: Long) {
    navigate("my_trip_character_info_route/$characterId")
}

fun NavGraphBuilder.myTripCharacterInfoNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(
        route = MY_TRIP_CHARACTER_INFO_ROUTE,
        arguments = listOf(
            navArgument(CHARACTER_ID) {
                type = NavType.LongType
            },
        ),
    ) {
        MyTripCharacterInfoRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
