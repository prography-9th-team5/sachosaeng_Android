package com.sachosaeng.app.core.usecase.bookmark

import com.sachosaeng.app.core.model.Bookmark
import com.sachosaeng.app.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarksUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(bookmarkIds: List<Bookmark>) =
        bookmarkRepository.deleteBookmarks(bookmarkIds = bookmarkIds.map { it.bookmarkId })
}