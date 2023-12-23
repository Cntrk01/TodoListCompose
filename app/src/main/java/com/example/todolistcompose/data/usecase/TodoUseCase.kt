package com.example.todolistcompose.data.usecase

import com.example.todolistcompose.data.repository.TodoRepositoryImpl
import com.example.todolistcompose.domain.model.Todo
import javax.inject.Inject

class TodoUseCase @Inject constructor(private val todoRepositoryImpl: TodoRepositoryImpl) {
    suspend fun addTodo(todo: Todo) = todoRepositoryImpl.addTodo(todo = todo)
    suspend fun updateTodo(todo: Todo) = todoRepositoryImpl.updateTodo(todo = todo)
    suspend fun deleteTodo(todo: Todo) = todoRepositoryImpl.deleteTodo(todo = todo)
    suspend fun getTodoWithId(todo: Todo) = todoRepositoryImpl.getTodoWithId(todo = todo)
    fun getAllTodo() = todoRepositoryImpl.getAllTodo()
}