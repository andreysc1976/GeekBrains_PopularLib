package ru.geekbrains.data

import io.reactivex.rxjava3.core.Observable

interface GitHubUserRepository {

    fun getUsers(): Observable<List<GitHubUser>>

    fun getUserById(userId: String): Observable<GitHubUser>

    fun getIds(): Observable<String>

}