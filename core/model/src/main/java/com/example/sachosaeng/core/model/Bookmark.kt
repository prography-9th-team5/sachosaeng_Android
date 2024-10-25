package com.sachosaeng.app.core.model

data class Bookmark(
    val type : BookmarkType,
    val bookmarkId: Int,
    val id: Int,
    val title: String,
    val description: String
)

enum class BookmarkType {
    VOTE,
    INFORMATION
}