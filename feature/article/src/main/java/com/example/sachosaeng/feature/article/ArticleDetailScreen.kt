package com.example.sachosaeng.feature.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.component.button.BookmarkButton
import com.example.sachosaeng.core.ui.component.topappbar.SachosaengDetailTopAppBar
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_White
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ArticleDetailScreen(
    navigateToBackStack: () -> Unit,
    viewModel: ArticleDetailViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()

    ArticleDetailScreen(
        navigateToBackStack = navigateToBackStack,
        state = state.value,
        onBookmarkArticle = viewModel::onBookmarkArticle,
    )
}

@Composable
internal fun ArticleDetailScreen(
    navigateToBackStack: () -> Unit,
    state: ArticleDetailUiState,
    onBookmarkArticle: () -> Unit,
) {
    Column {
        SachosaengDetailTopAppBar(
            modifier = Modifier.background(Gs_G2),
            navigateToBackStack = navigateToBackStack,
            title = stringResource(id = R.string.article_label),
            fontWeight = FontWeight.W700,
            fontSize = 16,
            tailingComponent = {
                BookmarkButton(
                    modifier = Modifier
                        .size(18.dp),
                    isBookmarked = state.isBookmarked,
                    onBookmarkButtonClicked = onBookmarkArticle
                )
            }
        )
        LazyColumn(
            modifier = Modifier
                .background(Gs_White)
                .weight(1f)
                .padding(20.dp),
        ) {
            item {
                ArticleDetailCard(
                    title = state.title,
                    subTitle = state.subtitle,
                    content = state.content,
                    referenceName = state.referenceName
                )
            }
        }
    }
}

@Preview
@Composable
fun ArticleDetailScreenPreview() {
    ArticleDetailScreen(
        state = ArticleDetailUiState(
            title = "Article Title",
            content = "Article Content",
            subtitle = "Article Subtitle",
            category = Category()
        ),
        onBookmarkArticle = {},
        navigateToBackStack = {}
    )
}