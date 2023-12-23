package com.example.todolistcompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val id : Int =0,
    val title : String,
    val description : String,
    val isImportant  : Boolean
)