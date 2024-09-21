package com.example.sachosaeng.core.usecase.bookmark

import com.example.sachosaeng.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarkArticleUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(bookmarkId: Int) =
        bookmarkRepository.deleteBookmarkArticle(bookmarkId = bookmarkId)
}