package com.example.sachosaeng.data.repository.article

import com.example.sachosaeng.core.model.SimilarArticle
import com.example.sachosaeng.data.model.article.SimilarArticleResponse

object ArticleMapper {
    fun SimilarArticleResponse.toDomain(): List<SimilarArticle> {
        return information.map {
            SimilarArticle(
                articleId = it.informationId,
                title = it.title
            )
        }
    }
}