package com.example.sachosaeng.core.usecase.bookmark

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class GetBookmarkListUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(category: Category) = bookmarkRepository.getBookmarkList(categoryId = category.id)
}