package com.example.sachosaeng.feature.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
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
    indicatorColor: Color = Gs_Black
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        tabs.forEachIndexed { index, tabTitle ->
            Column(modifier = modifier.width(IntrinsicSize.Max).padding(10.dp)) {
                Text(
                    text = tabTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clickable {
                            selectedTabIndex = index
                        }
                    )
                Spacer(
                    modifier = Modifier
                        .height(if (selectedTabIndex == index) 2.dp else 0.dp)
                        .fillMaxWidth()
                        .background(Gs_Black)
                        .background(color = indicatorColor)
                )
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(screenColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        contentScreens.getOrNull(selectedTabIndex)?.invoke()
    }
}