package com.fadybassem.elabda3task.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
class Table constructor() {
    @field:PrimaryKey
    var id: Int = 0

    @field:ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null

    @field:ColumnInfo(name = "name")
    var name: String? = null

    @field:ColumnInfo(name = "fullName")
    var fullName: String? = null

    @field:ColumnInfo(name = "size")
    var size: Int? = null

    @field:ColumnInfo(name = "forks")
    var forks: Int? = null

    @field:ColumnInfo(name = "watchersCount")
    var watchersCount: Int? = null

    @field:ColumnInfo(name = "rank")
    var rank: Int? = null

    @Ignore
    constructor(
        id: Int,
        avatarUrl: String,
        name: String,
        fullName: String,
        size: Int,
        forks: Int,
        watchersCount: Int,
        rank: Int
    ) : this() {
        this.id = id
        this.avatarUrl = avatarUrl
        this.name = name
        this.fullName = fullName
        this.size = size
        this.forks = forks
        this.watchersCount = watchersCount
        this.rank = rank
    }

}