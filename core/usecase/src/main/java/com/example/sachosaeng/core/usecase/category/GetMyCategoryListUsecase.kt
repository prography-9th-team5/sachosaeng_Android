package com.example.sachosaeng.core.usecase.category

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyCategoryListUsecase @Inject constructor(private val repository: CategoryRepository) :
    NoParameterUseCase<Flow<List<Category>>> {
    override operator fun invoke() = repository.getMyCategoryList()
}