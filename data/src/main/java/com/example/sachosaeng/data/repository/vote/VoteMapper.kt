package com.example.sachosaeng.data.repository.vote

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.model.VoteInfo
import com.example.sachosaeng.core.model.VoteList
import com.example.sachosaeng.core.model.VoteOption
import com.example.sachosaeng.data.model.vote.VoteDetailInfoResponse
import com.example.sachosaeng.data.model.vote.VoteInfoResponse
import com.example.sachosaeng.data.model.vote.VoteListInfoResponse
import com.example.sachosaeng.core.util.constant.ColorConstant.GS_BLACK_CODE

object VoteMapper {
    fun VoteInfoResponse.toDomain() = VoteInfo(
        id = voteId,
        title = title,
        category = Category(
            id = category?.categoryId ?: 0,
            color = category?.backgroundColor ?: GS_BLACK_CODE,
            name = category?.name ?: "",
            imageUrl = category?.iconUrl,
        ),
        isClosed = isClosed,
        voteCount = participantCount,
        isVoted = isVoted,
    )

    fun VoteListInfoResponse.toDomain(): VoteList {
        return this.let {
            VoteList(
                category = Category(
                    id = it.category.categoryId ?: 2,
                    color = it.category.backgroundColor ?: GS_BLACK_CODE,
                    textColor = it.category.textColor ?: GS_BLACK_CODE,
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
                id = category.categoryId ?: 0,
                color = category.backgroundColor ?: GS_BLACK_CODE,
                name = category.name,
                imageUrl = category.iconUrl,
            ),
            isBookmarked = isBookmarked,
            isVoted = isVoted,
            isClosed = isClosed,
            isMultipleChoiceAllowed = isMultipleChoiceAllowed,
            count = participantCount ?: 0,
            option = voteOptions.map { voteOptionResponse ->
                VoteOption(
                    voteOptionId = voteOptionResponse.voteOptionId,
                    content = voteOptionResponse.content,
                    count = voteOptionResponse.count,
                )
            },
            selectedOptionIds = chosenVoteOptionId,
            description = description,
        )
    }
}