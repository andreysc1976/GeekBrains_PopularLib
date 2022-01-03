package ru.geekbrains.mvpuser.base.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.mvpuser.base.GitHubRepo


@Database(exportSchema = false, entities = [GitHubRepo::class], version = 1)
abstract class DBRepoStorage: RoomDatabase() {
    abstract fun getGitHubRepoDao(): GitHubRepoDao
}