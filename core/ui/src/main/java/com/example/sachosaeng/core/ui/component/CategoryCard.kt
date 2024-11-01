package com.example.sachosaeng.core.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.transition.Visibility
import coil.compose.AsyncImage
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G4
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.core.util.extension.StringExtension.toColorResource

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    isBackgroundColorVisibility: Boolean = true,
    isSelected: Boolean = false,
    category: Category,
    onCategoryClicked: (Category) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = if (isBackgroundColorVisibility) Color(category.color.toColorResource()) else Gs_White,
            contentColor = if (isBackgroundColorVisibility) Color(category.textColor.toColorResource()) else Gs_G4
        ),
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .border(
                1.dp,
                if (isSelected) Color(category.textColor.toColorResource()) else Color.Transparent,
                RoundedCornerShape(4.dp)
            )
            .clickable {
                onCategoryClicked(category)
            },
    ) {
        Row(
            modifier = modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (category.imageUrl?.isNotEmpty() == true) {
                AsyncImage(
                    alignment = Alignment.CenterEnd,
                    contentDescription = "", model = category.imageUrl,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(
                text = category.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}


@Composable
fun CategoryCardWithoutBorder(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    category: Category,
    onCategoryClicked: (Category) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = if (isSelected) Color(category.color.toColorResource()) else Gs_White,
            contentColor = if (isSelected) Color(category.textColor.toColorResource()) else Gs_G4
        ),
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .clickable {
                onCategoryClicked(category)
            },
    ) {
        Row(
            modifier = modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (category.imageUrl?.isNotEmpty() == true) {
                AsyncImage(
                    alignment = Alignment.CenterEnd,
                    contentDescription = "", model = category.imageUrl,
                    colorFilter = ColorFilter.tint(if (isSelected) Color.Transparent else Gs_G2),
                    modifier = Modifier
                        .size(18.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(
                text = category.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}
