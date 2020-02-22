package com.fadybassem.elabda3task.data.room

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = arrayOf(Table::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun repoDao(): RepoDao

    companion object {

        private var appDatabase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase::class.java, "database.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return appDatabase!!
        }
    }

}