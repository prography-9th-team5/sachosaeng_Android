package com.example.sachosaeng.core.usecase.article

import com.example.sachosaeng.core.model.SimilarArticle
import com.example.sachosaeng.data.repository.article.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarArticleUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    operator fun invoke(categoryId: Int, voteId: Int, size: Int = 3): Flow<List<SimilarArticle>> =
        articleRepository.getSimilarArticle(categoryId, voteId, size)
}