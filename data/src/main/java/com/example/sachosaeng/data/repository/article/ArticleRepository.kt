package com.sachosaeng.app.data.repository.article

import com.sachosaeng.app.core.model.SimilarArticle
import com.sachosaeng.app.core.model.SimilarArticleDetail
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getSimilarArticle(categoryId: Int, voteId: Int, size: Int): Flow<List<SimilarArticle>>
    fun getArticleDetail(informationId: Int, categoryId: Int?): Flow<SimilarArticleDetail>
}