package com.sachosaeng.app.feature.signup.selectcategory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.R.string
import com.sachosaeng.app.core.ui.component.CategoryListFlowRow
import com.sachosaeng.app.core.ui.component.button.SachoSaengButton
import com.sachosaeng.app.core.ui.component.dialog.SachosaengOneButtonDialog
import com.sachosaeng.app.core.ui.component.dialog.SachosaengTwoButtonDialog
import com.sachosaeng.app.core.ui.component.topappbar.SachosaengDetailTopAppBar
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G5
import com.sachosaeng.app.feature.signup.component.SelectScreenDescription
import com.sachosaeng.app.feature.signup.component.SignUpProgressBar
import com.sachosaeng.app.feature.signup.component.SignUpProgressbarWithColor
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SelectCategoryScreen(
    snackBarMessage: (String) -> Unit = {},
    moveToNextStep: () -> Unit,
    navigateToBackStack: () -> Unit = {},
    navigateToAuth: () -> Unit = {},
    viewModel: SelectCategoryViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    var errorMessage by remember {
        mutableStateOf("")
    }

    viewModel.collectSideEffect {
        when (it) {
            is SelectCategorySideEffect.NavigateToNextStep -> moveToNextStep()
            is SelectCategorySideEffect.ShowErrorDialog -> {
                errorMessage = it.message
            }
            else -> {}
        }
    }

    if (errorMessage.isNotEmpty()) JoinFailedDialog(
        onConfirm = navigateToAuth,
        errorMessage = errorMessage
    )
    SelectCategoryScreen(
        state = state,
        onClickCategory = viewModel::onClickCategory,
        onSkip = viewModel::skipSelectCategory,
        moveToNextStep = viewModel::join,
        navigateToBackStack = navigateToBackStack
    )
}

@Composable
internal fun SelectCategoryScreen(
    modifier: Modifier = Modifier,
    state: SelectCategoryUiState,
    onSkip: () -> Unit,
    onClickCategory: (Category) -> Unit,
    moveToNextStep: () -> Unit = {},
    navigateToBackStack: () -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.run { padding(horizontal = 20.dp) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SachosaengDetailTopAppBar(
                navigateToBackStack = navigateToBackStack,
                title = stringResource(id = string.select_category_screen_top_bar),
                fontWeight = FontWeight.W500,
                fontSize = 16
            )
            SelectCategoryProgressBar()
            Row(
                modifier = modifier.fillMaxWidth(),
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
                state.selectedCategoryList,
                onClickCategory = { onClickCategory(it) },
                modifier = Modifier.fillMaxWidth(),
            )
            SachoSaengButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                text = stringResource(id = string.start_button),
                onClick = { moveToNextStep() },
                enabled = state.isAnyCategorySelected
            )
        }
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
fun SelectCategoryProgressBar(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        SignUpProgressbarWithColor(
            color = Gs_Black,
            modifier = modifier.padding(end = 10.dp)
        )
        SignUpProgressBar()
    }
}

@Composable
fun JoinFailedDialog(modifier: Modifier = Modifier, onConfirm: () -> Unit, errorMessage: String) {
    SachosaengOneButtonDialog(
        modifier = modifier,
        buttonText = stringResource(id = string.navigate_to_signup_text),
        buttonOnClick = { onConfirm() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(vertical = 36.dp)
                .fillMaxWidth(0.8f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_warning_black_small),
                contentDescription = null
            )
            Text(
                modifier = modifier.padding(top = 12.dp),
                textAlign = TextAlign.Center,
                text = errorMessage,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Composable
@Preview
fun SelectCategoryScreenPreview() {
    SelectCategoryScreen(
        modifier = Modifier,
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
                ),
                Category(
                    id = 10,
                    name = "Category 10",
                    imageUrl = "https://example.com/image10",
                    color = "#000000",
                    textColor = "#FFFFFF"
                ),
                Category(
                    id = 10,
                    name = "Category 10",
                    imageUrl = "https://example.com/image10",
                    color = "#000000",
                    textColor = "#FFFFFF"
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
        onClickCategory = {},
        onSkip = {},
        moveToNextStep = {},
        navigateToBackStack = {}
    )
}
