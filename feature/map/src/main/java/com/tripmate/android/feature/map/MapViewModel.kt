package com.tripmate.android.feature.map

import dagger.hilt.android.lifecycle.HiltViewModel
import io.hlab.sample.app.presentation.base.StateViewModel
import io.hlab.sample.app.presentation.base.ViewModelState
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : StateViewModel<MapState>(initialState = MapState()) {

    fun toggleBarShowingState() = withState { state ->
        setState { copy(isMenuBarShowing = !state.isMenuBarShowing) }
    }
}

data class MapState(
    val isMenuBarShowing: Boolean = true,
) : ViewModelState
