package com.example.sachosaeng.feature.bookmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.model.Bookmark
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G6
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.ui.R.drawable
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_G3

@Composable
fun BookmarkList(
    bookmarks: List<Bookmark>,
    modifier: Modifier = Modifier,
    selectedForModifyBookmarkList: List<Bookmark> = emptyList(),
    isModifyMode: Boolean = false,
    onSelectForModifyBookmark: (Bookmark) -> Unit = {},
    onBookmarkClicked: (Bookmark) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(bookmarks.size) {
            BookmarkCard(
                isSelected = selectedForModifyBookmarkList.contains(bookmarks[it]),
                isModifyMode = isModifyMode,
                bookmark = bookmarks[it],
                onBookmarkClicked = onBookmarkClicked,
                onSelectForModifyBookmark = onSelectForModifyBookmark
            )
        }
    }
}

@Composable
private fun BookmarkCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isModifyMode: Boolean = false,
    bookmark: Bookmark,
    onBookmarkClicked: (Bookmark) -> Unit = {},
    onSelectForModifyBookmark: (Bookmark) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = if (isSelected) Gs_G3 else Gs_White
        ),
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable {
                when (isModifyMode) {
                    true -> {
                        onSelectForModifyBookmark(bookmark)
                    }

                    false -> {
                        onBookmarkClicked(bookmark)
                    }
                }
            }
    ) {
        Row(
            modifier = modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isModifyMode) Image(
                painter = painterResource(id = if (isSelected) drawable.ic_checked_circle else drawable.ic_unchecked_circle),
                contentDescription = null
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = bookmark.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,
                    color = Gs_Black
                )
                if(bookmark.description.isNotEmpty()) Text(
                    text = bookmark.description,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W700,
                    color = Gs_G6
                )
            }
        }
    }
}

@Composable
@Preview
fun BookmarkListPreview() {
    val bookmarks = listOf(
        Bookmark(
            bookmarkId = 1,
            id = 1,
            title = "title1",
            description = "description1"
        ),
        Bookmark(
            bookmarkId = 2,
            id = 2,
            title = "title2",
            description = "description2"
        ),
        Bookmark(
            bookmarkId = 3,
            id = 3,
            title = "title3",
            description = "description3"
        )
    )
    BookmarkList(bookmarks = bookmarks)
}