package com.example.sachosaeng.feature.vote

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.usecase.bookmark.DeleteBookmarkUseCase
import com.example.sachosaeng.core.usecase.vote.BookmarkVoteUsecase
import com.example.sachosaeng.core.usecase.vote.GetSingleVoteUsecase
import com.example.sachosaeng.feature.vote.navigation.VOTE_DETAIL_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class VoteDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val deleteBookmarkUsecase: DeleteBookmarkUseCase,
    private val bookmarkVoteUsecase: BookmarkVoteUsecase,
    private val getSingleVoteUsecase: GetSingleVoteUsecase
) : ViewModel(), ContainerHost<Vote, Unit> {
    private val voteDetailId = savedStateHandle.get<Int>(VOTE_DETAIL_ID)
    override val container: Container<Vote, Unit> = container(Vote())

    fun getVoteContent() = intent {
        voteDetailId?.let {
            getSingleVoteUsecase(voteDetailId).collectLatest { vote ->
                reduce {
                    state.copy(
                        id = vote?.id ?: 0,
                        title = vote?.title ?: "",
                        count = vote?.count ?: 0,
                        category = vote?.category ?: Category(),
                        isBookmarked = vote?.isBookmarked ?: false,
                        selectedOption = vote?.selectedOption ?: "",
                        isClosed = vote?.isClosed ?: false,
                        option = vote?.option ?: listOf()
                    )
                }
            }
        } ?: run {
            Log.e("VoteDetailViewModel", "voteDetailId is null")
        }
    }

    fun bookmarkButtonClick() = intent {
        when (state.isBookmarked) {
            true -> deleteBookmark()
            false -> bookmarkVote()
        }
    }

    private fun deleteBookmark() = intent {
        deleteBookmarkUsecase(state).collectLatest {
            reduce {
                state.copy(isBookmarked = false)
            }
        }
    }

    private fun bookmarkVote() = intent {
        bookmarkVoteUsecase(state).collectLatest {
            reduce {
                state.copy(isBookmarked = true)
            }
        }
    }

    fun vote(selectedOption: String) = intent {
        reduce {
            state.copy(count = state.count + 1)
        }
    }
}
