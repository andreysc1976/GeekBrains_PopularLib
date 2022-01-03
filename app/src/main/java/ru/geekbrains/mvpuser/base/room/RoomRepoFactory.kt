package ru.geekbrains.mvpuser.base.room

import androidx.room.Room
import ru.geekbrains.App

object RoomRepoFactory {
    private val database: DBRepoStorage by lazy {
        Room.databaseBuilder(App.instance, DBRepoStorage::class.java, "githubrepo.db")
            .build()
    }

    fun create(): DBRepoStorage = database
}