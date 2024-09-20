package com.example.sachosaeng.data.repository.bookmark

import com.example.sachosaeng.core.model.Bookmark
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.data.api.BookmarkService
import com.example.sachosaeng.data.repository.bookmark.BookmarkMapper.toDomain
import com.example.sachosaeng.data.repository.bookmark.BookmarkMapper.toRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkService: BookmarkService
) : BookmarkRepository {
    override fun deleteBookmark(bookmarkId: Int): Flow<Unit> = flow {
        bookmarkService.deleteBookmark(bookmarkId).getOrThrow().data?.let { emit(it) }
    }

    override fun deleteBookmarks(bookmarkIds: List<Int>): Flow<Unit> = flow {
        bookmarkService.deleteBookmarks(bookmarkIds.toRequest()).getOrThrow().data?.let { emit(it) }
    }

    override fun bookmarkVote(voteId: Int): Flow<Unit> = flow {
        bookmarkService.voteBookmark(voteId.toRequest()).getOrThrow().data?.let { emit(it) }
    }

    override fun getBookmarkList(categoryId: Int): Flow<List<Bookmark>> = flow {
        bookmarkService.getBookmarkList(categoryId).getOrThrow().data?.let { emit(it.toDomain()) }
    }

    override fun getAllBookmarkList(): Flow<List<Bookmark>> = flow {
        bookmarkService.getAllBookmarkList().getOrThrow().data?.let { emit(it.toDomain()) }
    }
}