package com.example.sachosaeng.core.ui.component.bottomappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G4
import com.example.sachosaeng.core.ui.theme.Gs_G5

@Composable
fun SachoSaengBottomAppBar(
    items: () -> List<BottomAppbarItem>,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val isSelectedIndex = remember { mutableStateOf(0) }

    BottomAppBar(
        containerColor = Gs_G4,
        modifier = modifier
            .background(Gs_G2)
            .height(76.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
            items.invoke().forEachIndexed { index, it ->
                IconButton(onClick = {
                    isSelectedIndex.value = index
                    navController.navigate(it.route) {
                        popUpTo(navController.currentBackStackEntry?.destination?.route.orEmpty()) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }) {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = null,
                        tint = if (isSelectedIndex.value == index) Gs_Black else Gs_G5
                    )
                }
            }
        }
    }
}
