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

    TRIPLIST(
        iconResId = R.drawable.ic_triplist,
        selectedIconResId = R.drawable.ic_selected_triplist,
        contentDescription = "TripList Icon",
        label = "여행목록",
        route = "tripList_route",
    ),

    NOTIFICATION(
        iconResId = R.drawable.ic_notification,
        selectedIconResId = R.drawable.ic_selected_notification,
        contentDescription = "Notification Icon",
        label = "알림",
        route = "notification_route",
    ),

    MYPAGE(
        iconResId = R.drawable.ic_mypage,
        selectedIconResId = R.drawable.ic_selected_mypage,
        contentDescription = "My page Icon",
        label = "마이",
        route = "mypage_route",
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
