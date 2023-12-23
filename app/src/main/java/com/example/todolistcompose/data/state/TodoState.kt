package com.example.todolistcompose.data.state

import com.example.todolistcompose.domain.model.Todo

data class TodoState(
    val isLoading : Boolean=false,
    val todoList : List<Todo> ?=null,
    val errorMessage:String ?=null,
    val successfullMessage:String ?=null,
    val todoWithId : Todo ?=null
)
