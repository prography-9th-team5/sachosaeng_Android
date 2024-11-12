package com.sachosaeng.app.data.repository.category

import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.util.constant.ColorConstant.GS_BLACK_CODE
import com.sachosaeng.app.data.model.category.AllCategoryIconResponse
import com.sachosaeng.app.data.model.category.CategoriesResponse
import com.sachosaeng.app.data.model.category.CategoryResponse
import com.sachosaeng.app.data.model.category.SetCategoryRequest

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

    fun CategoryResponse.toDomain(): Category = Category(
        id = this.categoryId ?: 0,
        name = this.name,
        imageUrl = this.iconUrl,
        color = this.backgroundColor ?: GS_BLACK_CODE,
        textColor = this.textColor ?: GS_BLACK_CODE
    )

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

const val ALL_CATEGORY_ID = -1
const val ALL_CATEGORY_NAME = "전체 보기"