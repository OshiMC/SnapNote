package com.example.taskappnew

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskTable")
class Task (
    @ColumnInfo(name = "title") val taskTitle: String,
    @ColumnInfo(name = "description") val taskDescription: String,
    @ColumnInfo(name = "priority") val taskPriority: String,
    @ColumnInfo(name = "duedate") val taskDueDate: String,
    @ColumnInfo(name = "timestamp") val timeStamp: String
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}