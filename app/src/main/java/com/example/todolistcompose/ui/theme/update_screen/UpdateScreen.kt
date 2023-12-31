package com.example.todolistcompose.ui.theme.update_screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolistcompose.R
import com.example.todolistcompose.data.state.TodoState
import com.example.todolistcompose.data.viewmodel.MainViewModel
import com.example.todolistcompose.util.taskTextStyle
import com.example.todolistcompose.util.toastMsg
import com.example.todolistcompose.util.topAppBarTextStyle
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    id: Int,
    mainViewModel: MainViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val todos by mainViewModel.todoState.collectAsState(initial = TodoState())
    // val title = remember { mutableStateOf("") }
    //    val description = remember { mutableStateOf("") }
    //    val isImportant = remember { mutableStateOf(false) } ben böyle yapmıştım bunlarda her seferinde değer vermek için .value değerine erişmek gerekiuor.
    //fakat alttaki gibi delegasyon yapınca ifadelerin tipleri belirleniyor böylelikle  .value yazmaya gerek kalmıyor burada.

    var title: String by remember { mutableStateOf("") }
    var description: String by remember { mutableStateOf("") }
    var isImportant: Boolean by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        mainViewModel.getTodoById(todo = id)

        mainViewModel.todoState.collectLatest {
            title = it.todoWithId?.title.toString()
            description = it.todoWithId?.description.toString()
            isImportant = it.todoWithId?.isImportant == true
        }
    })

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Update Todo", style = topAppBarTextStyle)
            },
                navigationIcon = {
                    IconButton(onClick = { onBack.invoke() }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                })
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            Icon(
                imageVector = Icons.Rounded.Edit, contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))

            TextField(
                value = title,
                onValueChange = {
                    title = it
                },
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .horizontalScroll(state = rememberScrollState()),
                label = {
                    Text(text = "Title", fontFamily = FontFamily.Monospace)
                },
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Sentences
                ),
                textStyle = taskTextStyle,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.size(16.dp))

            TextField(
                value = description,
                onValueChange = { desc ->
                    description = desc
                },
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .verticalScroll(state = rememberScrollState()),
                label = {
                    Text(text = "Description", fontFamily = FontFamily.Monospace)
                },
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Sentences
                ),
                textStyle = taskTextStyle,
                maxLines = 5
            )

            Spacer(modifier = Modifier.size(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Important", fontFamily = FontFamily.Monospace, fontSize = 18.sp)
                Spacer(modifier = Modifier.size(8.dp))
                isImportant?.let {
                    Checkbox(checked = it, onCheckedChange = { check ->
                        isImportant = check
                    })
                }

            }
            Spacer(modifier = Modifier.size(8.dp))

            Button(onClick = {
                if (title.isBlank()) {
                    toastMsg(context = context, "Title is not empty...")
                } else if (description.isBlank()) {
                    toastMsg(context = context, "Description is not empty...")
                } else {
                    todos.todoWithId!!.title = title
                    todos.todoWithId!!.description = description
                    todos.todoWithId!!.isImportant = isImportant
                    mainViewModel.updateTodo(todo = mainViewModel.todoState.value.todoWithId!!)
                    onBack.invoke()
                    toastMsg(context = context, message = "Note Saved..")
                }
            }) {
                Text(text = stringResource(R.string.save_note), fontSize = 16.sp)
            }
        }
    }
}