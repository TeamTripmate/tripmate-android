package com.tripmate.android.feature.mypage.viewmodel.mypage

data class MyPageUiState(
    val profileImgUrl: String = "https://picsum.photos/72",
    val nickname: String = "나트립",
    val characterId: Long = 0L,
    val characterImgUrl: String = "https://picsum.photos/48",
    val personalizationResult: String = "인생이 즐거운 쇼핑 비버",
    val type1: String = "안생사진",
    val type2: String = "자유로운",
    val type3: String = "쇼핑마니아",
)
