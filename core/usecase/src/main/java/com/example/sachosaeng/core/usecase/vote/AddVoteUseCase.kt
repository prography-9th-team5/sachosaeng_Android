package com.example.sachosaeng.core.usecase.vote

import com.sachosaeng.app.data.repository.vote.VoteRepository
import javax.inject.Inject

class AddVoteUseCase @Inject constructor(
    private val voteRepository: VoteRepository
) {
    suspend operator fun invoke(
        title: String,
        options: List<String>,
        categoryId: Int,
        isMultipleChoiceAllowed: Boolean
    ) = voteRepository.addVote(
        title = title,
        isMultipleChoiceAllowed = isMultipleChoiceAllowed,
        options = options,
        categoryId = categoryId
    )
}