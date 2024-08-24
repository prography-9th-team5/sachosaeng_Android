package com.example.sachosaeng.feature.signup.selectcategory

import com.example.sachosaeng.core.model.Category

data class SelectCategoryUiState(
    val categoryList: List<Category> = emptyList(),
    val selectedCategoryList: List<Category> = emptyList(),
    val isAnyCategorySelected: Boolean = false
)