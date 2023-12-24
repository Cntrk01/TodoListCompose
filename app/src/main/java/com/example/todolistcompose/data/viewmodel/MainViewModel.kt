package com.example.todolistcompose.data.viewmodel

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

    init {
        getAllTodo()
    }
    //undoda bunu kullancam
    fun addTodo(todo: Todo) = viewModelScope.launch {
        useCase.addTodo(todo = todo).collectLatest {
            when(it){
                is Response.Loading->{
                    _state.update { itState->
                        itState.copy(isLoading = true)
                    }
                }
                is Response.Error->{
                    _state.update { itState->
                        itState.copy(errorMessage = itState.errorMessage)
                    }
                }
                else -> {
                    _state.update { itState->
                        itState.copy(successfullMessage = "Todo Added Successfully")
                    }
                }
            }
        }
    }

    fun updateTodo(todo: Todo) = viewModelScope.launch {
        useCase.updateTodo(todo = todo).collectLatest {
            when(it){
                is Response.Loading->{
                    _state.update { itState->
                        itState.copy(isLoading = true)
                    }
                }
                is Response.Error->{
                    _state.update { itState->
                        itState.copy(errorMessage = itState.errorMessage)
                    }
                }
                else -> {
                    _state.update { itState->
                        itState.copy(successfullMessage = "Todo Updated Successfully")
                    }
                }
            }
        }
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        useCase.deleteTodo(todo = todo).collectLatest {
            when(it){
                is Response.Loading->{
                    _state.update { itState->
                        itState.copy(isLoading = true)
                    }
                }
                is Response.Error->{
                    _state.update { itState->
                        itState.copy(errorMessage = itState.errorMessage)
                    }
                }
                else -> {
                    _state.update { itState->
                        itState.copy(successfullMessage = "Todo Deleted Successfully")
                    }
                }
            }
        }
    }

    fun getTodoById(todo: Int) = viewModelScope.launch {
        useCase.getTodoWithId(todo = todo).collectLatest {
            when(it){
                is Response.Loading->{
                    _state.update { itState->
                        itState.copy(isLoading = true)
                    }
                }
                is Response.Error->{
                    _state.update { itState->
                        itState.copy(errorMessage = itState.errorMessage)
                    }
                }
                else -> {
                    _state.update { itState->
                        itState.copy(todoWithId =itState.todoWithId)
                    }
                }
            }
        }
    }

    fun getAllTodo() = viewModelScope.launch {
        useCase.getAllTodo().collectLatest {
            when(it){
                is Response.Loading->{
                    _state.update { itState->
                        itState.copy(isLoading = true)
                    }
                }
                is Response.Error->{
                    _state.update { itState->
                        itState.copy(errorMessage = itState.errorMessage)
                    }
                }
                else -> {
                    _state.update { itState->
                        itState.copy(todoList = it.data)
                    }
                }
            }
        }
    }
}