package com.example.sachosaeng.feature.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.feature.R
import com.example.sachosaeng.feature.util.component.VoteCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.container.stateFlow.collectAsState()
    Column(
        modifier = Modifier
            .background(Gs_G2)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            CategoryDropdownButton(state.value.categoryList)
            ProfileImage(state.value.profileImageUrl)
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.value.voteList.size) {
                VoteCard(
                    category = state.value.voteList[it].category,
                    voteList = state.value.voteList[it].voteInfo
                )
            }
        }
    }
}

@Composable
fun CategoryDropdownButton(categoryList: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { expanded = true }
        ) {
            Text(
                text = stringResource(id = R.string.dropdown_default),
                fontSize = 26.sp,
                fontWeight = FontWeight.W700
            )
            Icon(
                if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                tint = Gs_Black,
                contentDescription = ""
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            categoryList.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category) },
                    onClick = { expanded = false }
                )
            }
        }
    }
}

@Composable
fun ProfileImage(profileImageUrl: String) {
    AsyncImage(
        modifier = Modifier.size(40.dp),
        model = if (profileImageUrl != "") profileImageUrl else R.drawable.ic_home_default_profile,
        contentDescription = "",
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}