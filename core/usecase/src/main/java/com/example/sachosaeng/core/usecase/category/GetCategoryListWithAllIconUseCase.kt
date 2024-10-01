package com.sachosaeng.app.core.usecase.category

import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetCategoryListWithAllIconUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke(): Flow<List<Category>> {
        val categoryList = repository.getCategoryList()
        val allIcon = repository.getAllCategoryIcon()

        return categoryList.combine(allIcon) { list, icon ->
            list.toMutableList().apply {
                add(0, icon)
            }
        }
    }
}