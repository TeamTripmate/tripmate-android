package com.tripmate.android.mate

import android.Manifest
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.common.extension.hasLocationPermission
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray005
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.MateTitle
import com.tripmate.android.core.designsystem.theme.MateTitleBackGround
import com.tripmate.android.core.designsystem.theme.Medium16_Light
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Primary03
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.domain.entity.CategoryEntity
import com.tripmate.android.domain.entity.SpotEntity
import com.tripmate.android.feature.map.MapSection
import com.tripmate.android.feature.map.state.CameraPositionState
import com.tripmate.android.feature.map.state.rememberCameraPositionState
import com.tripmate.android.mate.viewmodel.CategoryType
import com.tripmate.android.mate.viewmodel.MateUiAction
import com.tripmate.android.mate.viewmodel.MateUiEvent
import com.tripmate.android.mate.viewmodel.MateUiState
import com.tripmate.android.mate.viewmodel.MateViewModel
import kotlinx.coroutines.launch

@Composable
fun MateRoute(
    innerPadding: PaddingValues,
    viewModel: MateViewModel = hiltViewModel(),
    navigateToTripDetail: (spotId: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState()

    InitPermission(context = context, viewModel = viewModel)

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MateUiEvent.ClickCurrentLocation -> {
                viewModel.moveCurrentLocation(cameraPositionState)
            }

            is MateUiEvent.NavigateToTripDetail -> {
                navigateToTripDetail(event.spotId)
            }
        }
    }

    MateScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onAction = viewModel::onAction,
        selectPoiAction = viewModel::movePoiLocation,
        cameraPositionState = cameraPositionState,
    )
}

@Composable
fun MateScreen(
    innerPadding: PaddingValues,
    uiState: MateUiState,
    onAction: (MateUiAction) -> Unit,
    selectPoiAction: (CameraPositionState, SpotEntity) -> Unit,
    cameraPositionState: CameraPositionState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Text(
            text = stringResource(R.string.searching_mate_title),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(14.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            LaunchedEffect(uiState.selectPoiItem) {
                uiState.selectPoiItem?.let {
                    selectPoiAction(cameraPositionState, it)
                }
            }

            MapSection(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                markerInfoList = uiState.getMarkerInfoList(),
            ) {
                onAction(MateUiAction.OnMarkerClicked(it.labelId.toInt()))
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                val categoryList = CategoryType.entries.filter { it != CategoryType.None }.toTypedArray()
                items(categoryList) { item ->
                    CategoryItemView(uiState.categoryType, item) {
                        onAction(MateUiAction.OnMapCategorySelected(item))
                    }
                }
            }

            if (uiState.isShowingList) {
                ShowPoiListView(
                    uiState.categoryType,
                    uiState.getShowingSpotList(),
                    onAction,
                )
            }

            if (!uiState.isShowingList) {
                if (uiState.getShowingSpotList().isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            CurrentButton {
                                onAction(MateUiAction.OnCurrentLocationClicked)
                            }

                            ShowListButton {
                                onAction(MateUiAction.OnShowListClicked(true))
                            }
                        }
                        ViewPagerScreen(
                            uiState = uiState,
                            listItem = uiState.getShowingSpotList(),
                            onAction = onAction,
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            CurrentButton {
                                onAction(MateUiAction.OnCurrentLocationClicked)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CurrentButton(onAction: () -> Unit) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(color = Color.White, shape = CircleShape)
            .clickable {
                onAction()
            },
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_current_location),
            contentDescription = "current button",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.Center)
                .size(24.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
    }
}

@Composable
fun ShowListButton(onAction: () -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = Gray009,
                shape = RoundedCornerShape(50.dp),
            )
            .clickable {
                onAction()
            },
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(Background02)
                .padding(10.dp),
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.img_menu),
                contentDescription = "menu button",
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                text = stringResource(id = R.string.see_more_list),
                style = Medium16_Light,
                color = Gray001,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun ShowPoiListView(
    categoryType: CategoryType,
    listItem: List<SpotEntity>,
    onAction: (MateUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Background02),
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                val categoryList = CategoryType.entries.filter { it != CategoryType.None }.toTypedArray()
                items(categoryList) { item ->
                    CategoryItemView(categoryType, item) {
                        onAction(MateUiAction.OnMapCategorySelected(item))
                    }
                }
            }

            MateSearchingCheckBox {
                onAction(MateUiAction.OnSearchingListClicked(it))
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
            ) {
                items(listItem) { item ->
                    GetPoiCardView(item, true) {
                        onAction(MateUiAction.OnTripCardClicked(item.id.toString()))
                    }
                }
            }
        }

        ShowMapButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
        ) {
            onAction(MateUiAction.OnShowListClicked(false))
        }
    }
}

