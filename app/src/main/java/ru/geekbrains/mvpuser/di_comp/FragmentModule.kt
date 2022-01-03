package ru.geekbrains.mvpuser.di_comp

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.data.GitHubUserRepositoryImpl
import ru.geekbrains.data.rest.GitHubApi
import ru.geekbrains.data.room.DBStorage
import ru.geekbrains.mvpuser.base.GitHubRepoRepository
import ru.geekbrains.mvpuser.base.GitHubRepoRepositoryImpl
import ru.geekbrains.mvpuser.base.rest.GitHubRepoApi
import ru.geekbrains.mvpuser.base.room.DBRepoStorage


@Module
class FragmentModule {

    @FragmentScope
    @Provides
    fun provideDBRepoStorage(context: Context): DBRepoStorage{
        return Room.databaseBuilder(context, DBRepoStorage::class.java, "githubrepo.db")
            .build()
    }

    @FragmentScope
    @Provides
    fun provideGitHubRepoRepositiry(api: GitHubRepoApi, dbRepoStorage: DBRepoStorage): GitHubRepoRepository {
        return GitHubRepoRepositoryImpl(api, dbRepoStorage)
    }

    @FragmentScope
    @Provides
    fun provideGitHubRepoApi(gson: Gson): GitHubRepoApi =
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(
                OkHttpClient.Builder()
                    .addNetworkInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GitHubRepoApi::class.java)
}