package com.tripmate.android.feature.personalization.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tripmate.android.core.common.extension.sharedViewModel
import com.tripmate.android.feature.personalization.Q1Route
import com.tripmate.android.feature.personalization.Q2Route
import com.tripmate.android.feature.personalization.Q3Route
import com.tripmate.android.feature.personalization.Q4Route
import com.tripmate.android.feature.personalization.TripStyleRoute
import com.tripmate.android.feature.personalization.UserInfoRoute
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

const val PERSONALIZATION_ROUTE = "personalization_route"
const val Q1_ROUTE = "q1_route"
const val Q2_ROUTE = "q2_route"
const val Q3_ROUTE = "q3_route"
const val Q4_ROUTE = "q3_route"
const val TRIP_STYLE_ROUTE = "trip_style_route"
const val USER_INFO_ROUTE = "user_info_route"
const val RESULT_ROUTE = "result_route"

fun NavController.navigateToPersonalization() {
    navigate("booth_route")
}

fun NavGraphBuilder.personalizationNavGraph(
    padding: PaddingValues,
    navController: NavHostController,
    navigateToQ2: () -> Unit,
    navigateToQ3: () -> Unit,
    navigateToQ4: () -> Unit,
    navigateToTripStyle: () -> Unit,
    navigateToUserInfo: () -> Unit,
    navigateToResult: () -> Unit,
) {
    navigation(
        startDestination = Q1_ROUTE,
        route = PERSONALIZATION_ROUTE,
    ) {
        composable(route = Q1_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            Q1Route(
                navigateToQ2 = navigateToQ2,
                viewModel = viewModel,
            )
        }
        composable(route = Q2_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            Q2Route(
                navigateToQ3 = navigateToQ3,
                viewModel = viewModel,
            )
        }

        composable(route = Q3_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            Q3Route(
                navigateToQ4 = navigateToQ4,
                viewModel = viewModel,
            )
        }

        composable(route = Q4_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            Q4Route(
                navigateToTripStyle = navigateToTripStyle,
                viewModel = viewModel,
            )
        }

        composable(route = TRIP_STYLE_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            TripStyleRoute(
                navigateToUserInfo = navigateToUserInfo,
                viewModel = viewModel,
            )
        }

        composable(route = USER_INFO_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            UserInfoRoute(
                navigateToResult = navigateToResult,
                viewModel = viewModel,
            )
        }
    }
}
