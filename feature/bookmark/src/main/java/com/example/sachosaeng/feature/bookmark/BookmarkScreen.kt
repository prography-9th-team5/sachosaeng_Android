package com.sachosaeng.app.feature.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sachosaeng.app.core.model.Bookmark
import com.sachosaeng.app.core.model.BookmarkType
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.UserType
import com.sachosaeng.app.core.ui.component.TabRowComponentWithBottomLine
import com.sachosaeng.app.core.ui.component.button.SachoSaengButton
import com.sachosaeng.app.core.ui.component.topappbar.TopBarWithProfileImage
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.core.util.constant.IntConstant.ALL_CATEGORY_ID
import com.sachosaeng.app.feature.bookmark.component.BookmarkEmptyScreen
import com.sachosaeng.app.feature.bookmark.component.BookmarkList
import com.sachosaeng.app.feature.bookmark.component.CategoryRow
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun BookmarkScreen(
    moveToVote: (Int) -> Unit = {},
    moveToArticle: (Int, Int?) -> Unit = { _, _ -> },
    moveToMyPage: () -> Unit = {},
    showSnackBar: (String) -> Unit = {},
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is BookmarkSideEffect.ShowSnackBar -> showSnackBar(sideEffect.message)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getUserInfo()
    }

    BookmarkScreen(
        state = state.value,
        onProfileImageClicked = moveToMyPage,
        onModifyButtonClicked = viewModel::modifyBookmark,
        onCategoryClicked = viewModel::categoryClicked,
        deleteSelectedArticles = viewModel::deleteSelectedArticles,
        onBookmarkedVoteClicked = { bookmark -> moveToVote(bookmark.id) },
        onBookmarkedArticleClick = { bookmark ->
            moveToArticle(
                bookmark.id,
                state.value.selectedCategory?.id
            )
        },
        onSelectForModifyBookmark = viewModel::selectModifyBookmark,
        deleteSelectedBookmarks = viewModel::deleteSelectedBookmarks
    )
}

@Composable
internal fun BookmarkScreen(
    state: BookmarkScreenUiState,
    modifier: Modifier = Modifier,
    onModifyButtonClicked: () -> Unit = {},
    onProfileImageClicked: () -> Unit = {},
    onCategoryClicked: (Category) -> Unit = {},
    onBookmarkedVoteClicked: (Bookmark) -> Unit = {},
    onBookmarkedArticleClick: (Bookmark) -> Unit = {},
    deleteSelectedArticles: () -> Unit = {},
    deleteSelectedBookmarks: () -> Unit = {},
    onSelectForModifyBookmark: (Bookmark) -> Unit = {}
) {
    val myCategoryTabTitle = stringResource(id = R.string.vote_label)
    val allCategoryTabTitle = stringResource(id = R.string.bookmark_tab_article)
    val tabList = listOf(myCategoryTabTitle, allCategoryTabTitle)

    Column(
        modifier = modifier
            .fillMaxSize()  // 무한 크기 문제를 방지하기 위해 fillMaxSize 추가
            .background(Gs_G2)
            .padding(20.dp)
    ) {
        TopBarWithProfileImage(
            topBarContent = {
                Text(
                    text = stringResource(id = R.string.bookmark_screen_title),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W700
                )
            },
            userType = state.userType,
            onProfileImageClicked = onProfileImageClicked
        )
        CategoryRow(
            selectedCategory = state.selectedCategory,
            categories = state.allCategory,
            onModifyButtonClicked = onModifyButtonClicked,
            onCategoryClicked = onCategoryClicked,
            isModifyMode = state.isModifyMode
        )
        TabRowComponentWithBottomLine(
            screenColor = Gs_G2,
            tabs = tabList,
            contentScreens = listOf(
                {
                    VoteTabScreen(
                        state = state,
                        onBookmarkedVoteClicked = onBookmarkedVoteClicked,
                        onSelectForModifyBookmark = onSelectForModifyBookmark,
                        deleteSelectedBookmarks = { deleteSelectedBookmarks() }
                    )
                },
                {
                    ArticleTabScreen(
                        state = state,
                        onBookmarkedArticleClick = onBookmarkedArticleClick,
                        onSelectForModifyBookmark = onSelectForModifyBookmark,
                        deleteSelectedBookmarks = { deleteSelectedArticles() },
                    )
                },
            )
        )
    }
}

