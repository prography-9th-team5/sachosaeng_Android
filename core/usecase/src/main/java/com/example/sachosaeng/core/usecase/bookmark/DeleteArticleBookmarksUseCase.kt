package com.sachosaeng.app.core.usecase.bookmark

import com.sachosaeng.app.core.model.Bookmark
import com.sachosaeng.app.core.model.BookmarkType
import com.sachosaeng.app.data.repository.bookmark.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteArticleBookmarksUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(bookmarks: List<Bookmark>): Flow<Unit> {
       return bookmarkRepository.deleteBookmarkedArticle(bookmarks)
    }
}