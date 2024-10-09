package com.tripmate.android.mate_review.viewmodel

import com.tripmate.android.domain.entity.BadReviewEntity
import com.tripmate.android.domain.entity.GoodReviewEntity
import com.tripmate.android.feature.mate_review.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class MateReviewUiState(
    val mateReviewTitle: String = "두타산 무릉계곡 산행 하실 분",
    val tripLocation: String = "두타산 주차장",
    val companionId: Int = 0,
    val tripDate: String = "8.24(일)",
    val tripTime: String = "오전11:00",
    val allGoodReviews: ImmutableList<GoodReviewEntity> = persistentListOf(
        GoodReviewEntity(id = 1, textResId = R.string.good_manner, isSelected = false),
        GoodReviewEntity(id = 2, textResId = R.string.good_plan, isSelected = false),
        GoodReviewEntity(id = 3, textResId = R.string.good_conversation, isSelected = false),
        GoodReviewEntity(id = 4, textResId = R.string.good_respect, isSelected = false),
        GoodReviewEntity(id = 5, textResId = R.string.good_communication, isSelected = false),
        GoodReviewEntity(id = 6, textResId = R.string.good_adaptability, isSelected = false),
    ),
    val selectedGoodReviews: PersistentList<GoodReviewEntity> = persistentListOf(),
    val allBadReviews: ImmutableList<BadReviewEntity> = persistentListOf(
        BadReviewEntity(id = 1, textResId = R.string.bad_manner, isSelected = false),
        BadReviewEntity(id = 2, textResId = R.string.bad_time, isSelected = false),
        BadReviewEntity(id = 3, textResId = R.string.bad_talkative, isSelected = false),
        BadReviewEntity(id = 4, textResId = R.string.bad_joke, isSelected = false),
        BadReviewEntity(id = 5, textResId = R.string.bad_plan, isSelected = false),
        BadReviewEntity(id = 6, textResId = R.string.bad_assertive, isSelected = false),
        BadReviewEntity(id = 7, textResId = R.string.bad_communication, isSelected = false),
    ),
    val selectedBadReviews: PersistentList<BadReviewEntity> = persistentListOf(),
)