@Composable
private fun VoteTabScreen(
    state: BookmarkScreenUiState,
    deleteSelectedBookmarks: (List<Bookmark>) -> Unit = {},
    onBookmarkedVoteClicked: (Bookmark) -> Unit = {},
    onSelectForModifyBookmark: (Bookmark) -> Unit = {}
) {
    Column {
        if (state.bookmarkedVoteList.isEmpty()) {
            BookmarkEmptyScreen(emptyLabel = stringResource(id = R.string.bookmarked_vote_is_empty_description))
        }
        BookmarkList(
            isModifyMode = state.isModifyMode,
            bookmarks = state.bookmarkedVoteList,
            onBookmarkClicked = onBookmarkedVoteClicked,
            selectedForModifyBookmarkList = state.selectedForModifyBookmarkList,
            onSelectForModifyBookmark = onSelectForModifyBookmark
        )
        Spacer(modifier = Modifier.weight(1f))
        if (state.isModifyMode) {
            SachoSaengButton(
                enabled = state.selectedForModifyBookmarkList.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.delete_label),
                onClick = { deleteSelectedBookmarks(state.selectedForModifyBookmarkList) }
            )
        }
    }
}

@Composable
fun ArticleTabScreen(
    state: BookmarkScreenUiState,
    deleteSelectedBookmarks: (List<Bookmark>) -> Unit = {},
    onBookmarkedArticleClick: (Bookmark) -> Unit = {},
    onSelectForModifyBookmark: (Bookmark) -> Unit = {}
) {
    Column {
        if (state.bookmarkedArticleList.isEmpty()) {
            BookmarkEmptyScreen(emptyLabel = stringResource(id = R.string.bookmarked_article_is_empty_description))
        }
        BookmarkList(
            isModifyMode = state.isModifyMode,
            bookmarks = state.bookmarkedArticleList,
            onBookmarkClicked = onBookmarkedArticleClick,
            selectedForModifyBookmarkList = state.selectedForModifyBookmarkList,
            onSelectForModifyBookmark = onSelectForModifyBookmark,
        )
        Spacer(modifier = Modifier.weight(1f))
        if (state.isModifyMode) {
            SachoSaengButton(
                enabled = state.selectedForModifyBookmarkList.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.delete_label),
                onClick = { deleteSelectedBookmarks(state.selectedForModifyBookmarkList) }
            )
        }
    }
}

@Preview
@Composable
fun BookmarkScreenPreview() {
    BookmarkScreen(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gs_White),
        state = BookmarkScreenUiState(
            userType = UserType.NEW_EMPLOYEE,
            allCategory = listOf(
                Category(
                    id = 1,
                    name = "Category1",
                    color = "#FF0000",
                    textColor = "#FFFFFF"
                ),
                Category(
                    id = 2,
                    name = "Category2",
                    color = "#00FF00",
                    textColor = "#FFFFFF"
                ),
                Category(
                    id = 3,
                    name = "Category3",
                    color = "#0000FF",
                    textColor = "#FFFFFF"
                ),
            ),
            bookmarkedVoteList = listOf(),
            isModifyMode = true,
            selectedForModifyBookmarkList = listOf(
                Bookmark(
                    bookmarkId = 1,
                    id = 1,
                    title = "Bookmark1",
                    description = "Description1",
                    type = BookmarkType.VOTE
                ),
                Bookmark(
                    bookmarkId = 2,
                    id = 2,
                    title = "Bookmark2",
                    description = "Description2",
                    type = BookmarkType.VOTE
                ),
            ),
            selectedCategory = Category(
                id = 1,
                name = "Category1",
                color = "#FF0000",
                textColor = "#FFFFFF"
            )
        )
    )
}