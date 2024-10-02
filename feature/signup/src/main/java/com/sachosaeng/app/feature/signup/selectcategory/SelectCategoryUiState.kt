package com.sachosaeng.app.feature.signup.selectcategory

import com.sachosaeng.app.core.model.Category

data class SelectCategoryUiState(
    val categoryList: List<Category> = emptyList(),
    val selectedCategoryList: List<Category> = emptyList(),
    val isAnyCategorySelected: Boolean = false
)