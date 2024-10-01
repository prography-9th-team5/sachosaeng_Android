package com.example.sachosaeng.feature.mypage.modifyCategory

import com.example.sachosaeng.core.model.Category

data class ModifyCategoryUiState (
    val selectedList : List<Category> = listOf(),
    val visibleList : List<Category> = listOf(),
    val modifyButtonVisibility : Boolean = true
)