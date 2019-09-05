package com.brian.prioritize_2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
class Task(@PrimaryKey @ColumnInfo(name="task") val task: String) {

}
