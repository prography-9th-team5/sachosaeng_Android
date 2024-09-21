package com.example.sachosaeng.core.usecase.bookmark

import com.example.sachosaeng.core.model.Bookmark
import com.example.sachosaeng.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarksUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(bookmarkIds: List<Bookmark>) =
        bookmarkRepository.deleteBookmarks(bookmarkIds = bookmarkIds.map { it.bookmarkId })
}