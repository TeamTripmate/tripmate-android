package com.tripmate.android.mate.viewmodel

import com.tripmate.android.core.designsystem.R

sealed interface MateUiAction {
    data class OnMapCategorySelected(val categoryType: CategoryType) : MateUiAction
    data class OnShowListClicked(val isShowing: Boolean) : MateUiAction
    data object OnCurrentLocationClicked : MateUiAction
    data class OnSearchingListClicked(val isShowing: Boolean) : MateUiAction
    data class OnMarkerClicked(val poiId: Int) : MateUiAction
    data class OnTripCardClicked(val spotId: String) : MateUiAction
}

enum class CategoryType(
    val title: Int,
    val iconResource: Int?,
    val mateSelectMarkerIcon: Int?,
    val mateUnselectMarkerIcon: Int?,
    val selectMarkerIcon: Int?,
    val unselectMarkerIcon: Int?,
    val categoryCode: String?,
    val tips: String,
) {
    All(R.string.category_type_all, null, null, null, null, null, null, ""),
    Experience(
        R.string.category_type_experience,
        R.drawable.ic_experience,
        R.drawable.ic_select_mate_experience,
        R.drawable.ic_unselect_mate_experience,
        R.drawable.ic_select_experience,
        R.drawable.ic_unselect_experience,
        "EXPERIENCE",
        "인기 많은 체험을 원한다면 미리 예약 필수! \u2028인기 체험은 금방 마감되니 서둘러 예약하세요.\n" +
            "#OOTD는 편한게 최고 체험 활동이 많으니 움직임이 편한 옷과 운동화로 패션도 챙기고 편안함도 놓치지 마세요.\n" +
            "시간 배분은 필수 \uD83D\uDD70\uFE0F 체험지마다 소요 시간이 다르니, 미리 체크해서 알찬 하루를 계획하세요.",
    ),
    Culture(
        R.string.category_type_culture,
        R.drawable.ic_culture,
        R.drawable.ic_select_mate_culture,
        R.drawable.ic_unselect_mate_culture,
        R.drawable.ic_select_culture,
        R.drawable.ic_unselect_culture,
        "CULTURE_AND_ART",
        "전시 정보 체크 필수! 전시 정보는 사전 확인! 놓치고 싶지 않다면 공식 사이트 확인하세요.\n" +
            "문화 예술 투어는 감성 업! 오디오 가이드를 활용하면 전시 감상에 깊이를 더할 수 있어요.\n" +
            "전시 이후 핫플 카페 방문! 근처 예쁜 카페에서 전시 후 힐링 타임! 인근 핫플 카페를 미리 찾아보세요.",
    ),
    Show(
        R.string.category_type_show,
        R.drawable.ic_show,
        R.drawable.ic_select_mate_show,
        R.drawable.ic_unselect_mate_show,
        R.drawable.ic_select_show,
        R.drawable.ic_unselect_show,
        "FESTIVAL_AND_PERFORMANCE",
        "축제 일정 실시간 체크 필수! 축제나 공연 일정은 변동 가능성이 있으니, SNS나 공식 홈페이지에서 실시간 업데이트를 확인하세요.\n" +
            "푸드트럭에서 강원도 맛집 즐기기! 현지 푸드트럭이나 특산품 먹거리 먹방까지 즐기세요.\n" +
            "현금 챙겨가세요: 카드가 안 되는 곳도 있을 수 있으니 소액 현금 준비는 기본!",
    ),
    Nature(
        R.string.category_type_nature,
        R.drawable.ic_nature,
        R.drawable.ic_select_mate_nature,
        R.drawable.ic_unselect_mate_nature,
        R.drawable.ic_select_nature,
        R.drawable.ic_unselect_nature,
        "NATURE_AND_REST",
        "날씨를 확인하세요! 야외활동은 날씨가 중요한 법. 출발 전 기상 예보 꼭 확인하세요.\n" +
            "일출∙일몰 타이밍 인생샷\uD83D\uDCF8 강원도는 일출, 일몰 명소가 많아요. 시간을 맞춰 멋진 사진을 남겨보세요.\n" +
            "야외활동시 비상약 필수! 장시간 야외활동에는 비상약, 벌레 기피제를 챙기면 안전하게 즐길 수 있어요.",
    ),
    Course(
        R.string.category_type_course,
        R.drawable.ic_course,
        R.drawable.ic_select_mate_course,
        R.drawable.ic_unselect_mate_course,
        R.drawable.ic_select_course,
        R.drawable.ic_unselect_course,
        "TRIP_ITINERARY",
        "코스 길이 미리 체크하고 출발하기! 걷기나 자전거 코스는 자신의 체력에 맞게 선택하는 게 중요해요. 무리하지 말고 즐기기!\n" +
            "간식은 필수, 물도 잊지 마요! 코스 중간에 편의점이 없을 수 있으니, 간단한 간식과 물은 챙겨 가세요.\n" +
            "계절 맞춤 코디! 바람이 불거나 날씨가 추울 수 있으니, 바람막이나 방한 용품을 준비하면 좋아요.",
    ),
    History(
        R.string.category_type_history,
        R.drawable.ic_history,
        R.drawable.ic_select_mate_history,
        R.drawable.ic_unselect_mate_history,
        R.drawable.ic_select_history,
        R.drawable.ic_unselect_history,
        "HISTORY",
        "유명한 유적지나 박물관에서는 가이드 투어를 신청하면 깊이 있는 설명을 들을 수 있어요.\n" +
            "사진 촬영 금지 구역에서는 꼭 규칙을 지켜야 해요.\n" +
            "시원한 물과 간식을 챙겨 가면 긴 관람 시간 동안 지치지 않고 즐길 수 있어요.",
    ),
    Leports(
        R.string.category_type_leports,
        R.drawable.ic_leports,
        R.drawable.ic_select_mate_leports,
        R.drawable.ic_unselect_mate_leports,
        R.drawable.ic_select_leports,
        R.drawable.ic_unselect_leports,
        "LEISURE_SPORTS",
        "안전 장비를 철저히 착용하고, 활동 전에 반드시 가벼운 스트레칭을 하세요.\n" +
            "처음 도전하는 스포츠는 가이드나 강사의 안내를 따르는 것이 중요해요.\n" +
            "활동 후 충분한 휴식을 취하며 물을 자주 마셔 체력을 유지하세요.",
    ),
    Accommodate(
        R.string.category_type_accommodate,
        R.drawable.ic_accommodate,
        R.drawable.ic_select_mate_accommodate,
        R.drawable.ic_unselect_mate_accommodate,
        R.drawable.ic_select_accommodate,
        R.drawable.ic_unselect_accommodate,
        "ACCOMMODATION",
        "숙소 예약 시 체크인, 체크아웃 시간을 미리 확인하세요.\n" +
            "현지 숙박 스타일에 따라 전통적인 경험을 즐길 수 있는 기회도 놓치지 마세요.\n" +
            "숙소 내 귀중품 보관함을 이용해 안전을 지키세요.",
    ),
    Shopping(
        R.string.category_type_shopping,
        R.drawable.ic_shopping,
        R.drawable.ic_select_mate_shopping,
        R.drawable.ic_unselect_mate_shopping,
        R.drawable.ic_select_shopping,
        R.drawable.ic_unselect_shopping,
        "SHOPPING",
        "강원도는 감자빵, 황태, 메밀 등 다양한 특산품이 유명해요. 강원도만의 특별한 맛과 품질을 경험하실 수 있을 거예요!\n" +
            "강원도는 아름다운 자연과 함께하는 쇼핑이 매력적이에요. 예쁜 카페나 기념품 가게가 자연 속에 자리 잡은 곳이 많으니, 쇼핑도 하고 자연도 즐기는 여유로운 시간을 보내보세요!",
    ),
    Food(
        R.string.category_type_food,
        R.drawable.ic_food,
        R.drawable.ic_select_mate_food,
        R.drawable.ic_unselect_mate_food,
        R.drawable.ic_select_food,
        R.drawable.ic_unselect_food,
        "RESTAURANT_AND_CAFE",
        "미리 예약할 수 있는 유명 맛집은 예약을 권장해요, 대기 시간이 길 수 있으니까요.\n" +
            "현지에서만 맛볼 수 있는 특색 있는 음식들을 도전해 보세요.\n" +
            "인기 있는 장소일수록 리뷰를 참고해 메뉴를 선택하면 실패 확률이 줄어들어요.",
    ),
    None(R.string.category_type_none, null, null, null, null, null, null, ""),
}
