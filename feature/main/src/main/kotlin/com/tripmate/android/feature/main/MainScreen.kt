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
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.TripmateScaffold
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray005
import com.tripmate.android.core.designsystem.theme.Gray007
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Mid
import com.tripmate.android.feature.home.navigation.homeNavGraph
import com.tripmate.android.feature.mypage.navigation.myPageNavGraph
import com.tripmate.android.feature.mate_recruit.navigation.mateRecruitNavGraph
import com.tripmate.android.feature.mypage.navigation.myPickNavGraph
import com.tripmate.android.feature.mypage.navigation.myTripCharacterInfoNavGraph
import com.tripmate.android.feature.mypage.navigation.withdrawNavGraph
import com.tripmate.android.feature.trip_list.navigation.tripListNavGraph
import com.tripmate.android.mate.navigation.mateNavGraph
import com.tripmate.android.mate_review.navigation.mateReviewNavGraph
import com.tripmate.android.notification.navigation.notificationNavGraph
import com.tripmate.android.writing.navigation.writingNavGraph
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun MainScreen(
    navigator: MainNavController = rememberMainNavController(),
) {
    TripmateScaffold(
        bottomBar = {
            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries.toImmutableList(),
                currentTab = navigator.currentTab,
                onTabSelected = {
                    navigator.navigate(it)
                },
            )
        },
        containerColor = White,
    ) { innerPadding ->
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            modifier = Modifier.fillMaxSize(),
        ) {
            homeNavGraph(
                padding = innerPadding,
                navigateToMateRecruit = navigator::navigateToMateRecruit,
                navigateToMateReview = navigator::navigateToMateReview,
            )
            mateNavGraph(
                padding = innerPadding,
//                popBackStack = navigator::popBackStackIfNotHome,
            )
            writingNavGraph(
                padding = innerPadding,
//                popBackStack = navigator::popBackStackIfNotHome,
            )
            notificationNavGraph(
                padding = innerPadding,
            )
            myPageNavGraph(
                padding = innerPadding,
                navigateToMyTripCharacterInfo = navigator::navigateToMyTripCharacterInfo,
                navigateToMyPick = navigator::navigateToMyPick,
                navigateToWithdraw = navigator::navigateToWithdraw,
            )
            mateRecruitNavGraph(
                padding = innerPadding,
                popBackStack = navigator::popBackStackIfNotHome,
            )
            mateReviewNavGraph(
                padding = innerPadding,
                popBackStack = navigator::popBackStackIfNotHome,
            )
            myTripCharacterInfoNavGraph(
                padding = innerPadding,
                popBackStack = navigator::popBackStackIfNotHome,
            )
            tripListNavGraph(
                padding = innerPadding,
//                popBackStack = navigator::popBackStackIfNotHome,
            )
            myPickNavGraph(
                padding = innerPadding,
                popBackStack = navigator::popBackStackIfNotHome,
            )
            withdrawNavGraph(
                padding = innerPadding,
                popBackStack = navigator::popBackStackIfNotHome,
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
