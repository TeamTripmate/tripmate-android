package com.tripmate.android.feature.personalization.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tripmate.android.core.common.extension.sharedViewModel
import com.tripmate.android.feature.personalization.Question1Route
import com.tripmate.android.feature.personalization.Question2Route
import com.tripmate.android.feature.personalization.Question3Route
import com.tripmate.android.feature.personalization.Question4Route
import com.tripmate.android.feature.personalization.ResultRoute
import com.tripmate.android.feature.personalization.TripStyleRoute
import com.tripmate.android.feature.personalization.UserInfoRoute
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

const val PERSONALIZATION_ROUTE = "personalization_route"
const val Question1_ROUTE = "question1_route"
const val Question2_ROUTE = "question2_route"
const val Question3_ROUTE = "question3_route"
const val Question4_ROUTE = "question4_route"
const val TRIP_STYLE_ROUTE = "trip_style_route"
const val USER_INFO_ROUTE = "user_info_route"
const val RESULT_ROUTE = "result_route"

fun NavController.navigateToPersonalization() {
    navigate(PERSONALIZATION_ROUTE)
}

fun NavGraphBuilder.personalizationNavGraph(
    innerPadding: PaddingValues,
    navController: NavHostController,
    navigateToQuestion2: () -> Unit,
    navigateToQuestion3: () -> Unit,
    navigateToQuestion4: () -> Unit,
    navigateToTripStyle: () -> Unit,
    navigateToUserInfo: () -> Unit,
    navigateToResult: () -> Unit,
    navigateToMain: () -> Unit,
) {
    navigation(
        startDestination = Question1_ROUTE,
        route = PERSONALIZATION_ROUTE,
    ) {
        composable(route = Question1_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            Question1Route(
                navigateToQuestion2 = navigateToQuestion2,
                innerPadding = innerPadding,
                viewModel = viewModel,
            )
        }
        composable(route = Question2_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            Question2Route(
                navigateToQuestion3 = navigateToQuestion3,
                innerPadding = innerPadding,
                viewModel = viewModel,
            )
        }

        composable(route = Question3_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            Question3Route(
                navigateToQuestion4 = navigateToQuestion4,
                innerPadding = innerPadding,
                viewModel = viewModel,
            )
        }

        composable(route = Question4_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            Question4Route(
                navigateToTripStyle = navigateToTripStyle,
                innerPadding = innerPadding,
                viewModel = viewModel,
            )
        }

        composable(route = TRIP_STYLE_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            TripStyleRoute(
                navigateToUserInfo = navigateToUserInfo,
                innerPadding = innerPadding,
                viewModel = viewModel,
            )
        }

        composable(route = USER_INFO_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            UserInfoRoute(
                navigateToResult = navigateToResult,
                innerPadding = innerPadding,
                viewModel = viewModel,
            )
        }

        composable(route = RESULT_ROUTE) { entry ->
            val viewModel = entry.sharedViewModel<PersonalizationViewModel>(navController)
            ResultRoute(
                navigateToMain = navigateToMain,
                innerPadding = innerPadding,
                viewModel = viewModel,
            )
        }
    }
}
