package com.example.todolistcompose.ui.theme.home_screen.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.example.todolistcompose.util.taskTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTextField(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    onDone: () -> Unit,
    onClear: () -> Unit
) {
    Row {
        TextField(
            modifier = modifier.weight(2f)
                .focusRequester(focusRequester = focusRequester)
                .horizontalScroll(state = rememberScrollState()),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                )
            },
            shape = RectangleShape,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDone.invoke()
                }
            ),
//        trailingIcon = {
//            IconButton(onClick = {onClear.invoke()}) {
//                Icon(
//                    imageVector = Icons.Rounded.Clear,
//                    contentDescription = "Clear"
//                )
//            }
//        },
            textStyle = taskTextStyle,
            maxLines = 1,
        )
        IconButton(
            onClick = { onClear.invoke() },
        ) {
            Icon(
                imageVector = Icons.Rounded.Clear,
                contentDescription = "Clear"
            )
        }
    }
}