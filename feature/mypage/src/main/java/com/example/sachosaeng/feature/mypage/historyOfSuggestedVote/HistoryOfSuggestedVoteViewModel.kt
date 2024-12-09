package com.example.sachosaeng.feature.mypage.historyOfSuggestedVote

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.usecase.vote.GetHistoryOfSuggestedVoteUseCase
import com.sachosaeng.app.core.model.SuggestedVoteInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HistoryOfSuggestedVoteViewModel @Inject constructor(
    val getHistoryOfSuggestedVoteUseCase: GetHistoryOfSuggestedVoteUseCase
) : ViewModel(), ContainerHost<HistoryOfSuggestedVoteUiState, HistoryOfSuggestedVoteSideEffect> {
    override val container =
        container<HistoryOfSuggestedVoteUiState, HistoryOfSuggestedVoteSideEffect>(
            HistoryOfSuggestedVoteUiState()
        )

    init {
        getInitialHistoryOfSuggestedVote()
    }

    private fun getInitialHistoryOfSuggestedVote() = intent {
        reduce {
            state.copy(
                voteList = getHistoryOfSuggestedVoteUseCase()
            )
        }
    }

    fun onVoteItemClick(voteInfo: SuggestedVoteInfo) = intent {
    }
}

sealed class HistoryOfSuggestedVoteSideEffect {
    data class ShowToast(val message: String) : HistoryOfSuggestedVoteSideEffect()
    data class NavigateToVotePreviewDetail(val voteId: Int) : HistoryOfSuggestedVoteSideEffect()
}