package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.data.repository.user.UserRepository
import com.sachosaeng.app.data.repository.vote.VoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SetVoteUseCase @Inject constructor(
    private val voteRepository: VoteRepository,
    private val userRepository: UserRepository
) {
    operator fun invoke(voteId: Int, optionId: List<Int?>): Flow<Boolean> {
        return voteRepository.setVote(voteId, optionId)
            .onEach { isLevelUp ->
                userRepository.setLevelUpNotification(isLevelUp).launchIn(CoroutineScope(Dispatchers.IO))
            }
    }
}