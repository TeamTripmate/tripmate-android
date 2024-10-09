package com.tripmate.android.mate_review.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.entity.BadReviewEntity
import com.tripmate.android.domain.entity.GoodReviewEntity
import com.tripmate.android.domain.repository.MateRepository
import com.tripmate.android.mate_review.navigation.COMPANION_ID
import com.tripmate.android.mate_review.navigation.DATE
import com.tripmate.android.mate_review.navigation.TITLE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MateReviewViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val mateRepository: MateRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val companionId: Int = requireNotNull(savedStateHandle.get<Int>(COMPANION_ID))
    private val date: String = requireNotNull(savedStateHandle.get<String>(DATE))
    private val title: String = requireNotNull(savedStateHandle.get<String>(TITLE))

    private val _uiState = MutableStateFlow(MateReviewUiState(
        companionId = companionId,
        mateReviewTitle = title,
        tripDate = date,
    ))
    val uiState: StateFlow<MateReviewUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MateReviewUiEvent>()
    val uiEvent: Flow<MateReviewUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: MateReviewUiAction) {
        when (action) {
            is MateReviewUiAction.OnBackClicked -> navigateBack()
            is MateReviewUiAction.OnGoodReviewSelected -> addGoodReview(action.review)
            is MateReviewUiAction.OnGoodReviewDeselected -> removeGoodReview(action.review)
            is MateReviewUiAction.OnBadReviewSelected -> addBadReview(action.review)
            is MateReviewUiAction.OnBadReviewDeselected -> removeBadReview(action.review)
            is MateReviewUiAction.OnDoneClicked -> finish()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(MateReviewUiEvent.NavigateBack)
        }
    }

    private fun addGoodReview(goodReview: GoodReviewEntity) {
        if (_uiState.value.selectedGoodReviews.size >= 3) {
            return
        }

        _uiState.update {
            it.copy(selectedGoodReviews = it.selectedGoodReviews.add(goodReview))
        }
    }

    private fun removeGoodReview(goodReview: GoodReviewEntity) {
        _uiState.update {
            it.copy(selectedGoodReviews = it.selectedGoodReviews.remove(goodReview))
        }
    }

    private fun addBadReview(badReview: BadReviewEntity) {
        if (_uiState.value.selectedBadReviews.size >= 3) {
            return
        }

        _uiState.update {
            it.copy(selectedBadReviews = it.selectedBadReviews.add(badReview))
        }
    }

    private fun removeBadReview(badReview: BadReviewEntity) {
        _uiState.update {
            it.copy(selectedBadReviews = it.selectedBadReviews.remove(badReview))
        }
    }

    private fun finish() {
        viewModelScope.launch {
            _uiEvent.send(MateReviewUiEvent.Finish)
        }
    }
}
