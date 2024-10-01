package com.sachosaeng.app.core.usecase.bookmark

import com.sachosaeng.app.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarkArticleUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(bookmarkId: Int) =
        bookmarkRepository.deleteBookmarkArticle(bookmarkId = bookmarkId)
}