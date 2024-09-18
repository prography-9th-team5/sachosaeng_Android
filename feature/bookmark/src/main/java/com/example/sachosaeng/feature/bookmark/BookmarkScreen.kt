package com.example.sachosaeng.feature.bookmark

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.sachosaeng.core.model.Bookmark
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.ui.component.TabRowComponentWithBottomLine
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.component.topappbar.TopBarWithProfileImage
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G4
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.util.extension.StringExtension.toColorResource
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun BookmarkScreen(
    moveToVote: (Int) -> Unit = {},
    moveToMyPage: () -> Unit = {},
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()
    BookmarkScreen(
        state = state.value,
        onBookmarkClick = { bookmark ->
            //todo: bookmark로 가야하는지? vote로 봐야하는지?
            moveToVote(bookmark.voteId)
        },
        onProfileImageClicked = moveToMyPage,
        onModifyButtonClicked = viewModel::modifyBookmark,
        onCategoryClicked = viewModel::categoryClicked,
        onSelectForModifyBookmark = viewModel::selectModifyBookmark
    )
}

@Composable
internal fun BookmarkScreen(
    state: BookmarkScreenUiState,
    modifier: Modifier = Modifier,
    onModifyButtonClicked: () -> Unit = {},
    onProfileImageClicked: () -> Unit = {},
    onCategoryClicked: (Category) -> Unit = {},
    onBookmarkClick: (Bookmark) -> Unit = {},
    onSelectForModifyBookmark: (Bookmark) -> Unit = {}
) {
    val myCategoryTabTitle = stringResource(id = R.string.vote_label)
    val allCategoryTabTitle = stringResource(id = R.string.bookmark_tab_article)
    val tabList = listOf(myCategoryTabTitle, allCategoryTabTitle)

    Column(
        modifier = modifier
            .fillMaxSize()
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
        TabRowComponentWithBottomLine(
            screenColor = Gs_G2,
            tabs = tabList,
            contentScreens = listOf(
                {
                    CategoryRow(
                        selectedCategory = state.selectedCategory,
                        categories = state.allCategory,
                        onModifyButtonClicked = onModifyButtonClicked,
                        onCategoryClicked = onCategoryClicked,
                        isModifyMode = state.isModifyMode
                    )
                    BookmarkList(
                        isModifyMode = state.isModifyMode,
                        bookmarks = state.bookmarkList,
                        onBookmarkClicked = onBookmarkClick,
                        selectedForModifyBookmarkList = state.selectedForModifyBookmarkList,
                        onSelectForModifyBookmark = onSelectForModifyBookmark
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (state.isModifyMode) SachoSaengButton(
                        enabled = state.selectedForModifyBookmarkList.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.delete_label),
                        onClick = { }
                    )
                },
                {
                    CategoryRow(
                        categories = state.allCategory,
                        onModifyButtonClicked = onModifyButtonClicked,
                        isModifyMode = state.isModifyMode,
                    )
                },
            )
        )
    }
}

@Composable
fun CategoryRow(
    selectedCategory: Category? = null,
    categories: List<Category>,
    modifier: Modifier = Modifier,
    onCategoryClicked: (Category) -> Unit = {},
    onModifyButtonClicked: () -> Unit = {},
    isModifyMode: Boolean = false
) {
    Box {
        LazyRow(
            modifier = modifier.padding(top = 16.dp, bottom = 16.dp, end = 60.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(categories.size) {
                CategoryCard(
                    isSelected = selectedCategory == categories[it],
                    category = categories[it],
                    onCategoryClicked = onCategoryClicked
                )
            }
        }
        Row(
            modifier = Modifier
                .background(Gs_G2)
                .padding(16.dp)
                .align(Alignment.CenterEnd)
                .noRippleClickable { onModifyButtonClicked() }
        ) {
            Text(
                color = if (isModifyMode) Gs_G5 else Gs_Black,
                text = stringResource(id = if (isModifyMode) R.string.cancel_label else R.string.modify_label),
                fontSize = 14.sp,
                fontWeight = FontWeight.W500
            )
        }
    }
}

@Composable
private fun CategoryCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    category: Category,
    onCategoryClicked: (Category) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = Color(category.color.toColorResource()),
            contentColor = Color(category.textColor.toColorResource())
        ),
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .border(
                1.dp,
                if (isSelected) Color(category.textColor.toColorResource()) else Color.Transparent,
                RoundedCornerShape(4.dp)
            )
            .clickable {
                onCategoryClicked(category)
            },
    ) {
        Row(
            modifier = modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (category.imageUrl?.isNotEmpty() == true) {
                AsyncImage(
                    alignment = Alignment.CenterEnd,
                    contentDescription = "", model = category.imageUrl,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(
                text = category.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600
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
            bookmarkList = listOf(
                Bookmark(
                    voteBookmarkId = 1,
                    voteId = 1,
                    title = "Bookmark1",
                    description = "Description1"
                ),
                Bookmark(
                    voteBookmarkId = 2,
                    voteId = 2,
                    title = "Bookmark2",
                    description = "Description2"
                ),
                Bookmark(
                    voteBookmarkId = 3,
                    voteId = 3,
                    title = "Bookmark3",
                    description = "Description3"
                ),
            ),
            isModifyMode = true,
            selectedForModifyBookmarkList = listOf(
                Bookmark(
                    voteBookmarkId = 1,
                    voteId = 1,
                    title = "Bookmark1",
                    description = "Description1"
                ),
                Bookmark(
                    voteBookmarkId = 2,
                    voteId = 2,
                    title = "Bookmark2",
                    description = "Description2"
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