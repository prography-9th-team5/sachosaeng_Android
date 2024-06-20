package com.example.sachosaeng.feature.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_White

@Composable
fun TabRowComponent(
    screenColor: Color = Gs_White,
    tabs: List<String>,
    contentScreens: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    contentColor: Color = Gs_Black,
    indicatorColor: Color = Gs_Black
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(screenColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = containerColor,
            contentColor = contentColor,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = indicatorColor
                )
            }
        ) {
            tabs.forEachIndexed { index, tabTitle ->
                Tab(
                    modifier = Modifier.padding(all = 16.dp),
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                ) {
                    Text(text = tabTitle, fontSize = 20.sp, fontWeight = FontWeight.W700)
                }
            }
        }
        contentScreens.getOrNull(selectedTabIndex)?.invoke()
    }
}