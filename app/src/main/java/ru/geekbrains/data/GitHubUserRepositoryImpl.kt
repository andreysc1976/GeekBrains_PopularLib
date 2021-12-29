package ru.geekbrains.data

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.rest.GitHubApi
import ru.geekbrains.data.room.DBStorage
import javax.inject.Inject

class GitHubUserRepositoryImpl
@Inject constructor(
    private val gitHubApi: GitHubApi,
    private val roomDb: DBStorage
) : GitHubUserRepository {



    override fun getUsers():Single<List<GitHubUser>>
    {
        return roomDb.getGitHubUserDao().getUsers()
            .flatMap { users ->
                if (users.isEmpty()) {
                    gitHubApi.fetchUsers()
                        .map { apiRez ->
                            roomDb.getGitHubUserDao().saveUser(apiRez)
                            apiRez
                        }
                } else {
                    Single.just(users)
                }
            }
    }



    override fun getUserByLogin(userId: String): Single<GitHubUser>
    {
        return  roomDb.getGitHubUserDao().getUserByLogin(userId)
    }
}