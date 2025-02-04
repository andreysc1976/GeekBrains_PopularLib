package ru.geekbrains.data

import io.reactivex.rxjava3.core.Single

interface GitHubUserRepository {

    fun getUsers(): List<GitHubUser>

    fun getUserByLogin(userId: String): Single<GitHubUser>


}