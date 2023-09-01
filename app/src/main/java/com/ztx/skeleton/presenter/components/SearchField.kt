package com.ztx.skeleton.presenter.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ztx.skeleton.presenter.ui.theme.Dark_1f2329
import com.ztx.skeleton.presenter.ui.theme.Gray_BBBBBB
import com.ztx.skeleton.presenter.ui.theme.White_f6f8fa

@Composable
fun SearchField(
    onSearchAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val currentOnSearchAction by rememberUpdatedState(onSearchAction)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        OutlinedTextField(
            value = searchText,
            onValueChange = { newValue ->
                searchText = newValue
            },
            placeholder = { Text(text = "Search username...", fontWeight = FontWeight.Light) },
            trailingIcon = {
                if (searchText.text.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Icon",
                        modifier = Modifier.clickable {
                            searchText = TextFieldValue("")
                        }
                    )
                }
            },
            shape = RoundedCornerShape(
                topStart = 12.dp,
                bottomStart = 12.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            ),
            modifier = Modifier
                .weight(1f)  // makes the TextField take as much space as possible
                .background(
                    White_f6f8fa, RoundedCornerShape(
                        topStart = 12.dp,
                        bottomStart = 12.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .testTag("inputTextSearchField"),
            singleLine = true
        )

        Button(
            onClick = { currentOnSearchAction(searchText.text) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Dark_1f2329,
                contentColor = White_f6f8fa
            ),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                bottomStart = 0.dp,
                topEnd = 12.dp,
                bottomEnd = 12.dp
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .height(56.dp)
                .testTag("buttonSearchField")
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        }
    }
}
