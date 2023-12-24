package com.example.todolistcompose.ui.theme.home_screen.components

import android.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistcompose.data.viewmodel.MainViewModel
import com.example.todolistcompose.domain.model.Todo
import com.example.todolistcompose.util.toastMsg
import kotlinx.coroutines.job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogHomeScreen(
    openDialog: Boolean,
    onClose: () -> Unit,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    var title: String by remember { mutableStateOf("") }
    var description: String by remember { mutableStateOf("") }
    var isImportant: Boolean by remember { mutableStateOf(false) }

    val todo = Todo(id = 0, title = title, description = description, isImportant = isImportant)

    if (openDialog) {
        AlertDialog(
            title = {
                Text(text = "Todo", fontFamily = FontFamily.Serif)
            },
            text = {
                LaunchedEffect(key1 = true,
                    block = {
                        // coroutineContext.job, bu coroutine'in çalışan işini temsil eden bir Job'dır.
                        // invokeOnCompletion, bu Job tamamlandığında çalışan bir fonksiyondur.
                        coroutineContext.job.invokeOnCompletion {
                            // focusRequester.requestFocus(), muhtemelen bir odak talebi işlemidir.
                            // Yani, işlem tamamlandığında belirli bir odak noktasına odaklanmayı sağlar.
                            focusRequester.requestFocus()
                        }
                    })
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TodoTextField(
                        modifier = Modifier.focusRequester(focusRequester = focusRequester),
                        focusRequester = focusRequester,
                        value = title,
                        onValueChange = { title = it },
                        placeholder = "Add Title",
                        onDone = {
                            if (title.isBlank()) {
                                toastMsg(
                                    context = context,
                                    message = "Title must not be null !!"
                                )
                            }
                        },
                        onClear = { title = "" }
                    )
                    TodoTextField(
                        modifier = Modifier.focusRequester(focusRequester = focusRequester),
                        focusRequester = focusRequester,
                        value = description,
                        onValueChange = { description = it },
                        placeholder = "Add Description",
                        onDone = {
                            if (description.isBlank()) {
                                toastMsg(
                                    context = context,
                                    message = "Description must not be null !!"
                                )
                            }
                        },
                        onClear = { description = "" }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Important",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Checkbox(checked = isImportant, onCheckedChange = { isImportant = it })
                    }
                }
            },
            onDismissRequest = {
                onClose()
                title = ""
                description = ""
                isImportant = false
            }, confirmButton = { 
                Button(onClick = {
                    if (description.isNotBlank() && title.isNotBlank()) {
                        mainViewModel.addTodo(todo = todo)
                        title = ""
                        description = ""
                        isImportant = false
                        onClose()
                    }
                }) {
                    Text(text = "Save")
                }
            }, dismissButton = {
                OutlinedButton(onClick = {
                    title = ""
                    description = ""
                    isImportant = false
                    onClose()
                }) {
                    Text(text = "Close")
                }
            })
    }
}