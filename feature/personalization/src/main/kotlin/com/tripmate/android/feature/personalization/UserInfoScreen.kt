package com.tripmate.android.feature.personalization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

@Composable
fun UserInfoRoute(
    navigateToResult: () -> Unit,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToResult -> {
                navigateToResult()
            }

            else -> {}
        }
    }

    UserInfoScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}


@Composable
fun UserInfoScreen(
    uiState: PersonalizationUiState,
    onAction: (PersonalizationUiAction) -> Unit,
) {

}
