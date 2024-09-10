package com.tripmate.android.feature.main

import com.tripmate.android.core.designsystem.R

internal enum class MainTab(
    val iconResId: Int,
    val selectedIconResId: Int,
    val contentDescription: String,
    val label: String,
    val route: String,
) {
    HOME(
        iconResId = R.drawable.ic_home,
        selectedIconResId = R.drawable.ic_selected_home,
        contentDescription = "Home Icon",
        label = "홈",
        route = "home_route",
    ),
    MATE(
        iconResId = R.drawable.ic_mate,
        selectedIconResId = R.drawable.ic_selected_mate,
        contentDescription = "Mate Icon",
        label = "동행찾기",
        route = "mate_route",
    ),
    TRIP_LIST(
        iconResId = R.drawable.ic_trip_list,
        selectedIconResId = R.drawable.ic_selected_trip_list,
        contentDescription = "Trip List Icon",
        label = "여행목록",
        route = "trip_list_route",
    ),
    MY_PAGE(
        iconResId = R.drawable.ic_mypage,
        selectedIconResId = R.drawable.ic_selected_mypage,
        contentDescription = "My page Icon",
        label = "마이",
        route = "my_page_route",
    ),
    ;

    companion object {
        operator fun contains(route: String): Boolean {
            return entries.map { it.route }.contains(route)
        }

        fun find(route: String): MainTab? {
            return entries.find { it.route == route }
        }
    }
}
