package com.sachosaeng.app.core.usecase.article

import com.sachosaeng.app.core.model.SimilarArticle
import com.sachosaeng.app.data.repository.article.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarArticleUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    operator fun invoke(categoryId: Int, voteId: Int, size: Int = 3): Flow<List<SimilarArticle>> =
        articleRepository.getSimilarArticle(categoryId, voteId, size)
}