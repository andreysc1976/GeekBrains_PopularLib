package ru.geekbrains.data

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.rest.GitHubApiFactory
import ru.geekbrains.data.room.RoomFactory

class GitHubUserRepositoryImpl : GitHubUserRepository {

    private val gitHubApi = GitHubApiFactory.create()
    private val roomDb = RoomFactory.create().getGitHubUserDao()


    override fun getUsers():Single<List<GitHubUser>>
    {
        return roomDb.getUsers()
            .flatMap { users ->
                if (users.isEmpty()) {
                    gitHubApi.fetchUsers()
                        .map { apiRez ->
                            roomDb.saveUser(apiRez)
                            apiRez
                        }
                } else {
                    Single.just(users)
                }
            }
    }



    override fun getUserByLogin(userId: String): Single<GitHubUser>
    {
        return  gitHubApi.fetchUserByLogin(userId)
    }
}