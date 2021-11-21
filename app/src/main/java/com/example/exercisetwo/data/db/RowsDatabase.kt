package com.example.exercisetwo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exercisetwo.data.model.Rows

@Database(entities = [Rows::class], version = 3, exportSchema = false)
abstract class RowsDatabase : RoomDatabase() {
    abstract fun rowsDao(): RowsDao

    // to make our userdata singleton class so that instance is only one
    companion object {
        @Volatile //  writes to this field are immediately made visible to other threads.
        private var INSTANCE: RowsDatabase? = null

        fun getDatabase(context: Context): RowsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            //creating new instance if our instance is null
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RowsDatabase::class.java,
                    "rows_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}