package com.gvelesiani.todo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
// represents a table within the database
@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var todoName: String
) : Parcelable