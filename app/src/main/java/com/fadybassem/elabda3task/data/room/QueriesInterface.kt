package com.fadybassem.elabda3task.data.room

interface QueriesInterface {

    fun getAll(): List<Table?>?

    fun insert(threads: Table): Int
}