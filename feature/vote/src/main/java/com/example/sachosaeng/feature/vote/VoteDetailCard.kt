package com.example.sachosaeng.feature.vote

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G3
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.util.extension.IntExtension.toNumberOfPeople
import com.example.sachosaeng.core.util.extension.StringExtension.toColorResource
import com.example.sachosaeng.core.ui.R.drawable

@Composable
fun VoteDetailCard(
    modifier: Modifier = Modifier,
    vote: Vote,
    selectedOption: String,
    isBookmarked: Boolean = false,
    onBookmarkButtonClicked: () -> Unit = { },
    onSelectedOption: (String) -> Unit = { }
) {

    Card(colors = CardDefaults.cardColors().copy(containerColor = Gs_White)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .height(68.dp)
                .background(color = Color(vote.category.color.toColorResource()))
        ) {
            CategoryIcon(imageUrl = vote.category.imageUrl)
            BookmarkButton(
                isBookmarked = isBookmarked,
                onBookmarkButtonClicked = onBookmarkButtonClicked
            )
        }
        Column(modifier = modifier.padding(20.dp)) {
            Text(text = vote.title, fontWeight = FontWeight.W700, fontSize = 18.sp)
            Text(text = vote.count.toNumberOfPeople(), modifier = Modifier.padding(top = 16.dp))
            Column(
                modifier = modifier.padding(top = 28.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                vote.option.forEach { text ->
                    OptionRow(text = text, isSeleceted = text == selectedOption, onSelected = {
                        onSelectedOption(it)
                    })
                }
            }
        }
    }
}

@Composable
private fun CategoryIcon(imageUrl: String?) {
    AsyncImage(
        modifier = Modifier
            .size(68.dp)
            .padding(18.dp),
        model = imageUrl ?: drawable.ic_default_category,
        contentDescription = "",
    )
}

@Composable
private fun BookmarkButton(
    isBookmarked: Boolean = false,
    onBookmarkButtonClicked: () -> Unit = { }
) {
    IconButton(
        modifier = Modifier
            .size(68.dp)
            .padding(18.dp),
        onClick = { onBookmarkButtonClicked() }) {
        Icon(
            painterResource(id = drawable.ic_bookmark),
            tint = if (isBookmarked) Gs_Black else Gs_G5,
            contentDescription = ""
        )
    }
}

@Composable
private fun OptionRow(
    text: String,
    onSelected: (String) -> Unit = { },
    isSeleceted: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isSeleceted) Gs_Black else Gs_G3,
                shape = RoundedCornerShape(4.dp)
            )
            .background(Gs_G2)
            .clickable {
                onSelected(text)
            }
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            fontSize = 14.sp,
            fontWeight = if (isSeleceted) FontWeight.W700 else FontWeight.W500
        )
    }
}

@Preview
@Composable
fun VoteDetailCardPreview() {
    VoteDetailCard(
        selectedOption = "옵션2",
        vote = Vote(
            title = "친한 사수분 결혼식 축의금 얼마가 좋을까요?",
            count = 1000,
            option = listOf("옵션1", "옵션2", "옵션3"),
            category = Category(
                name = "카테고리",
                textColor = "000000"
            )
        )
    )
}