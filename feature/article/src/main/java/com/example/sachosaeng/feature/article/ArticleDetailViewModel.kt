package com.sachosaeng.app.feature.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sachosaeng.app.core.usecase.article.BookmarkArticleUseCase
import com.sachosaeng.app.core.usecase.article.GetSimilarArticleDetailUseCase
import com.sachosaeng.app.core.usecase.bookmark.DeleteBookmarkArticleUseCase
import com.sachosaeng.app.feature.article.navigation.ARTICLE_CATEGORY_ID
import com.sachosaeng.app.feature.article.navigation.ARTICLE_DETAIL_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSimilarArticleDetailUseCase: GetSimilarArticleDetailUseCase,
    private val bookmarkArticleUseCase: BookmarkArticleUseCase,
    private val deleteBookmarkArticleUseCase: DeleteBookmarkArticleUseCase
) : ViewModel(), ContainerHost<ArticleDetailUiState, Unit> {
    private val articleDetailId = savedStateHandle.get<Int>(ARTICLE_DETAIL_ID)
    private val categoryId = savedStateHandle.get<Int>(ARTICLE_CATEGORY_ID)
    override val container: Container<ArticleDetailUiState, Unit> =
        container(ArticleDetailUiState())

    init {
        fetchArticleDetail(articleId = articleDetailId, categoryId = categoryId)
    }

    private fun fetchArticleDetail(articleId: Int?, categoryId: Int?) = intent {
        if (articleId != null && categoryId != null) getSimilarArticleDetailUseCase(
            articleId,
            categoryId
        ).collectLatest {
            reduce {
                state.copy(
                    articleId = articleId,
                    title = it.title,
                    subtitle = it.subtitle,
                    content = it.content,
                    category = it.category,
                    isBookmarked = it.isBookmarked,
                    referenceName = it.referenceName
                )
            }
        }
    }

    fun onBookmarkArticle() = intent {
        state.articleId?.let {
            when (state.isBookmarked) {
                true -> deleteBookmarkArticleUseCase(it).collectLatest {
                    reduce {
                        state.copy(isBookmarked = false)
                    }
                }
                false -> bookmarkArticleUseCase(it).collectLatest {
                    reduce {
                        state.copy(isBookmarked = true)
                    }
                }
            }
        }
    }
}