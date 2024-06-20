package com.example.sachosaeng.feature.home

import com.example.sachosaeng.core.domain.model.Category

data class HomeScreenUiState(
    val profileImageUrl: String = "",
    val voteList: List<VoteList>,
    val allCategory: List<Category>,
    val myCategory: List<Category>,
    val selectedCategory: Category? = null
) {
    companion object {
        fun createDefault(): HomeScreenUiState {
            return HomeScreenUiState(
                profileImageUrl = "",
                allCategory = listOf(
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                ),
                myCategory = listOf(
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                    Category(),
                ),
                selectedCategory = Category(),
                voteList = listOf(
                    VoteList(
                        category = "category1",
                        voteInfo = listOf(
                            VoteInfo(
                                title = "첫 출근 날 팀원들에게 어떻게 인사하는게 좋을까요",
                                imageUrl = "https://picsum.photos/200/200",
                                voteCount = 0,
                            ),
                            VoteInfo(
                                title = "상사와의 전화에서 마무리 말을 어떻게 하는게 좋을까용?",
                                imageUrl = "https://picsum.photos/200/200",
                                voteCount = 0,
                            ),
                            VoteInfo(
                                title = "신입사원 첫 회식 추천해 주세요",
                                imageUrl = "https://picsum.photos/200/200",
                                voteCount = 0,
                            )
                        ),
                    ),
                    VoteList(
                        category = "category2",
                        voteInfo = listOf(
                            VoteInfo(
                                title = "첫 출근 날 팀원들에게 어떻게 인사하는게 좋을까요",
                                imageUrl = "https://picsum.photos/200/200",
                                voteCount = 0,
                            ),
                            VoteInfo(
                                title = "상사와의 전화에서 마무리 말을 어떻게 하는게 좋을까용?",
                                imageUrl = "https://picsum.photos/200/200",
                                voteCount = 0,
                            ),
                            VoteInfo(
                                title = "신입사원 첫 회식 추천해 주세요",
                                imageUrl = "https://picsum.photos/200/200",
                                voteCount = 0,
                            )
                        ),
                    ),
                    VoteList(
                        category = "category3",
                        voteInfo = listOf(
                            VoteInfo(
                                title = "첫 출근 날 팀원들에게 어떻게 인사하는게 좋을까요",
                                imageUrl = "https://picsum.photos/200/200",
                                voteCount = 0,
                            ),
                            VoteInfo(
                                title = "상사와의 전화에서 마무리 말을 어떻게 하는게 좋을까용?",
                                imageUrl = "https://picsum.photos/200/200",
                                voteCount = 0,
                            ),
                            VoteInfo(
                                title = "신입사원 첫 회식 추천해 주세요",
                                imageUrl = "https://picsum.photos/200/200",
                                voteCount = 0,
                            )
                        ),
                    )
                )
            )
        }
    }
}

data class VoteList(
    val category: String,
    val voteInfo: List<VoteInfo>
)

data class VoteInfo(
    val title: String,
    val imageUrl: String,
    val voteCount: Int,
)