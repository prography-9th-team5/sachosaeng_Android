package com.sachosaeng.app.data.repository.bookmark

import com.sachosaeng.app.core.model.Bookmark
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.data.api.BookmarkService
import com.sachosaeng.app.data.model.bookmark.SingleArticleBookmarkRequest
import com.sachosaeng.app.data.repository.bookmark.BookmarkMapper.toArticleRequest
import com.sachosaeng.app.data.repository.bookmark.BookmarkMapper.toDomain
import com.sachosaeng.app.data.repository.bookmark.BookmarkMapper.toRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkService: BookmarkService
) : BookmarkRepository {
    override fun deleteBookmark(bookmarkId: Int): Flow<Unit> = flow {
        bookmarkService.deleteBookmark(bookmarkId).getOrNull()?.data?.let { emit(it) }
    }

    override fun deleteBookmarkArticle(bookmarkId: Int): Flow<Unit> {
        return flow {
            bookmarkService.deleteArticleBookmark(bookmarkId).getOrNull()?.data?.let { emit(it) }
        }
    }

    override fun deleteBookmarks(bookmarkIds: List<Bookmark>): Flow<Unit> = flow {
        bookmarkService.deleteBookmarks(bookmarkIds.toRequest()).getOrNull()?.data?.let {
            emit(it)
        }
    }
    override fun deleteBookmarkedArticle(bookmarkIds: List<Bookmark>): Flow<Unit> = flow {
        bookmarkService.deleteArticleBookmarks(bookmarkIds.toArticleRequest()).getOrNull()?.data?.let {
            emit(it)
        }
    }

    override fun bookmarkVote(voteId: Int): Flow<Unit> = flow {
        bookmarkService.voteBookmark(voteId.toRequest()).getOrNull()?.data?.let { emit(it) }
    }

    override fun bookmarkArticle(articleId: Int): Flow<Unit> = flow {
        bookmarkService.bookmarkArticle(SingleArticleBookmarkRequest(informationId = articleId))
            .getOrNull()?.data?.let { emit(it) }
    }

    override fun getBookmarkList(categoryId: Int): Flow<List<Bookmark>> = flow {
        bookmarkService.getBookmarkList(categoryId)
            .getOrNull()?.data?.let { emit(it.toDomain()) }
    }

    override fun getAllBookmarkList(): Flow<List<Bookmark>> = flow {
        bookmarkService.getAllBookmarkList().getOrNull()?.data?.let { emit(it.toDomain()) }
    }

    override fun getAllBookmarkedArticleList(): Flow<List<Bookmark>> = flow {
        bookmarkService.getAllBookmarkedArticleList().getOrNull()?.data?.let { emit(it.toDomain()) }
    }

    override fun getBookmarkedArticleList(categoryId: Int): Flow<List<Bookmark>> = flow {
        bookmarkService.getBookmarkedArticleList(categoryId)
            .getOrNull()?.data?.let { emit(it.toDomain()) }
    }
}