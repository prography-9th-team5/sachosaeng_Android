package com.example.sachosaeng.data.repository.search

import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.VoteList
import com.sachosaeng.app.core.util.constant.ColorConstant.GS_BLACK_CODE
import com.sachosaeng.app.data.model.vote.MultipleCategoryVoteListInfoResponse
import com.sachosaeng.app.data.model.vote.VoteListInfoResponse
import com.sachosaeng.app.data.repository.vote.VoteMapper.toDomain

object SearchMapper {
    fun MultipleCategoryVoteListInfoResponse.toDomain(): List<VoteList> {
        return this.categories.map { voteList -> voteList.toDomain() }
    }

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
                description = "",
                voteInfo = it.votes.map { voteInfoResponse -> voteInfoResponse.toDomain() }
            )
        }
    }
}