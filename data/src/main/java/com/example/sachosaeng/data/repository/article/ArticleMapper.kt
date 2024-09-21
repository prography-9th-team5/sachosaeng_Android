package com.example.sachosaeng.data.repository.article

import com.example.sachosaeng.core.model.SimilarArticle
import com.example.sachosaeng.core.model.SimilarArticleDetail
import com.example.sachosaeng.data.model.article.SimilarArticleDetailResponse
import com.example.sachosaeng.data.model.article.SimilarArticleResponse
import com.example.sachosaeng.data.repository.article.ArticleMapper.toDomain
import com.example.sachosaeng.data.repository.category.CategoryMapper.toDomain

object ArticleMapper {
    fun SimilarArticleResponse.toDomain(): List<SimilarArticle> {
        return information.map {
            SimilarArticle(
                articleId = it.informationId,
                title = it.title
            )
        }
    }

    fun SimilarArticleDetailResponse.toDomain(): SimilarArticleDetail {
        return SimilarArticleDetail(
            articleId = this.informationId,
            title = this.title,
            subtitle = this.subtitle ?: "",
            content = this.content,
            category = this.category.toDomain(),
            isBookmarked = this.isBookmarked,
            referenceName = this.referenceName
        )
    }
}