package ru.geekbrains.mvpuser.base

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.mvpuser.base.rest.GitHubRepoApi
import ru.geekbrains.mvpuser.base.room.DBRepoStorage
import javax.inject.Inject

class GitHubRepoRepositoryImpl
    @Inject constructor(
        private val gitHubRepoApi: GitHubRepoApi,
        private val roomRepoDb: DBRepoStorage
) :GitHubRepoRepository {


    override fun getUserRepo(login: String): Single<List<GitHubRepo>> {
        return  roomRepoDb.getGitHubRepoDao().getReposByUser(login)
            .flatMap { repos->
                if (repos.isEmpty()){
                    gitHubRepoApi.fetchUserByLogin(login)
                        .map {apiRez->
                            roomRepoDb.getGitHubRepoDao().saveRepo(apiRez)
                            apiRez
                        }
                } else {
                    Single.just(repos)
                }
            }
    }
}