package ru.geekbrains.dagger

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.data.GitHubUserRepositoryImpl
import ru.geekbrains.data.rest.GitHubApi
import ru.geekbrains.data.room.DBStorage
import javax.inject.Named
import javax.inject.Singleton

@Module
class LocalStorageModule {
    @Provides
    fun provideRepository(api: GitHubApi, dbStorage: DBStorage): GitHubUserRepository {
        return GitHubUserRepositoryImpl(api, dbStorage)
    }

    @Singleton
    @Provides
    fun providesRoomModule(app: Context): DBStorage {
        return Room.databaseBuilder(app, DBStorage::class.java, "github.db")
            .build()
    }
}