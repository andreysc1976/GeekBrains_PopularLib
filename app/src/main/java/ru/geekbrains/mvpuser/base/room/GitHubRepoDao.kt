package ru.geekbrains.mvpuser.base.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.mvpuser.base.GitHubRepo

@Dao
interface GitHubRepoDao {



        @Query("SELECT * FROM GitHubRepoTable")
        fun getRepos(): Single<List<GitHubRepo>>

        @Query("SELECT * FROM GitHubRepoTable WHERE full_name LIKE :login")
        fun getReposByUser(login: String): Single<List<GitHubRepo>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun saveRepo(repos: List<GitHubRepo>)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun saveRepo(repo: GitHubRepo)


}