package ru.geekbrains.data

import io.reactivex.rxjava3.core.Single

class GitHubUserRepositoryImpl : GitHubUserRepository {

    private val gitHubApi = GitHubApiFactory.create()

    private val users = listOf(
        GitHubUser("andreysc1976"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5"),
    )

    override fun getUsers() =
        users



    override fun getUserByLogin(userId: String): Single<GitHubUser>
    {
        return  gitHubApi.fetchUserByLogin(userId)
    }
}