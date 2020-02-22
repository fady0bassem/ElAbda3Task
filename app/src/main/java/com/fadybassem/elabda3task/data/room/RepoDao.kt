package com.fadybassem.elabda3task.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepoDao {

    @Query("SELECT * FROM data_table ORDER BY rank ASC")
    fun getAllRepos(): List<Table?>?

    @Insert
    fun insert(table: Table?)
}