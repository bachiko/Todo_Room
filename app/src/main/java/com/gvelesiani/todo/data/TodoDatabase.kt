package com.gvelesiani.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// contains the database holder and serves as the main access point for the underlying connection to app's persisted, relational data

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile // making visible to other threads
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}