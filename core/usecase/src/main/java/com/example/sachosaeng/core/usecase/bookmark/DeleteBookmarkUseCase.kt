package com.example.sachosaeng.core.usecase.bookmark

import com.example.sachosaeng.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(bookmarkId: Int) =
        bookmarkRepository.deleteBookmark(bookmarkId = bookmarkId)
}