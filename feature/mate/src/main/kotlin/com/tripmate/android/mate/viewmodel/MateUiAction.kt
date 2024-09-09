package com.tripmate.android.mate.viewmodel

import com.tripmate.android.core.designsystem.R

sealed interface MateUiAction {
    data class OnMapCategorySelected(val categoryType: CategoryType) : MateUiAction
    data class OnShowListClicked(val isShowing: Boolean) : MateUiAction
    data object OnCurrentLocationClicked : MateUiAction
    data class OnSearchingListClicked(val isShowing: Boolean) : MateUiAction
    data class OnMarkerClicked(val poiId: Int) : MateUiAction
}

enum class CategoryType(
    val title: Int,
    val iconResource: Int?,
    val mateSelectMarkerIcon: Int?,
    val mateUnselectMarkerIcon: Int?,
    val selectMarkerIcon: Int?,
    val unselectMarkerIcon: Int?,
) {
    All(R.string.category_type_all, null, null, null, null, null),
    Experience(
        R.string.category_type_experience,
        R.drawable.ic_experience,
        R.drawable.ic_select_mate_experience,
        R.drawable.ic_unselect_mate_experience,
        R.drawable.ic_select_experience,
        R.drawable.ic_unselect_experience,
    ),
    Culture(
        R.string.category_type_culture,
        R.drawable.ic_culture,
        R.drawable.ic_select_mate_culture,
        R.drawable.ic_unselect_mate_culture,
        R.drawable.ic_select_culture,
        R.drawable.ic_unselect_culture,
    ),
    Show(
        R.string.category_type_show,
        R.drawable.ic_show,
        R.drawable.ic_select_mate_show,
        R.drawable.ic_unselect_mate_show,
        R.drawable.ic_select_show,
        R.drawable.ic_unselect_show,
    ),
    Nature(
        R.string.category_type_nature,
        R.drawable.ic_nature,
        R.drawable.ic_select_mate_nature,
        R.drawable.ic_unselect_mate_nature,
        R.drawable.ic_select_nature,
        R.drawable.ic_unselect_nature,
    ),
    Course(
        R.string.category_type_course,
        R.drawable.ic_course,
        R.drawable.ic_select_mate_course,
        R.drawable.ic_unselect_mate_course,
        R.drawable.ic_select_course,
        R.drawable.ic_unselect_course,
    ),
    History(
        R.string.category_type_history,
        R.drawable.ic_history,
        R.drawable.ic_select_mate_course,
        R.drawable.ic_unselect_mate_course,
        R.drawable.ic_select_course,
        R.drawable.ic_unselect_course,
    ),
    Leports(
        R.string.category_type_leports,
        R.drawable.ic_leports,
        R.drawable.ic_select_mate_leports,
        R.drawable.ic_unselect_mate_leports,
        R.drawable.ic_select_leports,
        R.drawable.ic_unselect_leports,
    ),
    Accommodate(
        R.string.category_type_accommodate,
        R.drawable.ic_accommodate,
        R.drawable.ic_select_mate_accommodate,
        R.drawable.ic_unselect_mate_accommodate,
        R.drawable.ic_select_accommodate,
        R.drawable.ic_unselect_accommodate,
    ),
    Shopping(
        R.string.category_type_shopping,
        R.drawable.ic_shopping,
        R.drawable.ic_select_mate_shopping,
        R.drawable.ic_unselect_mate_shopping,
        R.drawable.ic_select_shopping,
        R.drawable.ic_unselect_shopping,
    ),
    Food(
        R.string.category_type_food,
        R.drawable.ic_food,
        R.drawable.ic_select_mate_food,
        R.drawable.ic_unselect_mate_food,
        R.drawable.ic_select_food,
        R.drawable.ic_unselect_food,
    ),
}
