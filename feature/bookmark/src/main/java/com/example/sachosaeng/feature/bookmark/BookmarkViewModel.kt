package com.example.sachosaeng.feature.bookmark

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.StringResourceProvider
import com.example.sachosaeng.core.usecase.category.GetCategoryListUseCase
import com.example.sachosaeng.core.usecase.category.GetMyCategoryListUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import com.example.sachosaeng.core.ui.R.string.all_category_icon_text
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.usecase.bookmark.DeleteBookmarkListUseCase
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val stringResourceProvider: StringResourceProvider,
    private val getCategoryListUseCase: GetCategoryListUseCase,
) : ViewModel(), ContainerHost<BookmarkScreenUiState, Unit> {
    override val container: Container<BookmarkScreenUiState, Unit> =
        container(BookmarkScreenUiState())

    init {
        getAllCategoryList()
    }

    private fun getAllCategoryList() = intent {
        getCategoryListUseCase().collectLatest { allCategoryList ->
            val newList = listOf(Category(name = stringResourceProvider.getString(all_category_icon_text))) + allCategoryList
            reduce {
                state.copy(
                    allCategory = newList
                )
            }
        }
    }

    fun modifyBookmark() = intent {

    }
}