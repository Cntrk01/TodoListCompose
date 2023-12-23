package com.example.todolistcompose.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolistcompose.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM Todo WHERE id =:id")
    suspend fun getTodoById(id:Int) : Todo

    @Query("SELECT * FROM Todo")
    suspend fun getAllTodo() : Flow<List<Todo>>
}