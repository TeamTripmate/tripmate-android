package com.tripmate.android.feature.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.tripmate.android.feature.trip_original.navigation.tripOriginalNavGraph
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.TripmateScaffold
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray005
import com.tripmate.android.core.designsystem.theme.Gray007
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Mid
import com.tripmate.android.feature.trip_detail.navigation.tripDetailNavGraph
import com.tripmate.android.feature.home.navigation.homeNavGraph
import com.tripmate.android.feature.mate_recruit.navigation.mateRecruitNavGraph
import com.tripmate.android.feature.mate_recruit_post.navigation.mateRecruitPostNavGraph
import com.tripmate.android.feature.mypage.navigation.myPageNavGraph
import com.tripmate.android.feature.mypage.navigation.myPickNavGraph
import com.tripmate.android.feature.mypage.navigation.myTripCharacterInfoNavGraph
import com.tripmate.android.feature.mypage.navigation.privacyPolicyNavGraph
import com.tripmate.android.feature.mypage.navigation.termOfUseNavGraph
import com.tripmate.android.feature.mypage.navigation.withdrawNavGraph
import com.tripmate.android.feature.trip_list.navigation.mateListNavGraph
import com.tripmate.android.feature.trip_list.navigation.mateOpenChatNavGraph
import com.tripmate.android.feature.trip_list.navigation.tripListNavGraph
import com.tripmate.android.mate.navigation.mateNavGraph
import com.tripmate.android.feature.trip_detail.navigation.reportNavGraph
import com.tripmate.android.mate_review.navigation.mateReviewNavGraph
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun MainScreen(
    navController: MainNavController = rememberMainNavController(),
    navigateToPersonalization: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    TripmateScaffold(
        bottomBar = {
            MainBottomBar(
                visible = navController.shouldShowBottomBar(),
                tabs = MainTab.entries.toImmutableList(),
                currentTab = navController.currentTab,
                onTabSelected = {
                    navController.navigate(it)
                },
            )
        },
        containerColor = White,
    ) { innerPadding ->
        NavHost(
            navController = navController.navController,
            startDestination = navController.startDestination,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            modifier = Modifier.fillMaxSize(),
        ) {
            homeNavGraph(
                padding = innerPadding,
                navigateToTripDetail = navController::navigateToTripDetail,
                navigateToTripOriginal = navController::navigateToTripOriginal,
            )
            mateNavGraph(
                padding = innerPadding,
                navigateToTripDetail = navController::navigateToTripDetail,
            )
            myPageNavGraph(
                padding = innerPadding,
                navigateToMyTripCharacterInfo = navController::navigateToMyTripCharacterInfo,
                navigateToMyPick = navController::navigateToMyPick,
                navigateToLogin = navigateToLogin,
                navigateToWithdraw = navController::navigateToWithdraw,
                navigateToMain = navController::popBackStackToHome,
                navigateToPrivacyPolicy = navController::navigateToPrivacyPolicy,
                navigateToTermOfUse = navController::navigateToTermOfUse,
            )
            mateRecruitNavGraph(
                padding = innerPadding,
                popBackStack = navController::popBackStackIfNotHome,
            )
            mateReviewNavGraph(
                padding = innerPadding,
                popBackStack = navController::popBackStackIfNotHome,
            )
            mateRecruitPostNavGraph(
                padding = innerPadding,
                popBackStack = navController::popBackStackIfNotHome,
            )
            tripDetailNavGraph(
                padding = innerPadding,
                popBackStack = navController::popBackStackIfNotHome,
                navigateToMateRecruit = navController::navigateToMateRecruit,
                navigateToMateReviewPost = navController::navigateToMateReviewPost,
                navigateToReport = navController::navigateToReport,
            )
            myTripCharacterInfoNavGraph(
                padding = innerPadding,
                popBackStack = navController::popBackStackIfNotHome,
                navigateToPersonalization = navigateToPersonalization,
            )
            tripListNavGraph(
                padding = innerPadding,
                navigateToMateList = navController::navigateToMateList,
                navigateToMateOpenChat = navController::navigateToMateOpenChat,
            )
            mateListNavGraph(
                padding = innerPadding,
                popBackStack = navController::popBackStackIfNotHome,
            )
            mateOpenChatNavGraph(
                popBackStack = navController::popBackStackIfNotHome,
                navigateToDetailScreen = navController::navigateToMateReviewPost,
            )
            myPickNavGraph(
                padding = innerPadding,
                popBackStack = navController::popBackStackIfNotHome,
            )
            withdrawNavGraph(
                padding = innerPadding,
                popBackStack = navController::popBackStack,
                navigateToLogin = navigateToLogin,
                navigateToMain = navController::popBackStackToHome,
            )
            reportNavGraph(
                padding = innerPadding,
                popBackStack = navController::popBackStackIfNotHome,
            )
            privacyPolicyNavGraph(
                popBackStack = navController::popBackStackIfNotHome,
            )
            termOfUseNavGraph(
                popBackStack = navController::popBackStackIfNotHome,
            )
            tripOriginalNavGraph(
                padding = innerPadding,
            )
        }
    }
}

@Composable
private fun MainBottomBar(
    visible: Boolean,
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) },
    ) {
        Box(modifier = Modifier.background(Background02)) {
            Column {
                HorizontalDivider(color = Gray007)
                Row(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth()
                        .height(64.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    tabs.forEach { tab ->
                        MainBottomBarItem(
                            tab = tab,
                            selected = tab == currentTab,
                            onClick = {
                                if (tab != currentTab) {
                                    onTabSelected(tab)
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                indication = null,
                role = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = if (selected) ImageVector.vectorResource(tab.selectedIconResId)
                else ImageVector.vectorResource(tab.iconResId),
                contentDescription = tab.contentDescription,
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = tab.label,
                color = if (selected) Primary01 else Gray005,
                style = XSmall12_Mid,
            )
        }
    }
}

@ComponentPreview
@Composable
fun MainBottomBarPreview() {
    TripmateTheme {
        MainBottomBar(
            visible = true,
            tabs = MainTab.entries.toImmutableList(),
            currentTab = MainTab.HOME,
            onTabSelected = {},
        )
    }
}
