package com.example.sachosaeng.data.repository.vote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sachosaeng.data.model.vote.SuggestedVote
import com.sachosaeng.app.core.model.SuggestedVoteInfo
import com.sachosaeng.app.data.api.VoteService
import com.sachosaeng.app.data.repository.vote.VoteMapper.toDomain

internal class VoteHistoryPagingSource(
    private val voteService: VoteService,
) : PagingSource<String, SuggestedVoteInfo>() {

    override fun getRefreshKey(state: PagingState<String, SuggestedVoteInfo>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.id.toString()
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, SuggestedVoteInfo> {
        return try {
            val loadKey = params.key ?: ""

            val response = voteService.getHistoryOfSuggestedVote(
                cursor = loadKey.toIntOrNull(),
                size = params.loadSize
            ).getOrThrow()

            LoadResult.Page(
                data = response.data?.toDomain() ?: emptyList(),
                prevKey = null,
                nextKey = if (response.data?.nextCursor == null) null else response.data.nextCursor.toString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
