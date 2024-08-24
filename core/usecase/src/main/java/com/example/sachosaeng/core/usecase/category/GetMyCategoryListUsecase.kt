package com.example.sachosaeng.core.usecase.category

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.data.repository.category.CategoryRepository

class GetMyCategoryListUsecase(private val repository: CategoryRepository) :
    NoParameterUseCase<List<Category>> {
    override operator fun invoke() = repository.getMyCategoryList()
}