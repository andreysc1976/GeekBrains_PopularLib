package ru.geekbrains.mvpuser.base

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GitHubRepoTable")
data class GitHubRepo(
    @PrimaryKey
    val id:Int,
    val node_id:String,
    val name:String,
    val full_name:String,
    val description:String
)
