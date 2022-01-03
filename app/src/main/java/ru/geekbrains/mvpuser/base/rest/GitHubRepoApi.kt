package ru.geekbrains.mvpuser.base.rest

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.mvpuser.base.GitHubRepo

interface GitHubRepoApi {
    @GET("/users/{login}/repos")
    fun fetchUserByLogin(@Path("login") login: String): Single<List<GitHubRepo>>
}