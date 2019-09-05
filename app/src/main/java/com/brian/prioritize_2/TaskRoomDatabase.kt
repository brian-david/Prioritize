package com.brian.prioritize_2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Task::class), version = 1)
public abstract class TaskRoomDatabase : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getDatabase(conext: Context, scope: CoroutineScope): TaskRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    conext.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database"
                ).addCallback(TaskDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class TaskDatabaseCallback(private val scope: CoroutineScope):RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let{ database ->
                scope.launch {
                    populateDatabase(database.taskDao())
                }
            }
        }

        suspend fun populateDatabase(taskDao: TaskDao){
            taskDao.deleteAll()

            var task = Task("This is a task")
            taskDao.insert(task)
            taskDao.insert(Task("This is another task"))
        }
    }
}


