package com.gvelesiani.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*

// contains methods used for accessing the database
@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Update
    suspend fun updateTodoState(todo:Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTodos()

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Todo>>


}