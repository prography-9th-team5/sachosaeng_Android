package com.example.sachosaeng.data.repository.article

import com.example.sachosaeng.core.model.SimilarArticle
import com.example.sachosaeng.data.model.article.SimilarArticleResponse

object ArticleMapper {
    fun SimilarArticleResponse.toDomain(): SimilarArticle {
        return SimilarArticle(
            articleId = informationId,
            title = title
        )
    }
}