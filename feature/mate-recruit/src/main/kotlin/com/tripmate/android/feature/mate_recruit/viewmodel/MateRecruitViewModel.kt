package com.tripmate.android.feature.mate_recruit.viewmodel

import android.util.Patterns
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.common.ErrorHandlerActions
import com.tripmate.android.core.common.UiText
import com.tripmate.android.core.common.handleException
import com.tripmate.android.domain.entity.GenderAgeGroupEntity
import com.tripmate.android.domain.entity.MateRecruitmentEntity
import com.tripmate.android.domain.repository.AuthRepository
import com.tripmate.android.domain.repository.MateRepository
import com.tripmate.android.feature.mate_recruit.R
import com.tripmate.android.feature.mate_recruit.navigation.SPOT_ADDRESS
import com.tripmate.android.feature.mate_recruit.navigation.SPOT_ID
import com.tripmate.android.feature.mate_recruit.navigation.SPOT_TITLE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.MalformedURLException
import java.net.URL
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MateRecruitViewModel @Inject constructor(
    private val mateRepository: MateRepository,
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), ErrorHandlerActions {
    private val spotId = requireNotNull(savedStateHandle.get<String>(SPOT_ID)) {
        "spotId is required."
    }

    private val spotTitle = requireNotNull(savedStateHandle.get<String>(SPOT_TITLE)) {
        "spotTilte is required."
    }

    private val spotAddress = requireNotNull(savedStateHandle.get<String>(SPOT_ADDRESS)) {
        "spotAddress is required."
    }

    private val _uiState = MutableStateFlow(MateRecruitUiState(
        tripLocation = spotTitle,
        tripLocationAddress = spotAddress,
    ))
    val uiState: StateFlow<MateRecruitUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MateRecruitUiEvent>()
    val uiEvent: Flow<MateRecruitUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: MateRecruitUiAction) {
        when (action) {
            is MateRecruitUiAction.OnBackClicked -> navigateBack()
            is MateRecruitUiAction.OnMateRecruitTitleUpdated -> setMateRecruitTitle(action.title)
            is MateRecruitUiAction.OnScheduleDateClicked -> setDatePickerVisible(true)
            is MateRecruitUiAction.OnScheduleTimeClicked -> setTimePickerVisible(true)
            is MateRecruitUiAction.OnScheduleDateUpdated -> setMateRecruitDate(action.date)
            is MateRecruitUiAction.OnScheduleTimeUpdated -> setMateRecruitTime(action.time)
            is MateRecruitUiAction.OnDismiss -> {
                when (action.pickerType) {
                    PickerType.DATE -> setDatePickerVisible(false)
                    PickerType.TIME -> setTimePickerVisible(false)
                }
            }

            is MateRecruitUiAction.OnMateTypeSelected -> setMateType(action.mateType)
            is MateRecruitUiAction.OnGenderAgeGroupSelected -> addGenderAgeGroup(action.group)
            is MateRecruitUiAction.OnGenderAgeGroupDeselected -> removeGenderAgeGroup(action.group)
            is MateRecruitUiAction.OnMateRecruitContentUpdated -> setMateRecruitContent(action.content)
            is MateRecruitUiAction.OnOpenKakaoLinkUpdated -> setOpenKakaoLink(action.link)
            is MateRecruitUiAction.OnDoneClicked -> createCompanionRecruitment()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(MateRecruitUiEvent.NavigateBack)
        }
    }

    private fun setMateRecruitTitle(title: String) {
        _uiState.update { it.copy(mateRecruitTitle = title) }
    }

    private fun setTimePickerVisible(flag: Boolean) {
        _uiState.update { it.copy(isTimePickerVisible = flag) }
    }

    private fun setOpenKakaoLink(link: String) {
        _uiState.update { it.copy(openKakaoLink = link) }
        if (!isValidUrl(link)) {
            _uiState.update { it.copy(openKakaoLinkErrorText = UiText.StringResource(R.string.open_kakao_link_error_text)) }
        } else {
            _uiState.update { it.copy(openKakaoLinkErrorText = null) }
        }
    }

    private fun isValidUrl(urlString: String): Boolean {
        return try {
            // 안드로이드의 Patterns 클래스를 사용하여 URL 형식 검사
            val urlPattern = Patterns.WEB_URL
            if (!urlPattern.matcher(urlString).matches()) {
                return false
            }

            // URL 객체 생성을 통한 추가 검증
            val url = URL(urlString)
            val protocol = url.protocol
            if (protocol != "http" && protocol != "https") {
                return false
            }
            true
        } catch (e: MalformedURLException) {
            false
        }
    }

    private fun setMateRecruitContent(content: String) {
        _uiState.update { it.copy(mateRecruitContent = content) }
    }

    private fun setMateRecruitDate(date: String) {
        _uiState.update {
            it.copy(
                mateRecruitDate = date,
                isMateRecruitDateUpdated = true,
            )
        }
    }

    private fun setMateRecruitTime(time: String) {
        _uiState.update {
            it.copy(
                mateRecruitTime = time,
                isMateRecruitTimeUpdated = true,
            )
        }
    }

    private fun setMateType(mateType: MateType) {
        _uiState.update { it.copy(selectedMateType = mateType) }
    }

    private fun addGenderAgeGroup(genderAgeGroup: GenderAgeGroupEntity) {
        _uiState.update {
            it.copy(selectedGenderAgeGroups = it.selectedGenderAgeGroups.add(genderAgeGroup))
        }
    }

    private fun removeGenderAgeGroup(genderAgeGroup: GenderAgeGroupEntity) {
        _uiState.update {
            it.copy(selectedGenderAgeGroups = it.selectedGenderAgeGroups.remove(genderAgeGroup))
        }
    }

    private fun setDatePickerVisible(flag: Boolean) {
        _uiState.update { it.copy(isDatePickerVisible = flag) }
    }

    private fun createCompanionRecruitment() {
        viewModelScope.launch {
            mateRepository.createCompanionRecruitment(
                MateRecruitmentEntity(
                    spotId = spotId.toInt(),
                    date = getCurrentUTCDateTime(),
                    title = _uiState.value.mateRecruitTitle,
                    description = _uiState.value.mateRecruitContent,
                    type = if (uiState.value.selectedMateType == MateType.ALL) "ALL" else "SIMILAR",
                    sameGenderYn = _uiState.value.allGenderAgeGroups[0].isSelected,
                    sameAgeYn = _uiState.value.allGenderAgeGroups[1].isSelected,
                    openChatLink = _uiState.value.openKakaoLink,
                    creatorId = authRepository.getId(),
                ),
            ).onSuccess {
                _uiEvent.send(MateRecruitUiEvent.Finish)
            }.onFailure { exception ->
                handleException(exception, this@MateRecruitViewModel)
            }
        }
    }

    private fun getCurrentUTCDateTime(): String {
        val seoulZoneId = ZoneId.of("Asia/Seoul")
        val combinedDateTime = "${_uiState.value.mateRecruitDate} ${_uiState.value.mateRecruitTime}"

        val koreanFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a h시 mm분", Locale.KOREAN)
        val localDateTime = LocalDateTime.parse(combinedDateTime, koreanFormatter)

        val utcZoneId = ZoneId.of("UTC")
        val utcZonedDateTime = localDateTime.atZone(seoulZoneId).withZoneSameInstant(utcZoneId)

        return utcZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
    }

    override fun setServerErrorDialogVisible(flag: Boolean) {
        //
    }

    override fun setNetworkErrorDialogVisible(flag: Boolean) {
        //
    }
}
