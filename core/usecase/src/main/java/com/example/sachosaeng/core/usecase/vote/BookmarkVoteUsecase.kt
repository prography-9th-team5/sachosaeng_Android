package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.data.repository.bookmark.BookmarkRepository
import javax.inject.Inject

class BookmarkVoteUsecase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke(voteId: Int) = repository.bookmarkVote(voteId = voteId)
}