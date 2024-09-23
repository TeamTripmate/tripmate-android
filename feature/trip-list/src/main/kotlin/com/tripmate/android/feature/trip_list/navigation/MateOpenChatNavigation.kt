package com.tripmate.android.feature.trip_list.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tripmate.android.feature.trip_list.MateOpenChatRoute

const val MATE_OPEN_CHAT_ROUTE = "mate_open_chat_route"

fun NavController.navigateToMateOpenChat() {
    navigate(MATE_OPEN_CHAT_ROUTE)
}

fun NavGraphBuilder.mateOpenChatNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(route = MATE_OPEN_CHAT_ROUTE) {
        MateOpenChatRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
