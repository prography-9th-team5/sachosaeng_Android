package com.sachosaeng.app.data.repository.article

import com.sachosaeng.app.core.model.SimilarArticle
import com.sachosaeng.app.core.model.SimilarArticleDetail
import com.sachosaeng.app.data.model.article.SimilarArticleDetailResponse
import com.sachosaeng.app.data.model.article.SimilarArticleResponse
import com.sachosaeng.app.data.repository.article.ArticleMapper.toDomain
import com.sachosaeng.app.data.repository.category.CategoryMapper.toDomain

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