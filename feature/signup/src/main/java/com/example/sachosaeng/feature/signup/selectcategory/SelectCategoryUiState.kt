package com.example.sachosaeng.feature.signup.selectcategory

import com.example.sachosaeng.core.domain.model.Category

data class SelectCategoryUiState(
    val categoryList: List<Category> = emptyList(),
    val selectedCategoryList: List<Category> = emptyList()
)