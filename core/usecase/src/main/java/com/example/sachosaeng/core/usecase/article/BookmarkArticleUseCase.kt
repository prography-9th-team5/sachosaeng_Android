package com.sachosaeng.app.core.usecase.article

import com.sachosaeng.app.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class BookmarkArticleUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke(articleId: Int) = repository.bookmarkArticle(articleId = articleId)
}