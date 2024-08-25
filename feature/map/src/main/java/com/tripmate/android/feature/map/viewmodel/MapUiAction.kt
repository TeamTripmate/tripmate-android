package com.tripmate.android.feature.map.viewmodel

import com.tripmate.android.core.designsystem.R

sealed interface MapUiAction {
    data class OnMapCategorySelected(val categoryType: CategoryType) : MapUiAction
    data class OnShowListClicked(val isShowing: Boolean) : MapUiAction
    data object OnCurrentLocationClicked : MapUiAction
    data class OnSearchingListClicked(val isShowing: Boolean) : MapUiAction
}

enum class CategoryType(val title: Int, val iconResource: Int?) {
    All(R.string.category_type_searching, null),
    Food(R.string.category_type_food, R.drawable.img_food),
    Hiking(R.string.category_type_hiking, R.drawable.img_hiking),
    Healing(R.string.category_type_healing, R.drawable.img_healing)

}
