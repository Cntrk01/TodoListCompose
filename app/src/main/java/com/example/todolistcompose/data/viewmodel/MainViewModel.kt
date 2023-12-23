package com.example.todolistcompose.data.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistcompose.data.state.TodoState
import com.example.todolistcompose.data.usecase.TodoUseCase
import com.example.todolistcompose.domain.model.Todo
import com.example.todolistcompose.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase : TodoUseCase) : ViewModel() {
    private val _state = MutableStateFlow(TodoState())
    val todoState : StateFlow<TodoState> = _state

    //private val _insertTodo = MutableState()

    
}