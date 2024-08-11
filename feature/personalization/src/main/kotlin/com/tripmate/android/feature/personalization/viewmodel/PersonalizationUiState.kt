package com.tripmate.android.feature.personalization.viewmodel

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
        TripStyleEntity(1, "쇼핑매니아\n기념품은 필수", R.drawable.img_shopping_bags,false),
        TripStyleEntity(2, "풍경보며\n걷는게 좋아", R.drawable.img_running_shoe,false),
        TripStyleEntity(3, "랜드마크는\n무조건", R.drawable.img_statue_of_liberty,false),
        TripStyleEntity(4, "여행은\n액티비티", R.drawable.img_diving_mask,false),
        TripStyleEntity(5, "인스타에\n추억기록", R.drawable.img_mobile_phone,false),
        TripStyleEntity(6, "여행은\n모험이지", R.drawable.img_compass,false),
        TripStyleEntity(7, "자유로운\n배낭여행", R.drawable.img_backpack,false),
        TripStyleEntity(8, "인생샷 남기러\n여행가자!", R.drawable.img_camera_with_flash,false),
        TripStyleEntity(9, "문화체험은\n여행의 이유", R.drawable.img_framed_picture,false),
        TripStyleEntity(10, "맛집 방문은\n필수코스", R.drawable.img_pot_of_food,false),
        TripStyleEntity(11, "편안한게 최고\n여행은 힐링", R.drawable.img_deciduous_tree,false),
        TripStyleEntity(12, "테마파크는\n필수", R.drawable.img_ferris_wheel,false),
    ),
    val selectedTripStyles: PersistentList<TripStyleEntity> = persistentListOf(),
)
