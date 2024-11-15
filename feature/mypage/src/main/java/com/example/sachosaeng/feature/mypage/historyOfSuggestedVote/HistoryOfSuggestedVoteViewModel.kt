package com.example.sachosaeng.feature.mypage.historyOfSuggestedVote

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.usecase.vote.GetHistoryOfSuggestedVoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
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
}

sealed class HistoryOfSuggestedVoteSideEffect {
    data class ShowToast(val message: String) : HistoryOfSuggestedVoteSideEffect()
}