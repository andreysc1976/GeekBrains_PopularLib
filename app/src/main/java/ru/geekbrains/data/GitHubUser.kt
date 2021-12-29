package ru.geekbrains.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "GitHubUserTable")
data class GitHubUser(
    val login: String,
    val avatar_url: String?=null,
    @PrimaryKey
    val id:Int
)