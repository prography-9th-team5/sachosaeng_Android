package com.example.sachosaeng.data.repository.bookmark

import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun deleteBookmark(bookmarkId: Int): Flow<Unit>
    fun deleteBookmarkList(bookmarkIds : List<Int>): Flow<Unit>
    fun bookmarkVote(voteId: Int): Flow<Unit>
}