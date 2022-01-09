package ru.geekbrains.mvpuser.base

import io.reactivex.rxjava3.core.Single

interface GitHubRepoRepository {
    fun getUserRepo(login:String): Single<List<GitHubRepo>>
}