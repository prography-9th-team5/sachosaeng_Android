package com.example.sachosaeng.data.repository.vote

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.model.VoteInfo
import com.example.sachosaeng.core.model.VoteList
import com.example.sachosaeng.data.model.vote.VoteDetailInfoResponse
import com.example.sachosaeng.data.model.vote.VoteInfoResponse
import com.example.sachosaeng.data.model.vote.VoteListInfoResponse

object VoteMapper {
    fun VoteInfoResponse.toDomain() = VoteInfo(
        id = voteId,
        title = title,
        category = Category(
            id = category?.categoryId,
            color = category?.backgroundColor ?: "#000000",
            name = category?.name ?: "",
            imageUrl = category?.iconUrl,
        ),
        isClosed = isClosed,
        voteCount = participantCount,
    )

    fun VoteListInfoResponse.toDomain() : VoteList {
        return this.let {
            VoteList(
                category = Category(
                    id = it.category.categoryId,
                    color = it.category.backgroundColor ?: "#000000",
                    name = it.category.name,
                    imageUrl = it.category.iconUrl,
                ),
                voteInfo = it.votes.map { voteInfoResponse -> voteInfoResponse.toDomain() }
            )
        }
    }

    fun VoteDetailInfoResponse.toDomain(): Vote {
        return Vote(
            id = voteId,
            title = title,
            category = Category(
                id = category?.categoryId,
                color = category?.backgroundColor ?: "#000000",
                name = category?.name ?: "",
                imageUrl = category?.iconUrl,
            ),
            isClosed = isClosed,
            count = participantCount ?: 0,
            option = voteOptions.map { voteOptionResponse ->
                voteOptionResponse.content
            },
            selectedOption = listOf(chosenVoteOptionId.firstOrNull() ?: -1).first().toString(),
        )
    }
}