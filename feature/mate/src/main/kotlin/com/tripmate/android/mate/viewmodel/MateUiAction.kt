package com.tripmate.android.mate.viewmodel

import com.tripmate.android.core.designsystem.R

sealed interface MateUiAction {
    data class OnMapCategorySelected(val categoryType: CategoryType) : MateUiAction
    data class OnShowListClicked(val isShowing: Boolean) : MateUiAction
    data object OnCurrentLocationClicked : MateUiAction
    data class OnSearchingListClicked(val isShowing: Boolean) : MateUiAction
    data class OnMarkerClicked(val poiId: Int) : MateUiAction
}

enum class CategoryType(val title: Int, val iconResource: Int?) {
    All(R.string.category_type_searching_mate, null),
    Food(R.string.category_type_food, R.drawable.img_food),
    Hiking(R.string.category_type_hiking, R.drawable.img_hiking),
    Healing(R.string.category_type_healing, R.drawable.img_healing),
}
