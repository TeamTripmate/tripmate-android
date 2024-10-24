package com.tripmate.android.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.tripmate.android.feature.trip_original.navigation.navigateToTripOriginal
import com.tripmate.android.feature.trip_detail.navigation.navigateToTripDetail
import com.tripmate.android.feature.home.navigation.HOME_ROUTE
import com.tripmate.android.feature.home.navigation.navigateToHome
import com.tripmate.android.feature.mate_recruit.navigation.navigateToMateRecruit
import com.tripmate.android.feature.mate_recruit_post.navigation.navigateToMateRecruitPost
import com.tripmate.android.feature.mypage.navigation.navigateToMyPage
import com.tripmate.android.feature.mypage.navigation.navigateToMyPick
import com.tripmate.android.feature.mypage.navigation.navigateToMyTripCharacterInfo
import com.tripmate.android.feature.mypage.navigation.navigateToPrivacyPolicy
import com.tripmate.android.feature.mypage.navigation.navigateToTermOfUse
import com.tripmate.android.feature.mypage.navigation.navigateToWithdraw
import com.tripmate.android.feature.trip_list.navigation.navigateToMateList
import com.tripmate.android.feature.trip_list.navigation.navigateToMateOpenChat
import com.tripmate.android.feature.trip_list.navigation.navigateToTripList
import com.tripmate.android.mate.navigation.navigateToMate
import com.tripmate.android.feature.trip_detail.navigation.navigateToReport
import com.tripmate.android.feature.trip_list.navigation.navigateToCharacter
import com.tripmate.android.mate_review.navigation.navigateToMateReview

internal class MainNavController(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = HOME_ROUTE

    val currentTab: MainTab?
        @Composable get() = currentDestination
            ?.route
            ?.let(MainTab.Companion::find)

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.MATE -> navController.navigateToMate(navOptions)
            MainTab.TRIP_LIST -> navController.navigateToTripList(navOptions)
            MainTab.MY_PAGE -> navController.navigateToMyPage(navOptions)
        }
    }

    fun navigateToMateRecruit(spotId: String, spotTitle: String, spotAddress: String) {
        navController.navigateToMateRecruit(spotId, spotTitle, spotAddress)
    }

    fun navigateToMateReview(companionId: Long, title: String, date: String) {
        navController.navigateToMateReview(companionId, title, date)
    }

    fun navigateToTripDetail(spotId: String) {
        navController.navigateToTripDetail(spotId)
    }

    fun navigateToMateReviewPost(companionId: Long) {
        navController.navigateToMateRecruitPost(companionId)
    }

    fun navigateToMyTripCharacterInfo(characterId: String, tripStyle: String) {
        navController.navigateToMyTripCharacterInfo(characterId, tripStyle)
    }

    fun navigateToMyPick() {
        navController.navigateToMyPick()
    }

    fun navigateToWithdraw() {
        navController.navigateToWithdraw()
    }

    fun navigateToMateList(companionId: Long, page: Int) {
        navController.navigateToMateList(companionId, page)
    }

    fun navigateToMateOpenChat(
        companionId: Long,
        openChatLink: String,
        selectedKeyword1: String,
        selectedKeyword2: String,
        selectedKeyword3: String,
        tripStyle: String,
        characterId: String,
        isMatched: Boolean,
    ) {
        navController.navigateToMateOpenChat(
            companionId = companionId,
            openChatLink = openChatLink,
            selectedKeyword1 = selectedKeyword1,
            selectedKeyword2 = selectedKeyword2,
            selectedKeyword3 = selectedKeyword3,
            tripStyle = tripStyle,
            characterId = characterId,
            isMatched = isMatched,
        )
    }

    fun navigateToCharacterDescription(characterId: String, tag1: String, tag2: String, tag3: String) {
        navController.navigateToCharacter(characterId, tag1, tag2, tag3)
    }

    fun navigateToReport() {
        navController.navigateToReport()
    }

    fun navigateToPrivacyPolicy() {
        navController.navigateToPrivacyPolicy()
    }

    fun navigateToTermOfUse() {
        navController.navigateToTermOfUse()
    }

    fun navigateToTripOriginal(spotId: Int) {
        navController.navigateToTripOriginal(spotId)
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    // https://github.com/droidknights/DroidKnights2023_App/pull/243/commits/4bfb6d13908eaaab38ab3a59747d628efa3893cb
    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination(HOME_ROUTE)) {
            popBackStack()
        }
    }

    fun popBackStackToHome() {
        navController.popBackStack(HOME_ROUTE, false)
    }

    private fun isSameCurrentDestination(route: String) =
        navController.currentDestination?.route == route

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        return currentRoute in MainTab
    }
}

@Composable
internal fun rememberMainNavController(
    navController: NavHostController = rememberNavController(),
): MainNavController = remember(navController) {
    MainNavController(navController)
}
