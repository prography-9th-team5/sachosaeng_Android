package com.example.sachosaeng.data.repository.category

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.data.model.category.AllCategoryIconResponse
import com.example.sachosaeng.data.model.category.CategoryResponse

object CategoryMapper {
    fun List<CategoryResponse>.toDomain(): List<Category> = this.map {
        Category(
            id = it.categoryId ?: 0,
            name = it.name,
            imageUrl = it.iconUrl,
            color = it.backgroundColor ?: "",
            textColor = it.textColor
        )
    }

    //todo: 이걸 어디다 둔다..
    fun AllCategoryIconResponse.toDomain(): Category = Category(
        id = ALL_CATEGORY_ID,
        name = ALL_CATEGORY_NAME,
        imageUrl = this.iconUrl,
        color = this.backgroundColor ?: "",
        textColor = ""
    )
}

const val ALL_CATEGORY_ID = 1
const val ALL_CATEGORY_NAME = "전체 보기"