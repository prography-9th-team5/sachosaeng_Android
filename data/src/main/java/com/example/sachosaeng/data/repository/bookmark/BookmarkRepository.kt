package com.sachosaeng.app.data.repository.bookmark

import com.sachosaeng.app.core.model.Bookmark
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun deleteBookmark(bookmarkId: Int): Flow<Unit>
    fun deleteBookmarkArticle(bookmarkId: Int): Flow<Unit>
    fun deleteBookmarks(bookmarkIds: List<Bookmark>): Flow<Unit>
    fun deleteBookmarkedArticle(bookmarkIds: List<Bookmark>): Flow<Unit>
    fun bookmarkVote(voteId: Int): Flow<Unit>
    fun bookmarkArticle(articleId: Int): Flow<Unit>
    fun getBookmarkList(categoryId: Int): Flow<List<Bookmark>>
    fun getAllBookmarkList(): Flow<List<Bookmark>>
    fun getAllBookmarkedArticleList(): Flow<List<Bookmark>>
    fun getBookmarkedArticleList(categoryId: Int): Flow<List<Bookmark>>
}