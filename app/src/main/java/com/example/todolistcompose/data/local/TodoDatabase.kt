package com.example.todolistcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistcompose.domain.model.Todo

@Database(entities = [Todo::class], version = 3, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
}