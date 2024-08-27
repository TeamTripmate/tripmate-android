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
import androidx.compose.runtime.setValue
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
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray005
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.Medium16_Light
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.domain.entity.POISimpleListEntity
import com.tripmate.android.feature.map.MapSection
import com.tripmate.android.feature.map.state.CameraPositionState
import com.tripmate.android.feature.map.state.rememberCameraPositionState
import com.tripmate.android.mate.viewmodel.CategoryType
import com.tripmate.android.mate.viewmodel.MateUiAction
import com.tripmate.android.mate.viewmodel.MateUiEvent
import com.tripmate.android.mate.viewmodel.MateUiState
import com.tripmate.android.mate.viewmodel.MateViewModel

@Composable
fun MateRoute(
    innerPadding: PaddingValues,
    viewModel: MateViewModel = hiltViewModel(),
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
        }
    }

    MateScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onAction = viewModel::onAction,
        cameraPositionState = cameraPositionState,
    )
}

@Composable
fun MateScreen(
    innerPadding: PaddingValues,
    uiState: MateUiState,
    onAction: (MateUiAction) -> Unit,
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
            MapSection(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                val categoryList = CategoryType.entries.toTypedArray()
                items(categoryList) { item ->
                    CategoryItemView(item) {
                        onAction(MateUiAction.OnMapCategorySelected(item))
                    }
                }
            }

            if (uiState.isShowingList) {
                ShowPoiListView(
                    uiState.simpleList,
                    onAction,
                )
            }

            if (!uiState.isShowingList) {
                if (uiState.simpleList.isNotEmpty()) {
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
                        ViewPagerScreen(listItem = uiState.simpleList)
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
    listItem: List<POISimpleListEntity>,
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
                val categoryList = CategoryType.entries.toTypedArray()
                items(categoryList) { item ->
                    CategoryItemView(item) {
                        onAction(MateUiAction.OnMapCategorySelected(item))
                    }
                }
            }

            MateSearchingCheckBox {
                onAction(MateUiAction.OnSearchingListClicked(it))
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(listItem) { item ->
                    ListItemView(item) {
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
fun ListItemView(item: POISimpleListEntity, onItemClick: (POISimpleListEntity) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(255.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Gray009,
                shape = RoundedCornerShape(12.dp),
            )
            .clickable {
                onItemClick(item)
            },
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(Background02)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(110.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Gray001,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.address,
                fontSize = 12.sp,
                color = Gray005,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

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

@Composable
fun CategoryItemView(categoryType: CategoryType, onItemClick: (CategoryType) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = Gray009,
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
                .background(Background02)
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
    listItem: List<POISimpleListEntity>,
) {
    val pagerState = rememberPagerState {
        listItem.size
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        CustomViewPager(pagerState = pagerState, listItem)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomViewPager(pagerState: PagerState, listItem: List<POISimpleListEntity>) {
    HorizontalPager(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp),
        state = pagerState,
    ) {
        val item = listItem[pagerState.currentPage]
        GetPoiCardView(item)
    }
}

@Composable
fun GetPoiCardView(item: POISimpleListEntity) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(255.dp)
            .padding(top = 10.dp, bottom = 20.dp, start = 5.dp, end = 5.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Gray009,
                shape = RoundedCornerShape(12.dp),
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Background02)
                .padding(8.dp),
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(110.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Gray001,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.address,
                fontSize = 12.sp,
                color = Gray005,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

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
            cameraPositionState = CameraPositionState(),
        )
    }
}

@ComponentPreview
@Composable
fun GetPoiCardViewPreview() {
    TripmateTheme {
        GetPoiCardView(
            item = POISimpleListEntity(
                title = "Title 1",
                address = "강원도 춘천시",
                description = "This is the description for item 1",
                imageRes = R.drawable.img_camera_with_flash,
                lat = 37.5,
                lon = 127.0,
            ),
        )
    }
}
