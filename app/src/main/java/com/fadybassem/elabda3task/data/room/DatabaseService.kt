package com.fadybassem.elabda3task.data.room

import android.content.Context

class DatabaseService(context: Context) : QueriesInterface {

    private val dao: RepoDao = AppDatabase.getInstance(context).repoDao()

    override fun getAll(): List<Table?>? {
        return dao.getAllRepos()
    }

    override fun insert(threads: Table): Int {
        return try {
            dao.insert(threads)
            1
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }


}