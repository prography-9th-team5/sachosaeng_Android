package com.example.sachosaeng.data.repository.article

import com.example.sachosaeng.core.model.SimilarArticle
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getSimilarArticle(categoryId: Int, voteId: Int, size: Int): Flow<SimilarArticle>
}