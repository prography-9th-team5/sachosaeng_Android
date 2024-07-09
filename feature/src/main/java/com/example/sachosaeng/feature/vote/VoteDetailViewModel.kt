package com.example.sachosaeng.feature.vote

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sachosaeng.core.domain.model.Vote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class VoteDetailViewModel @Inject constructor(
//    private val getSingleVoteUsecase: GetSingleVoteUsecase
): ViewModel(), ContainerHost<Vote, Unit> {
    override val container : Container<Vote, Unit> = container(Vote())
    suspend fun getVoteContent() = intent {
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