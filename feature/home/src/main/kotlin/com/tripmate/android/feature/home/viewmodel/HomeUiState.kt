package com.tripmate.android.feature.home.viewmodel

import com.tripmate.android.domain.entity.SpotEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeUiState(
    val isLoading: Boolean = false,
    val tabs: ImmutableList<String> = persistentListOf("액티비티", "힐링"),
    val selectedTabIndex: Int = 0,
    val activitySelectedChips: ImmutableList<String> = persistentListOf("전체"),
    val healingSelectedChips: ImmutableList<String> = persistentListOf("전체"),
    val spotList: ImmutableList<SpotEntity> = persistentListOf(),
    val showTripOriginal: Boolean = false,
    val tripOriginalList: ImmutableList<TripOriginalData> = persistentListOf(
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip1,
            title = "오대산 상원사",
            location = "강원도 평창군 진부면",
            time = "11.26(화) 오전11:00",
            description = "오대산 상원사는 강원도 평창군에 위치한 역사 깊은 사찰입니다. 신라시대에 창건된 이 사찰은 오대산 국립공원 내에 자리잡고 있으며, 다양한 문화재와 유물로 유명합니다. 상원사 ...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip2,
            title = "강릉 명주동거리",
            location = "강원도 강릉시 명주동",
            time = "11.24(일) 오전11:00",
            description = "명주동은 시청과 옛 관아가 함께 자리하고 있어 강릉 행정의 중심 역할을 하던 곳이였어요. 시청 건물이 이전되고 빛을 잃어가던 명주동이, 낡은 건물을 활용한 문화 공간이 들어서 ...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip3,
            title = "속초관광수산시장 닭강정골목",
            location = "강원도 속초시 중앙로147번길 16",
            time = "11.24(일) 오전11:00",
            description = "강원도 속초시에 위치하며, 다양한 닭강정 가게가 밀집해 있는 전통시장이에요. 이곳은 바삭하고 달콤한 맛의 닭강정을 맛볼 수 있으며, 방문객들에게 인기 있는 맛집으로 유명하 ...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip4,
            title = "한반도 전기카트 체험장",
            location = "강원도 강릉시 강동면",
            time = "11.24(일) 오전11:00",
            description = "신재생 에너지를 활용한 야외 전동 고카트 체험장이에요. 20분간 쌩쌩 달리며 바람을 만끽할 수 있어요. 간단한 조작법과 주의사항만 지킨다면 누구나 운전이 가능하답니다. 특히 달...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip5,
            title = "웰컴투동막골 촬영지 산책 및 관람",
            location = "강원도 강릉시 강동면",
            time = "11.24(일) 오전11:00 ",
            description = "웰컴투동막골 촬영지는 강원도 평창군에 위치하며, 유명 영화 ‘웰컴투동막골’의 촬영지로 잘 알려진 곳이에요. 영화의 배경이 되었던  자연 경관과 전통적인 한국 마을의 모습이 보존 ...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip6,
            title = "강릉 바우길 9구간 헌화로 산책길 걷기",
            location = "강원도 강릉시 강동면",
            time = "11.24(일) 오전11:00 ",
            description = "오대산 상원사는 강원도 평창군에 위치한 역사 깊은 사찰입니다. 신라시대에 창건된 이 사찰은 오대산 국립공원 내에 자리잡고 있으며, 다양한 문화재와 유물로 유명합니다. 상원사 ...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip7,
            title = "소양호 (양구)",
            location = "강원도 양구군 양구읍",
            time = "11. 27(화) 오전11:00",
            description = "소양호는 강원도 춘천시, 양구군, 인제군에 걸쳐 조성된 한반도에서 가장 큰 인공 호수입니다.  춘천∼양구 간 주요 교통로이자 수상관광지 명소로 유명한데요. 이곳은 수상 레저와",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip8,
            title = "내린천 래프팅",
            location = "강원도 인제군 인제읍",
            time = "09. 28(토) 오전11:00",
            description = "내린천 래프팅은 강원도 인제군에 위치하고 있으며 한국의 3대 래프팅지로 꼽히는 내린천계곡에서 진행되는 수상레포츠입니다. 래프팅은 한배에 6~8명이 한 개 조를 이뤄 거친 ...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip9,
            title = "순수 양떼목장",
            location = "강원도 평창군 대관령면",
            time = "12. 21(토) 오전10:00",
            description = "순수 양떼목장은 강원도 평창에 위치한 국내 최대의 양 전문 목장으로, 약 20만 평방미터의 넓은 부지를 자랑합니다. 이곳의 양들은 매일 자연 방목되어 자유롭게 뛰어놀며 건강하",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip10,
            title = "주문진 방파제",
            location = "강원특별자치도 강릉시 주문진읍",
            time = "11.28(수) 오전11:00",
            description = "주문진 방파제는 드라마 도깨비 촬영지로, 강원도 강릉시 주문진읍에 위치하며, 아름다운 해안 경관을 가진 명소입니다. 방파제 끝에서 바라보는 바다와 항구의 풍경은 매우 아름 ...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip11,
            title = "동강사진박물관",
            location = "강원특별자치도 영월군 영월읍",
            time = "09. 28(토) 오전11:00",
            description = "동강사진박물관은 2005년 개관한 국내 최초의 공립 사진 박물관으로, 사진의 역사와 변천사를 소개합니다. 1940년대부터 1980년대까지의 다큐멘터리 사진 작품과 세계적으로 ...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip12,
            title = "월송유로베이커리앤로스터리",
            location = "강원특별자치도 춘천시 경춘로 349-1",
            time = "11.24(일) 오전11:00",
            description = "월송유로 베이커리 앤 로스터리는 춘천에 위치한 유럽풍의 베이커리 카페입니다. 카페 앞에 북한강뷰를 볼 수 있어요.고즈넉한 한옥 인테리어, 30여 년간 가꾼 소나무 정원이 카페 ...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip13,
            title = "평창 육백마지기",
            location = "강원특별자치도 춘천시 경춘로 349-1",
            time = "6.24(일) 오전11:00",
            description = "평창군에 위치한 고원지대의 목장으로, ‘볍씨 육백 말을 뿌릴 수 있을 정도로 넓은 평원’이라 하여 붙여진 지명이에요. 넓은 초원과 경이로운 자연 풍경에 한국의 알프스라고도 불린답 ...",
        ),
        TripOriginalData(
            imgUrl = com.tripmate.android.core.designsystem.R.drawable.img_trip14,
            title = "리빙스턴교",
            location = "강원특별자치도 인제군 인제읍 합강리",
            time = "11.24(일) 오전11:00",
            description = "리빙스턴교는 인제군 인제읍 덕산리에서 합강리를 잇는 다리로, 한국 전쟁 당시 아픈 사연이 담겨있습니다. 한국 전쟁중이던 1951년, 중공군의 총공세에 유엔군으로 참여했던 리빙 ...",
        ),
    ),
)

data class TripOriginalData(
    val imgUrl: Int,
    val title: String,
    val description: String,
    val location: String,
    val time: String,
)
