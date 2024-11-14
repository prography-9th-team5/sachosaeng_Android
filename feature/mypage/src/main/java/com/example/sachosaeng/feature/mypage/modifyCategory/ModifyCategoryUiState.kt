package com.sachosaeng.app.feature.mypage.modifyCategory

import com.sachosaeng.app.core.model.Category

data class ModifyCategoryUiState (
    val selectedList : List<Category> = listOf(),
    val visibleList : List<Category> = listOf(),
    val modifyButtonVisibility : Boolean = true
)