package com.example.sachosaeng.feature.addvote

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddVoteScreen(
    viewModel: AddVoteViewModel = hiltViewModel()
) {
    AddVoteScreen()
}

@Composable
internal fun AddVoteScreen() {
    LazyColumn {
        item {

        }
    }
}


@Composable
@Preview
fun AddVoteScreenPreview() {
    AddVoteScreen()
}