@Composable
fun ShowMapButton(
    modifier: Modifier,
    onAction: () -> Unit,
) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = Gray009,
                shape = RoundedCornerShape(50.dp),
            )
            .clickable {
                onAction()
            },
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .background(Background02)
                .align(Alignment.Center)
                .padding(10.dp),
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize(),
                painter = painterResource(id = R.drawable.img_show_map),
                contentDescription = "show map button",
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                modifier = Modifier
                    .wrapContentSize(),
                text = stringResource(id = R.string.show_map),
                style = Medium16_Light,
                color = Gray001,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun CategoryItemView(selectCategoryType: CategoryType, categoryType: CategoryType, onItemClick: (CategoryType) -> Unit) {
    val isSelect = selectCategoryType == categoryType
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = if (isSelect) Primary01 else Gray009,
                shape = RoundedCornerShape(50.dp),
            )
            .clickable {
                onItemClick(categoryType)
            },
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
                .background(if (isSelect) Primary03.copy(alpha = 0.1f) else Background02)
                .align(Alignment.Center)
                .padding(horizontal = 14.dp, vertical = 6.dp),
        ) {
            categoryType.iconResource?.let { iconRes ->
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = "category icon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically),
                )

                Spacer(modifier = Modifier.width(4.dp))
            }

            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = stringResource(id = categoryType.title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPagerScreen(
    uiState: MateUiState,
    listItem: List<SpotEntity>,
    viewPagerScrollAction: (Int) -> Unit = {},
    onAction: (MateUiAction) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState {
        listItem.size
    }

    val selectPosition = listItem.indexOfFirst { it.id == uiState.selectPoiItem?.id }

    LaunchedEffect(uiState) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(selectPosition)
        }

        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewPagerScrollAction(listItem[page].id)
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        CustomViewPager(pagerState = pagerState, listItem, onAction)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomViewPager(pagerState: PagerState, listItem: List<SpotEntity>, onAction: (MateUiAction) -> Unit) {
    HorizontalPager(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        state = pagerState,
    ) {
        val item = listItem[pagerState.currentPage]
        GetPoiCardView(item, false) {
            onAction(MateUiAction.OnTripCardClicked(item.id.toString()))
        }
    }
}

@Composable
fun GetPoiCardView(item: SpotEntity, isListView: Boolean, onTripCardClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 10.dp, bottom = if (isListView) 10.dp else 20.dp, start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Gray009,
                shape = RoundedCornerShape(12.dp),
            )
            .clickable {
                onTripCardClick()
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Background02)
                .padding(8.dp),
        ) {
            NetworkImage(
                imgUrl = item.thumbnailUrl,
                contentDescription = "Example Image Icon",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(110.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (item.isSearching) {
                Text(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(MateTitleBackGround)
                        .padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.category_type_searching_mate),
                    fontSize = 10.sp,
                    color = MateTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray001,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = item.subCategory,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray006,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_address_location),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .wrapContentSize(),
                )

                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = item.address,
                    fontSize = 12.sp,
                    color = Gray005,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = item.distance.toString() + " km",
                    fontSize = 12.sp,
                    color = Gray005,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.description,
                fontSize = 12.sp,
                color = Gray005,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun InitPermission(
    context: Context,
    viewModel: MateViewModel,
) {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ),
    )

    LaunchedEffect(!context.hasLocationPermission()) {
        permissionState.launchMultiplePermissionRequest()
    }

    when {
        permissionState.allPermissionsGranted -> {
            viewModel.fetchCurrentLocation()
        }

        permissionState.shouldShowRationale -> {
        }

        !permissionState.allPermissionsGranted && !permissionState.shouldShowRationale -> {
        }
    }
}

@Composable
fun CustomCheckbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    val imageRes = if (isChecked) {
        R.drawable.img_radio_checked
    } else {
        R.drawable.img_radio_unchecked
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .size(18.dp)
            .clickable { onCheckedChange(!isChecked) },
    )
}

@Composable
fun MateSearchingCheckBox(onAction: (Boolean) -> Unit) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomCheckbox(
            isChecked = isChecked,
            onCheckedChange = {
                isChecked = it
                onAction(it)
            },
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = stringResource(id = R.string.see_only_searching_mate))
    }
}

@DevicePreview
@Composable
fun MateScreenPreview() {
    TripmateTheme {
        MateScreen(
            innerPadding = PaddingValues(),
            uiState = MateUiState(),
            onAction = { },
            selectPoiAction = { _, _ -> },
            cameraPositionState = CameraPositionState(),
        )
    }
}

@ComponentPreview
@Composable
fun GetPoiCardViewPreview() {
    TripmateTheme {
        GetPoiCardView(
            item = SpotEntity(
                id = 1,
                title = "Title 1",
                distance = 12.34,
                description = "This is the description for item 1",
                thumbnailUrl = "https://picsum.photos/36",
                latitude = 37.5,
                longitude = 127.0,
                address = "Address 1",
                companionYn = false,
                spotType = "Spot Type 1",
                category = CategoryEntity(
                    largeCategory = "Large Category 1",
                    mediumCategory = "Medium Category 1",
                    smallCategory = "Small Category 1",
                ),
            ),
            false,
            onTripCardClick = {},
        )
    }
}
