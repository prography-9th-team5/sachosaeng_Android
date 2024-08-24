package com.example.sachosaeng.data.repository.category

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.data.model.category.CategoryResponse

object CategoryMapper {
    fun List<CategoryResponse>.toDomain() : List<Category> = this.map {
        Category(
            id = it.categoryId,
            name = it.name,
            imageUrl = it.iconUrl,
            color = it.backgroundColor,
            textColor = it.textColor
        )
    }
}