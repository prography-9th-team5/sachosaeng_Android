package com.example.sachosaeng.data.repository.article

import com.example.sachosaeng.core.model.SimilarArticle
import com.example.sachosaeng.data.api.ArticleService
import com.example.sachosaeng.data.repository.article.ArticleMapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleService: ArticleService
) : ArticleRepository {
    override fun getSimilarArticle(categoryId: Int, voteId: Int, size: Int): Flow<SimilarArticle> = flow {
        articleService.getSimilarArticle(categoryId, voteId, size).getOrThrow().data?.toDomain()
            ?.let { emit(it) }
    }
}