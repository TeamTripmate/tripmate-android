package com.tripmate.android.feature.personalization.viewmodel

import com.tripmate.android.core.common.UiText
import com.tripmate.android.domain.entity.TripStyleEntity
import com.tripmate.android.feature.personalization.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class PersonalizationUiState(
    val question1Answer: Int = 0,
    val question2Answer: Int = 0,
    val question3Answer: Int = 0,
    val question4Answer: Int = 0,
    val allTripStyles: ImmutableList<TripStyleEntity> = persistentListOf(
        TripStyleEntity(1, R.string.trip_style_1, R.drawable.img_shopping_bags, false),
        TripStyleEntity(2, R.string.trip_style_2, R.drawable.img_running_shoe, false),
        TripStyleEntity(3, R.string.trip_style_3, R.drawable.img_statue_of_liberty, false),
        TripStyleEntity(4, R.string.trip_style_4, R.drawable.img_diving_mask, false),
        TripStyleEntity(5, R.string.trip_style_5, R.drawable.img_mobile_phone, false),
        TripStyleEntity(6, R.string.trip_style_6, R.drawable.img_compass, false),
        TripStyleEntity(7, R.string.trip_style_7, R.drawable.img_backpack, false),
        TripStyleEntity(8, R.string.trip_style_8, R.drawable.img_camera_with_flash, false),
        TripStyleEntity(9, R.string.trip_style_9, R.drawable.img_framed_picture, false),
        TripStyleEntity(10, R.string.trip_style_10, R.drawable.img_pot_of_food, false),
        TripStyleEntity(11, R.string.trip_style_11, R.drawable.img_deciduous_tree, false),
        TripStyleEntity(12, R.string.trip_style_12, R.drawable.img_ferris_wheel, false),
    ),
    val selectedTripStyles: PersistentList<TripStyleEntity> = persistentListOf(),
    val selectedGender: Gender = Gender.NOT_SPECIFIED,
    val birthDate: String = "",
    val birthDateErrorText: UiText? = null,
    val isUnderAgeDialogVisible: Boolean = false,
    val characterName: String = "인스타 인생 맛집\n탐험러 펭귄",
    val characterTypeIntro: String = "펭귄은 내향적인 성향을 가지고 있고, 대부분의 시간을 집단 내에서 조용히 보내며, 개인적인 공간과 안정적인 환경을 선호해요.\n" +
        "\n 혼자보다는 집단과 함께 있는 것을 더 편안해하고, 사회적 상호작용보다 자신의 역할에 집중합니다. 매우 세부적으로 계획을 세우고 조직적인 행동을 하는 유형이에요",
    val tripStyleIntro: String = "펭귄은 여행을 떠나기 전에 철저한 계획을 세우는 것을 좋아해요. 여행의 주요 목적지와 일정, 활동을 미리 정해두고, 예상 가능한 상황에 대비해 준비를 철저히 할 때 안정감을 느끼며 편안하게 여행을 즐긴답니다.\n펭귄은 집단 내에서 협력하여 활동하는 것을 좋아하는데요. 여행 중에도 동행자와 함께 계획을 공유하고, 서로의 역할을 명확히 하여 협력적으로 움직이는 것을 선호하죠.",
    val isMyTripStyleShared: Boolean = false,
)

enum class Gender {
    MALE, FEMALE, NOT_SPECIFIED
}
