package com.example.sachosaeng.core.usecase.article

import com.example.sachosaeng.core.model.SimilarArticleDetail
import com.example.sachosaeng.data.repository.article.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarArticleDetailUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    operator fun invoke(articleId: Int, categoryId: Int): Flow<SimilarArticleDetail> =
        articleRepository.getArticleDetail(categoryId = categoryId, informationId = articleId)
}