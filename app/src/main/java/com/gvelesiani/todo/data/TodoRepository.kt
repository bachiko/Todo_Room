package com.gvelesiani.todo.data

import androidx.lifecycle.LiveData

// repository is a class that abstracts access to multiple data sources, best practice for code separation and architecture

class TodoRepository(private val todoDao: TodoDao) {
    val readAllData: LiveData<List<Todo>> = todoDao.readAllData()

    suspend fun addTodo(todo: Todo){
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: Todo){
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo){
        todoDao.deleteTodo(todo)
    }

    suspend fun deleteAllTodo(){
        todoDao.deleteAllTodos()
    }
}
