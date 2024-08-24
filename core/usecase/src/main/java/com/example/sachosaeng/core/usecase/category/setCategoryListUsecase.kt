package com.example.sachosaeng.core.usecase.category

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.data.repository.category.CategoryRepository

class setCategoryListUsecase(
    private val repository: CategoryRepository,
    val categoryList: List<Category>
) : NoParameterUseCase<Unit> {
    override operator fun invoke() = repository.setCategoryList(categoryList)
}