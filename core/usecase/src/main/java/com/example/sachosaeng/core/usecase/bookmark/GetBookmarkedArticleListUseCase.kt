package com.sachosaeng.app.core.usecase.bookmark

import com.sachosaeng.app.core.model.Bookmark
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.data.repository.bookmark.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedArticleListUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(category: Category? = null): Flow<List<Bookmark>> {
        return when (category == null) {
            true -> bookmarkRepository.getAllBookmarkedArticleList()
            false -> bookmarkRepository.getBookmarkedArticleList(categoryId = category.id)
        }
    }
}