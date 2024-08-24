package com.tripmate.android.notification.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tripmate.android.notification.NotificationRoute

const val NOTIFICATION_ROUTE = "notification_route"

fun NavController.navigateToNotification(navOptions: NavOptions) {
    navigate(NOTIFICATION_ROUTE, navOptions)
}

fun NavGraphBuilder.notificationNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit
) {
    composable(route = NOTIFICATION_ROUTE) {
        NotificationRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
