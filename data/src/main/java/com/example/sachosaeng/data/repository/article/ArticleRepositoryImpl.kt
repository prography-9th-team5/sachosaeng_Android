package com.sachosaeng.app.data.repository.article

import com.sachosaeng.app.core.model.SimilarArticle
import com.sachosaeng.app.core.model.SimilarArticleDetail
import com.sachosaeng.app.data.api.ArticleService
import com.sachosaeng.app.data.model.bookmark.SingleArticleBookmarkRequest
import com.sachosaeng.app.data.repository.article.ArticleMapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleService: ArticleService
) : ArticleRepository {
    override fun getSimilarArticle(
        categoryId: Int,
        voteId: Int,
        size: Int
    ): Flow<List<SimilarArticle>> = flow {
        articleService.getSimilarArticle(categoryId, voteId, size).getOrNull()?.data?.toDomain()
            ?.let { emit(it) }
    }

    override fun getArticleDetail(
        informationId: Int,
        categoryId: Int?
    ): Flow<SimilarArticleDetail> = flow {
        articleService.getArticleDetail(informationId = informationId, categoryId = categoryId)
            .getOrNull()?.data?.toDomain()
            ?.let { emit(it) }
    }
}