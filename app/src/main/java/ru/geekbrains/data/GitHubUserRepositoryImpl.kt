package ru.geekbrains.data

import io.reactivex.rxjava3.core.Observable


class GitHubUserRepositoryImpl : GitHubUserRepository {

    private val users = listOf(
        GitHubUser("login1", "1"),
        GitHubUser("login2","2"),
        GitHubUser("login3", "3"),
        GitHubUser("login4", "4"),
        GitHubUser("login5", "5"),
    )


    override fun getUsers(): Observable<List<GitHubUser>> {
         return Observable.just(users)
    }

    override fun getIds(): Observable<String> {
        val ids = listOf("1","2","3","4","2")
        return Observable.fromIterable(ids)
    }


    override fun getUserById(userId: String): Observable<GitHubUser>{
        return Observable.just(users)
            .map { it.findLast { it.id == userId} }
    }



}