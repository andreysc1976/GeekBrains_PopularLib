package ru.geekbrains.data

data class GitHubUser(
    val login: String,
    val avatar_url: String?=null,
    val id:Int=-1
)