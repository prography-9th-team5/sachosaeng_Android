package com.sachosaeng.app.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G3
import com.sachosaeng.app.core.ui.theme.Gs_G5
import com.sachosaeng.app.core.ui.theme.Gs_White

@Composable
fun TabRowComponent(
    screenColor: Color = Gs_White,
    tabs: List<String>,
    tabTrailingButton: @Composable () -> Unit = {},
    tabTrailingButtonComposition: Int = 0,
    tabTitleColor: Color = Gs_Black,
    contentScreens: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
    indicatorColor: Color = Gs_Black,
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Row(
        modifier = modifier
            .wrapContentWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        tabs.forEachIndexed { index, tabTitle ->
            val isSelected = selectedTabIndex == index
            Column(
                modifier = modifier
                    .width(IntrinsicSize.Max)
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = tabTitle,
                    fontSize = 20.sp,
                    color = if (isSelected) tabTitleColor else Gs_G5,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clickable {
                            selectedTabIndex = index
                        }
                )
                Spacer(
                    modifier = Modifier
                        .height(if (isSelected) 2.dp else 0.dp)
                        .fillMaxWidth()
                        .background(Gs_Black)
                        .background(color = indicatorColor)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        if (selectedTabIndex == tabTrailingButtonComposition) tabTrailingButton()
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


@Composable
fun TabRowComponentWithBottomLine(
    screenColor: Color = Gs_White,
    tabs: List<String>,
    tabTitleColor: Color = Gs_Black,
    contentScreens: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
    indicatorColor: Color = Gs_Black,
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        tabs.forEachIndexed { index, tabTitle ->
            val isSelected = selectedTabIndex == index
            Column(
                modifier = modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = tabTitle,
                    fontSize = 18.sp,
                    color = if (isSelected) tabTitleColor else Gs_G5,
                    fontWeight = if (isSelected) FontWeight.W700 else FontWeight.W500,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clickable {
                            selectedTabIndex = index
                        }
                )
                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(color = if (isSelected) indicatorColor else Gs_G3)
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