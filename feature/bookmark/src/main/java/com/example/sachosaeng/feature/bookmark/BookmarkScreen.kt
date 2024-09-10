package com.example.sachosaeng.feature.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.ui.component.TabRowComponentWithBottomLine
import com.example.sachosaeng.core.ui.component.topappbar.TopBarWithProfileImage
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.util.extension.StringExtension.toColorResource
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()
    BookmarkScreen(
        state = state.value
    )
}

@Composable
internal fun BookmarkScreen(
    state: BookmarkScreenUiState,
    modifier: Modifier = Modifier,
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
            onProfileImageClicked = {}
        )
        TabRowComponentWithBottomLine(
            tabs = tabList,
            contentScreens = listOf(
                { VoteColumnByCategory(categories = state.myCategory) },
                { VoteColumnByCategory(categories = state.myCategory) },
            )
        )
    }
}

@Composable
fun VoteColumnByCategory(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(10.dp)
    ) {
        categories.forEach {
            CategoryCard(category = it)
        }
    }
}

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = Color(category.color.toColorResource()),
            contentColor = Color(category.textColor.toColorResource())
        ),
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .padding(10.dp)
    ) {
        Text(
            modifier = modifier.padding(10.dp),
            text = category.name,
            fontSize = 15.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    BookmarkScreen(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gs_White),
        state = BookmarkScreenUiState(
            userType = UserType.NEW_EMPLOYEE,
        )
    )
}