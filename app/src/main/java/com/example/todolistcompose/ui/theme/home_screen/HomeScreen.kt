package com.example.todolistcompose.ui.theme.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistcompose.data.state.TodoState
import com.example.todolistcompose.data.viewmodel.MainViewModel
import com.example.todolistcompose.ui.theme.home_screen.components.AlertDialogHomeScreen
import com.example.todolistcompose.ui.theme.home_screen.components.EmptyTaskScreen
import com.example.todolistcompose.ui.theme.home_screen.components.TodoCard
import com.example.todolistcompose.util.SnackBar
import com.example.todolistcompose.util.topAppBarTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    onUpdate: (id: Int) -> Unit
) {
    val todos by mainViewModel.todoState.collectAsState(initial = TodoState())

    var openDialog by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Todos",
                    style = topAppBarTextStyle
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { openDialog = true }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        AlertDialogHomeScreen(
            openDialog = openDialog,
            onClose = { openDialog = false },
            mainViewModel = mainViewModel
        )

        if (todos.todoList?.isEmpty() == true) {
            EmptyTaskScreen(paddingValues = paddingValues)
        } else {
            todos.todoList?.let { todoList ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = paddingValues),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp) //dikeyde boşluk veriyor
                ) {
                    items(
                            items = todoList,
                        ) {todo->
                            TodoCard(
                                todo = todo,
                                onDone = {
                                    mainViewModel.deleteTodo(todo = todo)
                                    SnackBar(
                                        scope = scope,
                                        snackBarHostState = snackBarHostState,
                                        message = "DONE! -> \"${todo.title}\"",
                                        actionLabel = "UNDO",
                                        onAction = { mainViewModel.addTodo(todo = todo) }
                                    )
                                },
                                onUpdate = { onUpdate.invoke(it)
                                println(it)
                                }
                            )
                        }
                }
            }
        }
    }
}