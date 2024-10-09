package com.tripmate.android.feature.trip_list.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tripmate.android.feature.trip_list.MateOpenChatRoute

const val OPEN_CHAT_LINK = "openChatLink"
const val SELECTED_KEYWORD1 = "selectedKeyword1"
const val SELECTED_KEYWORD2 = "selectedKeyword2"
const val SELECTED_KEYWORD3 = "selectedKeyword3"
const val TRIP_STYLE = "tripStyle"
const val CHARACTER_ID = "characterId"
const val COMPANION_ID2 = "companionId2"
const val MATE_OPEN_CHAT_ROUTE = "mate_open_chat_route/{$OPEN_CHAT_LINK}/{$SELECTED_KEYWORD1}/{$SELECTED_KEYWORD2}/{$SELECTED_KEYWORD3}/{$TRIP_STYLE}/{$CHARACTER_ID}/{$COMPANION_ID2}"

fun NavController.navigateToMateOpenChat(
    companionId: Long,
    openChatLink: String,
    selectedKeyword1: String,
    selectedKeyword2: String,
    selectedKeyword3: String,
    tripStyle: String,
    characterId: String,
) {
    val encodedCompanionId = Uri.encode(companionId.toString())
    val encodedOpenChatLink = Uri.encode(openChatLink)
    val encodedSelectedKeyword1 = Uri.encode(selectedKeyword1)
    val encodedSelectedKeyword2 = Uri.encode(selectedKeyword2)
    val encodedSelectedKeyword3 = Uri.encode(selectedKeyword3)
    val encodedTripStyle = Uri.encode(tripStyle)
    val encodedCharacterId = Uri.encode(characterId)

    val route = "mate_open_chat_route/" +
        "$encodedOpenChatLink/" +
        "$encodedSelectedKeyword1/" +
        "$encodedSelectedKeyword2/" +
        "$encodedSelectedKeyword3/" +
        "$encodedTripStyle/" +
        "$encodedCharacterId/" +
        "$encodedCompanionId"

    navigate(route)
}

fun NavGraphBuilder.mateOpenChatNavGraph(
    navigateToDetailScreen: (Long) -> Unit,
    popBackStack: () -> Unit,
) {
    composable(
        route = MATE_OPEN_CHAT_ROUTE,
        arguments = listOf(
            navArgument(OPEN_CHAT_LINK) { type = NavType.StringType },
            navArgument(SELECTED_KEYWORD1) { type = NavType.StringType },
            navArgument(SELECTED_KEYWORD2) { type = NavType.StringType },
            navArgument(SELECTED_KEYWORD3) { type = NavType.StringType },
            navArgument(TRIP_STYLE) { type = NavType.StringType },
            navArgument(CHARACTER_ID) { type = NavType.StringType },
            navArgument(COMPANION_ID2) { type = NavType.LongType },
        ),
    ) { backStackEntry ->
        val companionId = backStackEntry.arguments?.getLong(COMPANION_ID2) ?: 0
        val openChatLink = backStackEntry.arguments?.getString(OPEN_CHAT_LINK)?.let { Uri.decode(it) } ?: ""
        val selectedKeyword1 = backStackEntry.arguments?.getString(SELECTED_KEYWORD1)?.let { Uri.decode(it) } ?: ""
        val selectedKeyword2 = backStackEntry.arguments?.getString(SELECTED_KEYWORD2)?.let { Uri.decode(it) } ?: ""
        val selectedKeyword3 = backStackEntry.arguments?.getString(SELECTED_KEYWORD3)?.let { Uri.decode(it) } ?: ""
        val tripStyle = backStackEntry.arguments?.getString(TRIP_STYLE)?.let { Uri.decode(it) } ?: ""
        val characterId = backStackEntry.arguments?.getString(CHARACTER_ID)?.let { Uri.decode(it) } ?: ""

        MateOpenChatRoute(
            popBackStack = popBackStack,
            openChatLink = openChatLink,
            selectedKeywords = listOf(selectedKeyword1, selectedKeyword2, selectedKeyword3),
            tripStyle = tripStyle,
            characterId = characterId,
            companionId = companionId,
            navigateToDetailScreen = navigateToDetailScreen,
        )
    }
}
