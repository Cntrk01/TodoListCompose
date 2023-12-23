package com.example.todolistcompose.di

import android.content.Context
import androidx.room.Room
import com.example.todolistcompose.data.local.TodoDao
import com.example.todolistcompose.data.local.TodoDatabase
import com.example.todolistcompose.data.repository.TodoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Binds
    @Singleton
    fun provideLocalDatabase (@ApplicationContext context: Context) : TodoDatabase{
        return Room.databaseBuilder(context = context, klass = TodoDatabase::class.java, name = "todo_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Binds
    @Singleton
    fun provideTodoDao(db:TodoDatabase) : TodoDao {
        return db.todoDao()
    }

    @Binds
    @Singleton
    fun provideTodoRepositoryImpl(dao: TodoDao) : TodoRepositoryImpl{
        return TodoRepositoryImpl(todoDao = dao)
    }
 }