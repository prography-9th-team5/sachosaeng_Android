package com.example.sachosaeng.feature.signup.selectcategory

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.component.CategoryListFlowRow
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.component.topappbar.SachosaengDetailTopAppBar
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.feature.signup.SelectScreenDescription
import com.example.sachosaeng.feature.signup.SignUpProgressBar
import com.example.sachosaeng.feature.signup.SignUpProgressbarWithColor
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SelectCategoryScreen(
    moveToNextStep: () -> Unit,
    navigateToBackStack: () -> Unit = {},
    viewModel: SelectCategoryViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    SelectCategoryScreen(
        state = state,
        onSelectCategory = { viewModel::selectCategory.invoke(it) },
        onSkip = { viewModel::skipSelectCategory.invoke() },
        moveToNextStep = moveToNextStep,
        navigateToBackStack = navigateToBackStack
    )
}

@Composable
internal fun SelectCategoryScreen(
    state: SelectCategoryUiState,
    onSkip: () -> Unit,
    onSelectCategory: (Category) -> Unit,
    moveToNextStep: () -> Unit = {},
    navigateToBackStack: () -> Unit = {}
) {
    Column(
        modifier = Modifier.run { padding(horizontal = 20.dp) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SachosaengDetailTopAppBar(
            navigateToBackStack = navigateToBackStack,
            title = stringResource(id = string.select_category_screen_top_bar),
            fontWeight = FontWeight.W500,
            fontSize = 16
        )
        SelectCategoryProgressBar()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SelectScreenDescription(
                title = stringResource(id = string.select_initial_category_label),
                subText = stringResource(
                    id = string.select_initial_category_desc
                )
            )
            SkipButton(onSkip)
        }
        CategoryListFlowRow(
            state.categoryList,
            onSelectCategory = { onSelectCategory(it) })
        SachoSaengButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End),
            text = stringResource(id = string.start_button),
            onClick = { moveToNextStep() }
        )
    }
}

@Composable
fun SkipButton(onSkip: () -> Unit = {}) {
    Text(
        modifier = Modifier
            .noRippleClickable {
                onSkip()
            }
            .padding(top = 6.dp),
        text = stringResource(id = string.skip_button),
        fontSize = 16.sp,
        color = Gs_G5
    )
}

@Composable
fun SelectCategoryProgressBar() {
    Row(modifier = Modifier.fillMaxWidth()) {
        SignUpProgressbarWithColor(
            color = Gs_Black
        )
        SignUpProgressBar()
    }
}

@Composable
@Preview
fun SelectCategoryScreenPreview() {
    SelectCategoryScreen(
        state = SelectCategoryUiState(
            categoryList = listOf(
                Category(
                    id = 1,
                    name = "Category 1",
                    imageUrl = "https://picsum.photos/200/300",
                    color = "#FFFFFF",
                    textColor = "#000000"
                ),
                Category(
                    id = 2,
                    name = "Category 2",
                    imageUrl = "https://picsum.photos/200/300",
                    color = "#000000",
                    textColor = "#FFFFFF"
                ),
                Category(
                    id = 3,
                    name = "Category 3",
                    imageUrl = "https://example.com/image3",
                    color = "#FFFFFF",
                    textColor = "#000000"
                ),
                Category(
                    id = 4,
                    name = "Category 4",
                    imageUrl = "https://example.com/image4",
                    color = "#000000",
                    textColor = "#FFFFFF"
                ),
                Category(
                    id = 5,
                    name = "Category 5",
                    imageUrl = "https://example.com/image5",
                    color = "#FFFFFF",
                    textColor = "#000000"
                ),
                Category(
                    id = 6,
                    name = "Category 6",
                    imageUrl = "https://example.com/image6",
                    color = "#000000",
                    textColor = "#FFFFFF"
                ),
                Category(
                    id = 7,
                    name = "Category 7",
                    imageUrl = "https://example.com/image7",
                    color = "#FFFFFF",
                    textColor = "#000000"
                ),
                Category(
                    id = 8,
                    name = "Category 8",
                    imageUrl = "https://example.com/image8",
                    color = "#000000",
                    textColor = "#FFFFFF"
                ),
                Category(
                    id = 9,
                    name = "Category 9",
                    imageUrl = "https://example.com/image9",
                    color = "#FFFFFF",
                    textColor = "#000000"
                ),
                Category(
                    id = 10,
                    name = "Category 10",
                    imageUrl = "https://example.com/image10",
                    color = "#000000",
                    textColor = "#FFFFFF"
                )
            )
        ),
        onSelectCategory = {},
        onSkip = {}
    )
}
