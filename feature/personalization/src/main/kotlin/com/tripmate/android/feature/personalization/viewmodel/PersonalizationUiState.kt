package com.tripmate.android.feature.personalization.viewmodel

import com.tripmate.android.core.common.UiText
import com.tripmate.android.domain.entity.TripStyleEntity
import com.tripmate.android.core.designsystem.R
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
    val isUnderAgeDialogVisible : Boolean = false,
)

enum class Gender {
    MALE, FEMALE, NOT_SPECIFIED
}
