package com.example.sachosaeng.feature.vote

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.usecase.vote.GetSingleVoteUsecase
import com.example.sachosaeng.feature.vote.navigation.VOTE_DETAIL_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class VoteDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSingleVoteUsecase: GetSingleVoteUsecase
): ViewModel(), ContainerHost<Vote, Unit> {
    private val voteDetailId = savedStateHandle.get<Int>(VOTE_DETAIL_ID)
    override val container : Container<Vote, Unit> = container(Vote())
    fun getVoteContent() = intent {
        voteDetailId?.let { getSingleVoteUsecase(voteDetailId) }
    }

    fun bookmarkVote() = intent {
        Log.e("VoteDetailViewModel", "bookmarkVote")
        reduce {
            state.copy(isBookmarked = !state.isBookmarked)
        }
    }

    fun vote(selectedOption: String) = intent {
        reduce {
            state.copy(count = state.count + 1)
        }
    }
}