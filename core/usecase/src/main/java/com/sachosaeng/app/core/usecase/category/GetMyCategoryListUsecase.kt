package com.sachosaeng.app.core.usecase.category

import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.usecase.NoParameterUseCase
import com.sachosaeng.app.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyCategoryListUsecase @Inject constructor(private val repository: CategoryRepository) :
    NoParameterUseCase<Flow<List<Category>>> {
    override operator fun invoke() = repository.getMyCategoryList()
}