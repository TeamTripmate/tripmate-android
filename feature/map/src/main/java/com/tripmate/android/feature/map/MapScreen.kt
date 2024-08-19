package com.tripmate.android.feature.map

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.label.LabelOptions
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray005
import com.tripmate.android.core.designsystem.theme.Medium16_Light
import com.tripmate.android.domain.entity.POISimpleListEntity
import com.tripmate.android.feature.map.viewmodel.CategoryType
import com.tripmate.android.feature.map.viewmodel.MapUiAction
import com.tripmate.android.feature.map.viewmodel.MapViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val listItem = when (uiState.categoryType) {
        CategoryType.Searaching -> {
            listOf(
                POISimpleListEntity("Searching Title 1", "강원도 춘천시", "This is the description for item 1", R.drawable.img_camera_with_flash, lat = 37.517235, lon = 127.047325),
                POISimpleListEntity("Searching Title 2", "강원도 춘천시", "This is the description for item 2", R.drawable.img_camera_with_flash, lat = 37.5, lon = 127.2),
                POISimpleListEntity("Searching Title 3", "강원도 춘천시", "This is the description for item 3", R.drawable.img_camera_with_flash, lat = 37.5, lon = 127.3),
            )
        }

        else -> {
            listOf(
                POISimpleListEntity("Title 1", "강원도 춘천시", "This is the description for item 1", R.drawable.img_camera_with_flash, lat = 37.5, lon = 127.0),
                POISimpleListEntity("Title 2", "강원도 춘천시", "This is the description for item 2", R.drawable.img_camera_with_flash, lat = 37.6, lon = 127.0),
                POISimpleListEntity("Title 3", "강원도 춘천시", "This is the description for item 3", R.drawable.img_camera_with_flash, lat = 37.7, lon = 127.0),
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("내 근처 여행") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { }
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Background02,
                    ),
                )
            },

            ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                if (uiState.isShowingList) {
                    ShowPoiListView(listItem, viewModel)
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        MapSection(
                            modifier = Modifier.fillMaxSize(),
                        )
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        val categoryList = CategoryType.values()
                        items(categoryList) { item ->
                            CategoryItemView(item) {
                                viewModel.onAction(MapUiAction.OnMapCategorySelected(item))
                            }
                        }
                    }
                }
            }
        }

        if (uiState.isShowingList.not()) {
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
                    CurrentButton()

                    MenuButton {
                        viewModel.onAction(MapUiAction.OnShowListClicked(true))
                    }
                }
                ViewPagerScreen(modifier, listItem = listItem)
            }
        }
    }
}


@Composable
fun CurrentButton() {
    Box(modifier = Modifier.size(44.dp)) {
        Image(
            painter = painterResource(id = R.drawable.img_current_location_background),
            contentDescription = "current button background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
        )

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
fun MenuButton(onAction: () -> Unit) {
    Card(
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .height(44.dp)
            .width(110.dp)
            .clickable {
                onAction()
            },
        colors = CardDefaults.cardColors(containerColor = Background02),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
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
    viewModel: MapViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background02),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            val categoryList = CategoryType.values()
            items(categoryList) { item ->
                CategoryItemView(item) {
                    viewModel.onAction(MapUiAction.OnMapCategorySelected(item))
                }
            }
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
}


@Composable
fun ListItemView(item: POISimpleListEntity, onItemClick: (POISimpleListEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(255.dp)
            .clickable {
                onItemClick(item)
            },
        colors = CardDefaults.cardColors(
            containerColor = Background02,
        ),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
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
    Card(
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .clickable {
                onItemClick(categoryType)
            },
        colors = CardDefaults.cardColors(containerColor = Background02),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
                .align(Alignment.CenterHorizontally)
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
    modifier: Modifier,
    listItem: List<POISimpleListEntity>,
) {

    val pagerState = rememberPagerState {
        listItem.size
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        CustomViewPager(pagerState = pagerState, listItem) {

        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomViewPager(pagerState: PagerState, listItem: List<POISimpleListEntity>, onItemClick: (POISimpleListEntity) -> Unit) {
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(255.dp)
            .padding(top = 10.dp, bottom = 20.dp, start = 5.dp, end = 5.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Gray005),
        colors = CardDefaults.cardColors(
            containerColor = Background02,
        ),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
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
