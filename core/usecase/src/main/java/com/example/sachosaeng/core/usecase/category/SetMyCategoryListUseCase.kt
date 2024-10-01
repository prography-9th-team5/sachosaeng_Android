package com.sachosaeng.app.core.usecase.category

import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.usecase.Usecase
import com.sachosaeng.app.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetMyCategoryListUseCase @Inject constructor(
    private val repository: CategoryRepository,
) : Usecase<List<Category>, Flow<Unit>> {
    override operator fun invoke(param: List<Category>): Flow<Unit> = repository.setCategoryList(param)
}