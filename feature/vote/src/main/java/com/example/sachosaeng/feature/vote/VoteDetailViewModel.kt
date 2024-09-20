package com.example.sachosaeng.feature.vote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.ui.R.drawable
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.usecase.article.GetSimilarArticleUseCase
import com.example.sachosaeng.core.usecase.bookmark.DeleteBookmarkUseCase
import com.example.sachosaeng.core.usecase.user.GetMyInfoUsecase
import com.example.sachosaeng.core.usecase.vote.BookmarkVoteUsecase
import com.example.sachosaeng.core.usecase.vote.GetSingleVoteUsecase
import com.example.sachosaeng.core.usecase.vote.SetVoteUseCase
import com.example.sachosaeng.feature.vote.navigation.VOTE_DETAIL_ID
import com.example.sachosaeng.feature.vote.navigation.VOTE_IS_DAILY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
    private val getSingleVoteUsecase: GetSingleVoteUsecase,
    private val getSimilarArticleUseCase: GetSimilarArticleUseCase,
    private val voteUseCase: SetVoteUseCase,
    private val getMyInfoUsecase: GetMyInfoUsecase
) : ViewModel(), ContainerHost<VoteDetailUiState, Unit> {
    private val voteDetailId = savedStateHandle.get<Int>(VOTE_DETAIL_ID)
    private val isDailyVote = savedStateHandle.get<Boolean>(VOTE_IS_DAILY)
    override val container: Container<VoteDetailUiState, Unit> =
        container(VoteDetailUiState())

    init {
        getVoteContent()
    }

    private fun getVoteContent() = intent {
        val voteCompleteDescriptionIcon = getVoteCompleteDescriptionImageRes().first()
        voteDetailId?.let {
            getSingleVoteUsecase(voteDetailId).collectLatest { vote ->
                reduce {
                    state.copy(
                        vote = vote ?: Vote(),
                        isCompleteState = false,
                        completeIconImageRes = voteCompleteDescriptionIcon,
                        isDailyVote = isDailyVote ?: false
                    )
                }
                getSimilarArticle()
            }
        }
    }

    private fun getVoteCompleteDescriptionImageRes() = flow {
        val userType = getMyInfoUsecase().first().let {
            when (UserType.getType(it.userType) ?: UserType.NEW_EMPLOYEE) {
                UserType.NEW_EMPLOYEE -> drawable.ic_vote_complete_newcomer
                UserType.JOB_SEEKER -> drawable.ic_vote_complete_jobseeker
                UserType.STUDENT -> drawable.ic_vote_complete_student
                UserType.OTHER -> drawable.ic_vote_complete_etc
            }
        }
        emit(userType)
    }

    fun bookmarkButtonClick() = intent {
        when (state.vote.isBookmarked) {
            true -> deleteBookmark()
            false -> bookmarkVote()
        }
    }

    private fun deleteBookmark() = intent {
        deleteBookmarkUsecase(state.vote.id).collectLatest {
            reduce {
                state.copy(state.vote.copy(isBookmarked = false))
            }
        }
    }

    private fun bookmarkVote() = intent {
        bookmarkVoteUsecase(state.vote.id).collectLatest {
            reduce {
                state.copy(state.vote.copy(isBookmarked = true))
            }
        }
    }

    fun onSelectOption(optionId: Int) = intent {
        reduce {
            val newSelectedOptionIds = if (state.vote.isMultipleChoiceAllowed) {
                if (state.vote.selectedOptionIds.contains(optionId)) {
                    state.vote.selectedOptionIds - optionId
                } else {
                    state.vote.selectedOptionIds + optionId
                }
            } else {
                listOf(optionId)
            }
            state.copy(vote = state.vote.copy(selectedOptionIds = newSelectedOptionIds))
        }
    }

    private fun getSimilarArticle() = intent {
        getSimilarArticleUseCase(
            categoryId = state.vote.category.id,
            voteId = state.vote.id
        ).collectLatest {
            reduce {
                state.copy(similarArticle = it)
            }
        }
    }

    fun vote() = intent {
        state.vote.selectedOptionIds.isNotEmpty().let {
            voteUseCase(state.vote.id, state.vote.selectedOptionIds).collectLatest {
                showVoteCompleteScreen()
            }
        }
    }

    private fun showVoteCompleteScreen() = intent {
        reduce { state.copy(isCompleteState = true) }
        delay(2000)
        getVoteContent()
    }
}