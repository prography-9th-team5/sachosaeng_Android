package com.example.sachosaeng.feature.vote

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.util.ResourceProvider
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.Vote
import com.sachosaeng.app.core.usecase.vote.GetSingleVoteUsecase
import com.sachosaeng.app.feature.vote.navigation.VOTE_PREVIEW_DETAIL_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class VotePreviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSingleVoteUsecase: GetSingleVoteUsecase,
    val resourceProvider: ResourceProvider
) : ViewModel(),
    ContainerHost<VotePreviewUiState, VotePreviewSideEffect> {
    private val votePreviewId = savedStateHandle.get<Int>(VOTE_PREVIEW_DETAIL_ID)
    override val container: Container<VotePreviewUiState, VotePreviewSideEffect> =
        container(VotePreviewUiState())

    init {
        Log.d("VotePreviewViewModel", "$votePreviewId")
        getVoteContent()
    }

    private fun getVoteContent() = intent {
        votePreviewId?.let {
            getSingleVoteUsecase(votePreviewId).collectLatest { vote ->
                Log.d("VotePreviewViewModel", "$vote")
                reduce {
                    state.copy(
                        title = vote?.title ?: "",
                        description = vote?.description ?: "",
                        canMultipleChoice = vote?.isMultipleChoiceAllowed ?: false,
                        options = vote?.option?.map { it.content } ?: emptyList(),
                        category = vote?.category ?: Category()
                    )
                }
            }
        }
    }
}

sealed class VotePreviewSideEffect {
    data class NavigateToVotePreview(val voteId: Int) : VotePreviewSideEffect()
    data class ShowSnackBar(val message: String) : VotePreviewSideEffect()
}

