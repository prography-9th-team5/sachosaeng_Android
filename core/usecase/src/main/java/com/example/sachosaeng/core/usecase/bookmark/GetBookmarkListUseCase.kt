package com.example.sachosaeng.core.usecase.bookmark

import com.example.sachosaeng.core.model.Bookmark
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.data.repository.bookmark.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkListUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(category: Category? = null): Flow<List<Bookmark>> {
        return when (category == null) {
            true -> bookmarkRepository.getAllBookmarkList()
            false -> bookmarkRepository.getBookmarkList(categoryId = category.id)
        }
    }
}