package com.sachosaeng.app.core.usecase.article

import com.sachosaeng.app.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class BookmarkVoteUsecase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke(voteId: Int) = repository.bookmarkVote(voteId = voteId)
}