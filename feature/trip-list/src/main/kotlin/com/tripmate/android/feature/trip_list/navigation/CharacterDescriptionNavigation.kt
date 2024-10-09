package com.tripmate.android.feature.trip_list.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tripmate.android.feature.trip_list.CharacterRoute

const val CHARACTER = "character"
const val TAG1 = "tag1"
const val TAG2 = "tag2"
const val TAG3 = "tag3"
const val CHARACTER_ROUTE = "character_route/{$CHARACTER}/{$TAG1}/{$TAG2}/{$TAG3}"

fun NavController.navigateToCharacter(characterId: String, tag1: String, tag2: String, tag3: String) {
    navigate("character_route/$characterId/$tag1/$tag2/$tag3")
}

fun NavGraphBuilder.characterNavGraph(
    padding: PaddingValues,
) {
    composable(
        route = CHARACTER_ROUTE,
        arguments = listOf(
            navArgument(CHARACTER) {
                type = NavType.StringType
            },
            navArgument(TAG1) {
                type = NavType.StringType
            },
            navArgument(TAG2) {
                type = NavType.StringType
            },
            navArgument(TAG3) {
                type = NavType.StringType
            },
        ),
    ) { backStackEntry ->
        val characterId = backStackEntry.arguments?.getString(CHARACTER) ?: ""
        val tag1 = backStackEntry.arguments?.getString(TAG1) ?: ""
        val tag2 = backStackEntry.arguments?.getString(TAG2) ?: ""
        val tag3 = backStackEntry.arguments?.getString(TAG3) ?: ""

        CharacterRoute(
            innerPadding = padding,
            characterId = characterId,
            tag1 = tag1,
            tag2 = tag2,
            tag3 = tag3,
        )
    }
}
