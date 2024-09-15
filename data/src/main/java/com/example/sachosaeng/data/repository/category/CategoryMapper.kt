package com.example.sachosaeng.data.repository.category

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.util.constant.ColorConstant.GS_BLACK_CODE
import com.example.sachosaeng.data.model.category.AllCategoryIconResponse
import com.example.sachosaeng.data.model.category.CategoriesResponse
import com.example.sachosaeng.data.model.category.CategoryResponse
import com.example.sachosaeng.data.model.category.SetCategoryRequest

object CategoryMapper {
    fun CategoriesResponse.toDomain(): List<Category> = this.categories.map {
        Category(
            id = it.categoryId ?: 0,
            name = it.name,
            imageUrl = it.iconUrl,
            color = it.backgroundColor ?: GS_BLACK_CODE,
            textColor = it.textColor ?: GS_BLACK_CODE
        )
    }

    fun AllCategoryIconResponse.toDomain(): Category = Category(
        id = ALL_CATEGORY_ID,
        name = ALL_CATEGORY_NAME,
        imageUrl = this.iconUrl,
        color = this.backgroundColor ?: "",
        textColor = ""
    )

    fun List<Category>.toRequest(): SetCategoryRequest =
        this.map { it.id }.let { SetCategoryRequest(it) }
}

const val ALL_CATEGORY_ID = 1
const val ALL_CATEGORY_NAME = "전체 보기"