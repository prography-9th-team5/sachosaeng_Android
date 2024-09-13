package com.example.sachosaeng.core.usecase.bookmark

import com.example.sachosaeng.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarkListUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(bookmarkIds: List<Int>) {
        bookmarkRepository.deleteBookmarkList(bookmarkIds)
    }
}