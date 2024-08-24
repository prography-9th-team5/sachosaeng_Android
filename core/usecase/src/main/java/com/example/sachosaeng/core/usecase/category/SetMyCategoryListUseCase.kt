package com.example.sachosaeng.core.usecase.category

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetMyCategoryListUseCase @Inject constructor(
    private val repository: CategoryRepository,
) : Usecase<List<Category>, Flow<Unit>> {
    override operator fun invoke(categoryList: List<Category>): Flow<Unit> = repository.setCategoryList(categoryList)
}