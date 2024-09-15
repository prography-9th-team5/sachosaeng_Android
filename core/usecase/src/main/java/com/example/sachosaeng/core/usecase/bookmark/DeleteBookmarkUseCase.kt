package com.example.sachosaeng.core.usecase.bookmark

import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(bookmark: Vote) = bookmarkRepository.deleteBookmark(bookmarkId = bookmark.id)
}