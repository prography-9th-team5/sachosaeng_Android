package com.sachosaeng.app.feature.search.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sachosaeng.app.core.ui.R.string
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sachosaeng.app.core.ui.component.SearchTopBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            SearchTopBar(
                placeholder = stringResource(id = string.search_placeholder),
                navigateToBackStack = { }
            )
        }, content = { paddingValues ->
            LazyColumn(modifier = modifier.padding(paddingValues)) {
                item {

                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}