package com.example.sachosaeng.core.usecase.article

import com.example.sachosaeng.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class BookmarkArticleUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke(articleId: Int) = repository.bookmarkArticle(articleId = articleId)
}