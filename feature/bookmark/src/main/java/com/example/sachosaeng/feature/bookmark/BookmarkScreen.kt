package com.example.sachosaeng.feature.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.ui.component.TabRowComponentWithBottomLine
import com.example.sachosaeng.core.ui.component.topappbar.TopBarWithProfileImage
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.util.extension.StringExtension.toColorResource
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    moveToMyPage: () -> Unit = {},
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()
    BookmarkScreen(
        state = state.value,
        onProfileImageClicked = moveToMyPage,
        onModifyButtonClicked = viewModel::modifyBookmark,
    )
}

@Composable
internal fun BookmarkScreen(
    state: BookmarkScreenUiState,
    modifier: Modifier = Modifier,
    onModifyButtonClicked: () -> Unit = {},
    onProfileImageClicked: () -> Unit = {}
) {
    val myCategoryTabTitle = stringResource(id = R.string.bookmark_tab_vote)
    val allCategoryTabTitle = stringResource(id = R.string.bookmark_tab_article)
    val tabList = listOf(myCategoryTabTitle, allCategoryTabTitle)

    Column(modifier = modifier) {
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
            tabs = tabList,
            contentScreens = listOf(
                {
                    VoteColumnByCategory(
                        categories = state.allCategory
                    )
                },
                {
                    VoteColumnByCategory(
                        categories = state.allCategory,
                        onModifyButtonClicked = onModifyButtonClicked
                    )
                },
            )
        )
    }
}

@Composable
fun VoteColumnByCategory(
    categories: List<Category>,
    modifier: Modifier = Modifier,
    onModifyButtonClicked: () -> Unit = {},
) {
    Box {
        LazyRow(
            modifier = modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(categories.size) {
                CategoryCard(
                    category = categories[it]
                )
            }
        }
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(20.dp)
                .align(Alignment.CenterEnd)
                .noRippleClickable { onModifyButtonClicked() }
        ) {
            Text(
                color = Gs_G5,
                text = stringResource(id = R.string.modify_label),
                fontSize = 14.sp,
                fontWeight = FontWeight.W500
            )
        }
    }
}

@Composable
private fun CategoryCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean = true,
    category: Category,
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = Color(category.color.toColorResource()),
            contentColor = Color(category.textColor.toColorResource())
        ),
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .padding(10.dp)
            .border(
                1.dp,
                if (isSelected) Color(category.textColor.toColorResource()) else Color.Transparent,
                RoundedCornerShape(4.dp)
            ),
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
                )
            }
            Text(
                modifier = modifier.padding(start = 8.dp),
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
            )
        )
    )
}