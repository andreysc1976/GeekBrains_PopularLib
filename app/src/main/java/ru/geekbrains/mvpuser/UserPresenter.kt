package ru.geekbrains.mvpuser

import android.util.Log
import com.google.android.material.appbar.AppBarLayout
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import moxy.MvpPresenter
import ru.geekbrains.App
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.mvpuser.base.GitHubRepoRepository
import ru.geekbrains.mvpuser.di_comp.FragmentScope
import ru.geekbrains.navigation.CustomRouter
import javax.inject.Inject

class UserPresenter(
    private val userLogin: String,
) : MvpPresenter<UserView>() {

    @Inject lateinit var userRepoRepository: GitHubRepoRepository
    @Inject lateinit var userRepository:GitHubUserRepository


    override fun onFirstViewAttach() {
        userRepository
            .getUserByLogin(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({user->
                viewState.showUser(user)
            })

        userRepoRepository
            .getUserRepo(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }


}