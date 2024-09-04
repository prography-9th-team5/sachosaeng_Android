package com.example.sachosaeng.core.usecase.category

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.data.repository.category.CategoryRepository
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