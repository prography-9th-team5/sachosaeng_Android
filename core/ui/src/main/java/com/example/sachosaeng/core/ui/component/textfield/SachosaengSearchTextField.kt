package com.example.sachosaeng.core.ui.component.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_G5

@Composable
fun SachosaengSearchTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "",
    onSearch: (String) -> Unit = {}
) {
    val valueState = remember { mutableStateOf(value) }
    val innerValue by valueState
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(White)
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        BasicTextField(
            value = innerValue,
            onValueChange = {
                valueState.value = it
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { onSearch(innerValue) },
            ),
            decorationBox = { innerTextField ->
                Box(modifier = modifier.fillMaxWidth()) {
                    if (innerValue.isEmpty()) Text(
                        text = placeholder,
                        fontSize = 16.sp,
                        color = Gs_G5,
                    )
                    innerTextField()
                }
            }
        )
        if (value.isNotEmpty()) Image(
            modifier = Modifier
                .noRippleClickable {
                    valueState.value = ""
                }
                .align(Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.ic_clear_circle),
            contentDescription = "Search"
        )
    }
}

@Composable
@Preview()
fun SachosaengSearchTextFieldPreview() {
    SachosaengSearchTextField(
        value = "",
    )
}