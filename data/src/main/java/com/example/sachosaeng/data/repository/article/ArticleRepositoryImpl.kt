package com.example.sachosaeng.data.repository.article

import com.example.sachosaeng.core.model.SimilarArticle
import com.example.sachosaeng.core.model.SimilarArticleDetail
import com.example.sachosaeng.data.api.ArticleService
import com.example.sachosaeng.data.model.bookmark.SingleArticleBookmarkRequest
import com.example.sachosaeng.data.repository.article.ArticleMapper.toDomain
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
        articleService.getSimilarArticle(categoryId, voteId, size).getOrThrow().data?.toDomain()
            ?.let { emit(it) }
    }

    override fun getArticleDetail(
        informationId: Int,
        categoryId: Int
    ): Flow<SimilarArticleDetail> = flow {
        articleService.getArticleDetail(informationId = informationId, categoryId = categoryId)
            .getOrThrow().data?.toDomain()
            ?.let { emit(it) }
    }
}