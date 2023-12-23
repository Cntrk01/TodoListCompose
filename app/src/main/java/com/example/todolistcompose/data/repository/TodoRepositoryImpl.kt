package com.example.todolistcompose.data.repository

import com.example.todolistcompose.data.local.TodoDao
import com.example.todolistcompose.util.Response
import com.example.todolistcompose.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val todoDao:TodoDao){
    suspend fun addTodo(todo: Todo) : Flow<Response<Unit>>{
        return flow {
            emit(Response.Loading())

            try {
                todoDao.insertTodo(todo = todo)
                emit(Response.Success(Unit))
            }catch (e:Exception){
                emit(Response.Error(message = "The note could not be saved..."))
            }
        }
    }
    suspend fun updateTodo(todo: Todo) : Flow<Response<Unit>> {
        return flow {
            emit(Response.Loading())
            try {
                todoDao.updateTodo(todo = todo)
                emit(Response.Success(Unit))
            }catch (e:Exception){
                emit(Response.Error(message = "The note could not be updated..."))
            }
        }
    }
    suspend fun deleteTodo(todo: Todo) : Flow<Response<Unit>>{
        return flow {
            emit(Response.Loading())
            try {
                todoDao.deleteTodo(todo = todo)
                emit(Response.Success(Unit))
            }catch (e:Exception){
                emit(Response.Error(message = "The note could not be deleted..."))
            }
        }
    }
    suspend fun getTodoWithId(todo: Todo): Flow<Response<Unit>>{
        return flow {
            emit(Response.Loading())
            try {
                val todoDetailId= todoDao.getTodoById(id = todo.id)
                if (todo.id==todoDetailId.id){
                    emit(Response.Success(Unit))
                }else{
                    emit(Response.Error(message = "Except..."))
                }
            }catch (e:Exception){
                emit(Response.Error(message = "Except..."))
            }
        }
    }

    suspend fun getAllTodo() : Flow<Response<List<Todo>>>{
        return flow{
            emit(Response.Loading())
            try {
                val todoList=todoDao.getAllTodo()
                todoList.collectLatest {
                    if (it.isNotEmpty()){
                        emit(Response.Success(it))
                    }else{
                        emit(Response.Error(message = "Except..."))
                    }
                }
            }catch (e:Exception){
                emit(Response.Error(message = "Except..."))
            }
        }
    }

}