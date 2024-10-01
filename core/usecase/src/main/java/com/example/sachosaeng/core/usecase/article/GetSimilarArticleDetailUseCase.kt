package com.sachosaeng.app.core.usecase.article

import com.sachosaeng.app.core.model.SimilarArticleDetail
import com.sachosaeng.app.data.repository.article.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarArticleDetailUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    operator fun invoke(articleId: Int, categoryId: Int): Flow<SimilarArticleDetail> =
        articleRepository.getArticleDetail(categoryId = categoryId, informationId = articleId)
}